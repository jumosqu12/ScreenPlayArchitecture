package co.com.pragma.tasks;

import co.com.pragma.exceptions.ParamNotFoundException;
import co.com.pragma.exceptions.ScreenPlayException;
import co.com.pragma.tasks.annotations.CATask;
import co.com.pragma.utils.Util;
import org.gradle.api.tasks.options.Option;

import java.io.IOException;

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
        builder.setupFromTemplate(path);
    }
}
