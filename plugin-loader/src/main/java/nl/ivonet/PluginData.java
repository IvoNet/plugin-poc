package nl.ivonet;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginData {

    private final String name;
    private final String main;
    private final URL jar;
    private final Class<?> cache;

    public PluginData(final String name, final String mainClass, final URL jar) throws ClassNotFoundException {
        this.name = name;
        this.main = mainClass;
        this.jar = jar;
        this.cache = Class.forName(this.main, true, new URLClassLoader(new URL[] {this.jar}));
    }

    public String getName() {
        return name;
    }

    public AbstractPlugin getInstance() {
        try {
            return (AbstractPlugin) this.cache.getDeclaredConstructor().newInstance();
        } catch(final InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "PluginData{" +
                "name='" + name + '\'' +
                '}';
    }
}
