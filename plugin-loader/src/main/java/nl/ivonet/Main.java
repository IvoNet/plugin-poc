package nl.ivonet;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static String getPluginLocation() {
        String abspath = new File(".").getAbsolutePath();
        abspath = abspath.substring(0, abspath.length() - 1);
        return new File(abspath + "plugins/").getAbsolutePath();
    }

    public static void main(String[] args) throws InterruptedException {
        final PluginLoader pluginLoader = new PluginLoader();
        final Map<String, PluginData> pluginDataMap = pluginLoader.parsePlugins(getPluginLocation());
        final List<AbstractPlugin> plugins = pluginDataMap.values().stream().map(PluginData::getInstance).collect(Collectors.toList());
        plugins.forEach(AbstractPlugin::start);
        Thread.sleep(5000);
        plugins.forEach(AbstractPlugin::shutdown);

    }
}
