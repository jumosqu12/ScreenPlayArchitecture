package co.com.pragma.tasks;

import co.com.pragma.exceptions.ScreenPlayException;
import co.com.pragma.tasks.annotations.CATask;
import co.com.pragma.utils.Constants;
import co.com.pragma.utils.Constants.ProjectType;
import co.com.pragma.utils.Constants.Language;
import co.com.pragma.utils.Constants.BooleanOption;
import static co.com.pragma.utils.Constants.SERENITY_PROPERTIES;

import co.com.pragma.utils.FileUtil;
import co.com.pragma.utils.Util;
import org.gradle.api.tasks.options.Option;


import java.io.IOException;

@CATask(
        name = "screenPlayArchitecture",
        shortCut = "spa",
        description = "Scaffolding ScreenPlay architecture project"
)
public class GenerateArchitectureDefaultTask extends AbstracScreenPlayArchitectureDefaultTask{
    private String groupId = "co.com.example.test";
    private ProjectType type = ProjectType.UX;
    private String projectName = "Screenplay_architecture";
    private String principalPackage = "screen";
    private BooleanOption force = BooleanOption.FALSE;
    private Language language = Language.JAVA;
    private int javaVersion = Constants.Java11;

    @Option(option = "groupId", description = "Set group id to use in the project")
    public void setgroupId(String groupId) { this.groupId = groupId; }

    @Option(option = "principalPackage", description = "Set name principal package on project")
    public void principalPackage(String principalPackage) { this.principalPackage = principalPackage; }

    @Option(option = "type", description = "Set project type to implement")
    public void setType(ProjectType type) { this.type = type; }

    @Option(option = "projectName", description = "Set project name, by default is ScreenPlayArchitecture")
    public void name(String projectName) { this.projectName = projectName; }

    @Option(option = "language", description = "Set code language project, by default is Java")
    public void language(Language language) { this.language = language; }

    @Option(option = "javaVersion", description = "Set Java version")
    public void javaVersion(String javaVersion) { this.javaVersion = Integer.parseInt(javaVersion); }

    @Option(option = "force", description = "Force regenerates all files")
    public void setForce(BooleanOption force) {
        this.force = force;
    }


    @Override
    public void execute() throws IOException, ScreenPlayException {
        logger.lifecycle("ScreenPlay architecture plugin version: {}" , Util.getVersionPlugin());
        logger.lifecycle("Project Name: {}" , projectName);
        logger.lifecycle("Group id: {}" , groupId);
        logger.lifecycle("Principal Package: {}" , principalPackage);
        logger.lifecycle("Project type: {}" , type);
        logger.lifecycle("Java Version: {}" , javaVersion);
        builder.addGroupId(groupId);
        builder.addParam("projectName", projectName);
        builder.addParam("principalPackage", principalPackage);
        builder.addParam("language", language.name().toLowerCase());
        builder.addParam("libreryToUse", type.equals(ProjectType.UX) ?
                Constants.SERENITY_UX_LIBRARY:Constants.SERENITY_REST_LIBRARY);
        builder.addParam("webDriverV", type.equals(ProjectType.UX) ? Constants.SERENITY_WEBDRIVER_VERSION:"");
        builder.addParam("webDriverLibrary", type.equals(ProjectType.UX) ? Constants.SERENITY_WEBDRIVER:"");
        builder.addParam("javaVersion", javaVersion);
        builder.addParam("java11", javaVersion == Constants.Java11);
        builder.addParam("remoteRepository", Constants.BANCOLOMBIA_REPOSITORIES);
        builder.addParam("serenityV", Constants.SERENITY_VERSION);
        builder.addParam("cucumberV",Constants.SERENITY_CUCUMBER_VERSION);
        builder.addParam("junitV", Constants.JUNIT);
        builder.addParam("hamcrestV", Constants.HAMCREST);
        builder.addParam("versionLoom", Constants.LOMBOK_VERSION);
        builder.addParam("screenArchitectureV", Util.getVersionPlugin());

        Boolean exists = FileUtil.exists(builder.getProject().getProjectDir().getPath(), SERENITY_PROPERTIES);
        if (exists && force == BooleanOption.FALSE){
            logger.lifecycle("Another project was found in the same directory, rewriting build.gradle, gradle.properties and setting.gradle files ");
            builder.setupFromTemplate("structure/restructure");
        }else{
            builder.setupFromTemplate("structure");
        }
        builder.persist();
    }

}
