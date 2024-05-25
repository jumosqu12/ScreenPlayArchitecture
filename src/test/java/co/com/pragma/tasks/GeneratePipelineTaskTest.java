package co.com.pragma.tasks;

import co.com.pragma.exceptions.ScreenPlayException;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static co.com.pragma.utils.FileUtilsTest.deleteStructure;
import static org.junit.Assert.assertTrue;

public class GeneratePipelineTaskTest {


    GeneratePipelineTask task;

    static Project project =
            ProjectBuilder.builder().withProjectDir(new File("build/unitTest")).build();

    @Before
    public void init() throws IOException, ScreenPlayException {
        deleteStructure(Path.of("build/unitTest"));
        setup();
    }

    @AfterClass
    public static void clean() {
        deleteStructure(project.getProjectDir().toPath());
    }

    public void setup() throws IOException, ScreenPlayException {
        deleteStructure(project.getProjectDir().toPath());
        project.getTasks().create("spa", GenerateArchitectureDefaultTask.class);
        GenerateArchitectureDefaultTask architectureDefaultTask = (GenerateArchitectureDefaultTask)
                project.getTasks().getByName("spa");
        architectureDefaultTask.execute();

        project.getTasks().create("test", GeneratePipelineTask.class);
        task = (GeneratePipelineTask) project.getTasks().getByName("test");
    }

    @Test
    public void generatePipeline() throws ScreenPlayException, IOException {
        task.setName("AW130_PruebasUnit_Test");
        task.setType("azure");
        task.execute();
        assertTrue(new File("build/unitTest/AW130_PruebasUnit_Test_Build.yaml").exists());
    }
}
