package co.com.bancolombia.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Constants {

    public static final String PLUGIN_TASK_GROUP = "ScreenPlay Architecture";
    public static final String APP_SERVICE = "app-service";

    public enum BooleanOption {
        TRUE,
        FALSE
    }

    public enum Language {
        JAVA,
        KOTLIN
    }

    public static final int Java11 = 11;

    public enum ProjectType {
        REST,
        UX
    }

    public static final String PLUGIN_VERSION = "1.1.0";
    public static final String BUILD_GRADLE = "./build.gradle";
    public static final String CRITICAL_ROOT_NAME = "rutacritica";
    public static final String GRADLE_PROPERTIES = "/gradle.properties";
    public static final String SETTINGS_GRADLE = "/settings.gradle";
    public static final String SERENITY_PROPERTIES = "./serenity.properties";
    public static final String BANCOLOMBIA_REPOSITORIES = "https://artifactory.apps.bancolombia.com/maven-bancolombia/";
    public static final String SERENITY_VERSION = "4.1.0";
    public static final String SERENITY_REST_LIBRARY = "net.serenity-bdd:serenity-screenplay-rest:$rootProject.ext.serenityVersion";
    public static final String SERENITY_UX_LIBRARY = "net.serenity-bdd:serenity-screenplay-webdriver:$rootProject.ext.serenityWebVersion";
    public static final Map<String, String> DATA_BASE_LIBRARY = Map.of("mysql",
            "implementation group: 'com.mysql', name: 'mysql-connector-j', version: '8.4.0'\n" +
                    "    implementation group: 'org.apache.tomcat', name: 'tomcat-jdbc', version: '10.1.24'\n" +
                    "    //Additional library");
    public static final String SERENITY_CUCUMBER_VERSION = "4.1.0";
    public static final String SERENITY_WEBDRIVER_VERSION = "serenityWebVersion = '4.1.0'";
    public static final String SERENITY_WEBDRIVER = "testImplementation(\"io.github.bonigarcia:webdrivermanager:5.4.1\")";
    public static final String LOMBOK_VERSION = "1.18.22";
    public static final String JUNIT = "4.13.2";
    public static final String HAMCREST = "1.3";
    public static final List<String> INTERACTIONS = List.of(new String[]{"POST", "GET", "PUT", "OPTIONS", "PATCH", "GENERIC"});
    public static final List<String> LANGUAGE = List.of(new String[]{"EN", "ES"});
    public static final List<String> TASKS = List.of(new String[]{"UX", "REST"});
    public static final List<String> SCENARIOS = List.of(new String[]{"Scenario", "Escenario", "Esquema del escenario", "Scenario Outline"});
    public static final List<String> STEPS = List.of(new String[]{"Dado", "Given"});
    public static final List<String> DATABASE = List.of(new String[]{"mysql", "postgresql", "oracle", "sqlServer", "redis", "s3", "as400"});
    public static final String METHODUI = "@Given(\"This method is responsible for parameterizing the instantiation of chromedriver\")\n" +
            "    public void thisMethodIsResponsibleForParameterizingTheInstantiationOfChromedriver() {\n" +
            "        OnStage.theActorInTheSpotlight().wasAbleTo(Open.browserOn().thePageNamed(\"pages.swaglabsUrl\"));\n" +
            "    }";




}
