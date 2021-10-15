package io.github.treech.log.logback;

import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.core.joran.spi.DefaultNestedComponentRegistry;

/**
 * Created by yeguoqiang on 2019/11/13.
 */
public class CustomNestedComponentRules {

    private static final List<NestedComponent> NESTED_COMPONENTS;

    static {
        NESTED_COMPONENTS = new ArrayList<>();
    }

    public static void addCustomNestedComponentRegistryRules(DefaultNestedComponentRegistry registry) {

        if (!NESTED_COMPONENTS.isEmpty()) {
            for (NestedComponent component : NESTED_COMPONENTS) {
                registry.add(component.hostClass, component.propertyName,
                        component.componentClass);
            }
        }
    }

    public static void addCustomNestedComponent(NestedComponent component) {
        NESTED_COMPONENTS.add(component);
    }

    public static class NestedComponent {

        final Class<?> hostClass;
        final String propertyName;
        final Class<?> componentClass;

        public NestedComponent(Class<?> hostClass, String propertyName, Class<?> componentClass) {
            this.hostClass = hostClass;
            this.propertyName = propertyName;
            this.componentClass = componentClass;
        }
    }
}
