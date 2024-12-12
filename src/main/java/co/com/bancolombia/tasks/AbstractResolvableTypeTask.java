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
import co.com.bancolombia.factory.ModuleFactory;
import co.com.bancolombia.utils.Util;
import org.gradle.api.tasks.options.Option;
import org.gradle.api.tasks.options.OptionValues;

import java.io.IOException;
import java.util.List;

public abstract class AbstractResolvableTypeTask extends AbstracScreenPlayArchitectureDefaultTask{
    protected String name;
    protected String type;

    @Option(option = "name", description = "Set name")
    public void setName(String name) { this.name = name; }
    @Option(option = "type", description = "Set type")
    public void setType(String type) { this.type = type; }

    @OptionValues("type")
    public List<String> getTypes() { return super.resolveTypes(); }

    @Override
    public void execute() throws IOException, ScreenPlayException {
        type = type == null ? null : type;
        name = name == null ? null : name;
        if (type == null) {
            printHelp();
            throw new IllegalArgumentException(
                    "No " + resolvePrefix() + " type is set, usage: gradle " + getName() + " --type "
                            + Util.formatTaskOptions(getTypes()));
        }
        ModuleFactory moduleFactory = resolveFactory(type);
        logger.lifecycle("ScreenPlay Architecture plugin version: {}", Util.getVersionPlugin());
        logger.lifecycle("{} name: {}", resolvePrefix(), name);
        builder.addParam("namePipeline", name);
        builder.addParam("type", type);
        prepareParams();
        moduleFactory.buildModule(builder);
        builder.persist();
    }

    protected abstract void prepareParams();
}
