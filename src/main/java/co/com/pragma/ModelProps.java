package co.com.pragma;

import org.gradle.api.provider.Property;

public interface ModelProps {
    Property<String> getWhiteListedDependencies();
}
