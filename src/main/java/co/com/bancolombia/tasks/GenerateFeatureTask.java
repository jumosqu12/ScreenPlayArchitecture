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

import co.com.bancolombia.exceptions.ParamNotFoundException;
import co.com.bancolombia.exceptions.ScreenPlayException;
import co.com.bancolombia.tasks.annotations.CATask;
import co.com.bancolombia.utils.Constants;
import co.com.bancolombia.utils.FileUtil;
import co.com.bancolombia.utils.Util;
import org.gradle.api.tasks.options.Option;

import java.io.IOException;

import static co.com.bancolombia.utils.Constants.SERENITY_PROPERTIES;

@CATask(
        name = "generateFeature", shortCut = "gft", description = "generate feature file"
)
public class GenerateFeatureTask extends AbstracScreenPlayArchitectureDefaultTask{

    private String name;
    private String nameSubFolder;
    private String examples = "false";

    @Option(option = "name", description = "Set feature file name")
    public void setName(String name){ this.name = name; }

    @Option(option = "examples", description = "Define if Scenario Outline are needed")
    public void setExamples(String examples){ this.examples = examples; }

    @Option(option = "nameSubFolder", description = "Define if folder new are needed")
    public void setNameSubFolder(String subFolder) { this.nameSubFolder = subFolder; }

    @Override
    public void execute() throws IOException, ScreenPlayException {
        if (name.isEmpty()) {
            printHelp();
            throw new IllegalArgumentException("The feature name is necessary: use gradle generateFeature --name=[filename]");
        }
        name = name.toLowerCase();
        logger.lifecycle("ScreenPlay architecture plugin version: {}", Util.getVersionPlugin());
        logger.lifecycle("Feature name: {}", name);
        logger.lifecycle("Implement Scenario Outline {}", examples);
        builder.addParam("featureName", name);
        if (nameSubFolder != null) {
            builder.addParam("subfolder", nameSubFolder.toLowerCase());
            setupToExecute("features/subfolder");
            if (Boolean.parseBoolean(examples)) {
                setupToExecute("features/outline");
            } else {
                setupToExecute("features/feature");
            }
            builder.persist();
        }else {
            throw new IllegalArgumentException("Name subfolder is required ");
        }
    }

    private void setupToExecute(String path) throws ParamNotFoundException, IOException {
        builder.setupFromTemplate(path , Constants.DEFINITION_FILE);
    }
}
