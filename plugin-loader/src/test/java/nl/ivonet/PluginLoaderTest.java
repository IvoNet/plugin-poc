package nl.ivonet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PluginLoaderTest {

    private PluginLoader pluginLoader;

    @BeforeEach
    void setUp() {
        this.pluginLoader = new PluginLoader();
    }

    @Test
    void name() {
        final Map<String, PluginData> stringPluginDataMap = pluginLoader.parsePlugins("/Users/iwo16283/dev/ivonet-pocs/plugin-poc/artifact/plugins");
        System.out.println("stringPluginDataMap = " + stringPluginDataMap);
    }
}
