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

@CATask(
        name = "dbConnection",
        shortCut = "dbc",
        description = "Generate class for data base connections"
)
public class GenerateBdConnectionTask extends AbstracScreenPlayArchitectureDefaultTask{

    private String dataBaseType;

    @Option(option = "dataBase", description = "Define data base type to implement")
    public void setDataBaseType(String dataBase) { this.dataBaseType = dataBase; }

    @Override
    public void execute() throws IOException, ScreenPlayException {
        if ( dataBaseType.isEmpty()){
            throw new IllegalArgumentException("The data base type is necessary: use gradle dbConnection --dataBase=[databasetype]");
        }
        if ( !Constants.DATABASE.contains(dataBaseType.toLowerCase())){
            throw new IllegalArgumentException("you must choose a type of database available in the following list:" +
                    " MySQL, PostgreSQL, Oracle, SQLServer and AS400");
        }

        logger.lifecycle("ScreenPlay architecture plugin version: {}", Util.getVersionPlugin());
        logger.lifecycle("Type implementation: {}", dataBaseType);
        builder.setupFromTemplate("database/" + dataBaseType.toLowerCase(), Constants.DEFINITION_FILE);
        Util.getDataFile(builder, "//Additional library",
                Constants.DATA_BASE_LIBRARY.get(dataBaseType.toLowerCase()));
        Util.getDataFile(builder, "// System configs",
                Constants.SETTING_SYSTEM_CONFIG.replace("PARAM",
                        Constants.PARAM_CONFIG.get(dataBaseType.toLowerCase())));
        builder.persist();
    }
}
