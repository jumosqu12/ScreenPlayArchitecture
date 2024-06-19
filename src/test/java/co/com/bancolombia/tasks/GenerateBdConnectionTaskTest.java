package co.com.bancolombia.tasks;

import co.com.bancolombia.exceptions.ScreenPlayException;
import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import static co.com.bancolombia.utils.FileUtilsTest.deleteStructure;
import static org.junit.Assert.assertTrue;

public class GenerateBdConnectionTaskTest {

    GenerateBdConnectionTask task;

    @Before
    public void init() throws IOException, ScreenPlayException {
        File projectDir = new File("build/unitTest");
        Files.createDirectories(projectDir.toPath());
        writeString(
                new File(projectDir, "build.gradle"),
                "plugins {" + "  co.com.bancolombia.screenPlayArchitecture')" + "}");

        Project project =
                ProjectBuilder.builder()
                        .withName("cleanArchitecture")
                        .withProjectDir(new File("build/unitTest"))
                        .build();

        project.getTasks().create("testStructure", GenerateArchitectureDefaultTask.class);
        GenerateArchitectureDefaultTask taskStructure =
                (GenerateArchitectureDefaultTask) project.getTasks().getByName("testStructure");
        taskStructure.execute();

        project.getTasks().create("test", GenerateBdConnectionTask.class);
        task = (GenerateBdConnectionTask) project.getTasks().getByName("test");
    }
    @Test
    public void generateMySqlDbConnection() throws ScreenPlayException, IOException {
        task.setDataBaseType("mysql");
        task.execute();
        assertTrue(new File("build/unitTest/src/main/java/co/com/example/test/screen/utils/connetiondb/MySQLConnection.java").exists());
        assertTrue(new File("build/unitTest/src/main/java/co/com/example/test/screen/utils/connetiondb/ExecuteQueryDb.java").exists());
        assertTrue(new File("build/unitTest/mysql.properties").exists());
    }
    @Test
    public void generateAs400DbConnection() throws ScreenPlayException, IOException {
        task.setDataBaseType("as400");
        task.execute();
        assertTrue(new File("build/unitTest/src/main/java/co/com/example/test/screen/utils/connetiondb/AS400Connection.java").exists());
        assertTrue(new File("build/unitTest/as400.properties").exists());
    }
    @Test
    public void generatePostgreSqlDbConnection() throws ScreenPlayException, IOException {
        task.setDataBaseType("postgresql");
        task.execute();
        assertTrue(new File("build/unitTest/src/main/java/co/com/example/test/screen/utils/connetiondb/PostgreSQLConnection.java").exists());
        assertTrue(new File("build/unitTest/postgresql.properties").exists());
    }

    @Test
    public void generateSqlServerDbConnection() throws ScreenPlayException, IOException {
        task.setDataBaseType("sqlserver");
        task.execute();
        assertTrue(new File("build/unitTest/src/main/java/co/com/example/test/screen/utils/connetiondb/SqlServerConnection.java").exists());
        assertTrue(new File("build/unitTest/sqlServer.properties").exists());
    }

    private void writeString(File file, String string) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            writer.write(string);
        }
    }
}
