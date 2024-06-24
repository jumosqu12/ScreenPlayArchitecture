package co.com.bancolombia.factory.pipelines;

import co.com.bancolombia.exceptions.ScreenPlayException;
import co.com.bancolombia.factory.ModuleBuilder;
import co.com.bancolombia.factory.ModuleFactory;
import co.com.bancolombia.utils.Constants;

import java.io.IOException;

public class PipelineAzure implements ModuleFactory {
    @Override
    public void buildModule(ModuleBuilder builder) throws IOException, ScreenPlayException {
        builder.setupFromTemplate("pipeline/azure", Constants.DEFINITION_FILE);
    }
}
