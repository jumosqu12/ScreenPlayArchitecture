/**
 * Copyright [2024] [Junior Mosquera Mosquera ]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.com.bancolombia.tasks;

import co.com.bancolombia.exceptions.ScreenPlayException;
import co.com.bancolombia.tasks.annotations.CATask;
import co.com.bancolombia.utils.Constants;
import co.com.bancolombia.utils.Util;
import org.gradle.api.tasks.options.Option;

import java.io.IOException;

@CATask(name = "generateRestInteraction", shortCut = "gri", description = "Generate rest Interaction classes")
public class GenerateRestInteractionTask extends AbstracScreenPlayArchitectureDefaultTask{
    protected String typeInteraction = "";
    protected String nameInteraction = "";

    @Option(option = "typeInteraction", description = "set the type of interaction to use ")
    public void setTypeInteraction(String typeInteraction) { this.typeInteraction = typeInteraction; }

    @Option(option = "nameInteraction", description = "set the name of interaction to use ")
    public void setNameInteraction(String nameInteraction) { this.nameInteraction = nameInteraction; }


    @Override
    public void execute() throws IOException, ScreenPlayException {
        if (typeInteraction.isEmpty() ) {
            throw new IllegalArgumentException("The type interaction is necessary: use gradle generateRestInteraction --typeInteraction=[interaction]");
        }
        if (!Constants.INTERACTIONS.contains(typeInteraction.toUpperCase())) {
            throw new IllegalArgumentException("The type interaction is not valid");
        }
        if (typeInteraction.equalsIgnoreCase("GENERIC") && nameInteraction.isEmpty()) {
            throw new IllegalArgumentException("When you choose GENERIC as a interaction type, the name for" +
                    " that class is necessary: \nuse gradle generateRestInteraction --typeInteraction=GENERIC " +
                    "--nameInteraction=[name]");
        }
        logger.lifecycle("ScreenPlay architecture plugin version: {}", Util.getVersionPlugin());
        logger.lifecycle("Type interaction: {}", typeInteraction);
        if (!nameInteraction.isEmpty()) {
            builder.addParam("nameInteCapitalize", Util.capitalize(nameInteraction));
            builder.addParam("nameInteLower", Util.nocapitalize(nameInteraction));
        }
        builder.setupFromTemplate("interactions/" + typeInteraction.toLowerCase(), Constants.DEFINITION_FILE);
        builder.persist();
    }
}
