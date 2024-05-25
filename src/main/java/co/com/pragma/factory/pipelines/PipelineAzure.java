package co.com.pragma.factory.pipelines;

import co.com.pragma.exceptions.ScreenPlayException;
import co.com.pragma.factory.ModuleBuilder;
import co.com.pragma.factory.ModuleFactory;

import java.io.IOException;

public class PipelineAzure implements ModuleFactory {
    @Override
    public void buildModule(ModuleBuilder builder) throws IOException, ScreenPlayException {
        builder.setupFromTemplate("pipeline/azure");
    }
}
