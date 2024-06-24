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
