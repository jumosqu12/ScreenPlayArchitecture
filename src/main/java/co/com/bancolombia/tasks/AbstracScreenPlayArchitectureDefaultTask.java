package co.com.bancolombia.tasks;
/**
 * Copyright [2024] [Junior Mosquera Mosquera ]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import co.com.bancolombia.exceptions.InvalidTaskOptionException;
import co.com.bancolombia.exceptions.ScreenPlayException;
import co.com.bancolombia.factory.ModuleBuilder;
import co.com.bancolombia.factory.ModuleFactory;
import co.com.bancolombia.utils.ReflectionUtils;
import lombok.SneakyThrows;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.tasks.TaskAction;
import org.gradle.internal.logging.text.StyledTextOutputFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstracScreenPlayArchitectureDefaultTask extends DefaultTask {

    protected final ModuleBuilder builder = new ModuleBuilder(getProject());
    protected final Logger logger = getProject().getLogger();

    protected AbstracScreenPlayArchitectureDefaultTask(){
        builder.setStyledLogger(
                getTextOutputFactory().create(AbstracScreenPlayArchitectureDefaultTask.class)
        );
    }

    @Inject
    protected StyledTextOutputFactory getTextOutputFactory() {
        throw new UnsupportedOperationException();
    }

    @TaskAction
    public void executeBaseTask() throws IOException, ScreenPlayException{

        long start = System.currentTimeMillis();
        execute();
        afterExecute(
                () -> {
                    String type = "After" + builder.getStringParam("type");
                    return resolveFactory(resolvePackage(), resolvePrefix(), type);
                });
        afterExecute(() -> resolveFactory(getClass().getPackageName(), "", "After" + getCleanedClass()));
    }

    public abstract void execute() throws IOException, ScreenPlayException;

    private void afterExecute(Supplier<ModuleFactory> factorySupplier) {
        try {
            ModuleFactory moduleFactory = factorySupplier.get();
            logger.lifecycle("Applying {}", moduleFactory.getClass().getSimpleName());
            moduleFactory.buildModule(builder);
            logger.lifecycle("{} applied", moduleFactory.getClass().getSimpleName());
        }catch (UnsupportedOperationException | InvalidTaskOptionException ignored){
            logger.debug("No ModuleFactory implementation");
        }catch (IOException | ScreenPlayException e){
            logger.warn("Error on afterExecute factory: ", e);
        }
    }

    @SneakyThrows
    protected ModuleFactory resolveFactory(String packageName, String prefix, String type) {
        logger.info(
                "Finding factory with prefix {} and type {} in package {}", prefix, type, packageName);
        return ReflectionUtils.getModuleFactories(packageName)
                .filter(clazz -> clazz.getSimpleName().replace(prefix, "").equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(
                        () ->
                                new InvalidTaskOptionException(
                                        prefix + " of type " + type + " not found, valid values: " + resolveTypes()))
                .getDeclaredConstructor()
                .newInstance();
    }

    @SneakyThrows
    protected List<String> resolveTypes() {
        return ReflectionUtils.getModuleFactories(resolvePackage())
                .map(clazz -> clazz.getSimpleName().replace(resolvePrefix(), "").toUpperCase())
                .collect(Collectors.toList());
    }

    private String getCleanedClass() {
        String className = getClass().getSimpleName();
        if (className.contains("$")) {
            className = className.split("\\$")[1];
        }
        if (className.contains("_Decorated")) {
            className = className.split("_")[0];
        }
        return className;
    }

    protected void printHelp() {
        Optional.ofNullable(getProject().getTasks().findByPath("help"))
                .ifPresent(task -> task.getActions().stream()
                        .findFirst()
                        .ifPresent(
                                action -> {
                                    task.setProperty("taskPath", getName());
                                    action.execute(task);
                                }));
    }
    @SneakyThrows
    protected ModuleFactory resolveFactory(String type) {
        return resolveFactory(resolvePackage(), resolvePrefix(), type);
    }

    protected String resolvePrefix() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    protected String resolvePackage() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
