package nl.ivonet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {

    private final Map<String, PluginData> data;

    public PluginLoader() {
        data = new HashMap<>();
    }

    public void parsePlugin(final String dir) throws IOException {
        parsePlugin(new File(dir));
    }

    public void parsePlugin(final File dir) {
        final JarFile jarFile;
        try {
            jarFile = new JarFile(dir);
        } catch(IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
        final JarEntry jarEntry = jarFile.getJarEntry("plugin.cnf");

        try(final BufferedReader br = new BufferedReader(new InputStreamReader(jarFile.getInputStream(jarEntry)))) {
            String line;
            while((line = br.readLine()) != null) {
                if(line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                final String[] split = line.split(" ");
                if(split.length < 2) {
                    throw new IllegalStateException("Config should be <name> <class> lines");
                }
                if(data.containsKey(split[0])) {
                    System.out.println(String.format("A plugin with this the name [%s] is already defined.", split[0]));
                    continue;
                }
                data.put(split[0], new PluginData(split[0], split[1], dir.toURI().toURL()));
            }
        } catch(ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<String, PluginData> parsePlugins(final String dir) {
        parsePlugins(new File(dir));
        return this.data;
    }

    public Map<String, PluginData> parsePlugins(final File dir) {
        Arrays.stream(Objects.requireNonNull(dir.listFiles((file, s) -> s.endsWith(".jar")))).forEach(this::parsePlugin);
        return this.data;
    }
}
