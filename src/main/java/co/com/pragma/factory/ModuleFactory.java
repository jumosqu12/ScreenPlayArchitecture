package co.com.pragma.factory;

import co.com.pragma.exceptions.ScreenPlayException;

import java.io.IOException;

public interface ModuleFactory {
    void buildModule(ModuleBuilder builder) throws IOException, ScreenPlayException;

}
