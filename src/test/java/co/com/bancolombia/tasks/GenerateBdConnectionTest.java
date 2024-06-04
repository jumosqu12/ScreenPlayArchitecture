package co.com.bancolombia.tasks;

import co.com.bancolombia.exceptions.ScreenPlayException;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static co.com.bancolombia.utils.FileUtilsTest.deleteStructure;
import static org.junit.Assert.assertTrue;

public class GenerateBdConnectionTest {

    GenerateBdConnection task;

    static Project project =
            ProjectBuilder.builder().withProjectDir(new File("build/unitTest")).build();

    @Before
    public void init() throws ScreenPlayException, IOException {
        deleteStructure(Path.of("build/unitTest"));
        setup();
    }

    @AfterClass
    public static void  clean(){
        deleteStructure(project.getProjectDir().toPath());
    }

    public void setup() throws ScreenPlayException, IOException {
        project.getTasks().create("spa", GenerateArchitectureDefaultTask.class);
        GenerateArchitectureDefaultTask architectureDefaultTask = (GenerateArchitectureDefaultTask)
                project.getTasks().getByName("spa");
        architectureDefaultTask.execute();

        project.getTasks().create("test", GenerateBdConnection.class);
        task = (GenerateBdConnection) project.getTasks().getByName("test");
    }

    @Test
    public void generateMySqlDbConnection() throws ScreenPlayException, IOException {
        task.setDataBaseType("mysql");
        task.execute();
        assertTrue(new File("build/unitTest/src/main/java/co/com/example/test/screen/utils/connetiondb/MysqlConnection.java").exists());
        assertTrue(new File("build/unitTest/mysql.properties").exists());
    }
}
