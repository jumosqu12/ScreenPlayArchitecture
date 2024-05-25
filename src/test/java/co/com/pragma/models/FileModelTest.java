package co.com.pragma.models;

import org.junit.Assert;
import org.junit.Test;

public class FileModelTest {

    @Test
    public void fileModelToStringTest() {
        FileModel model = FileModel.builder().content("x").path("y").build();
        Assert.assertNotNull(model.toString());
    }
}
