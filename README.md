# A plugin POC

This project demonstrates a simple java application plugin mechanism.

## plugin-loader

is the main application
it will load the plugins

## plugin-1

is an example plugin


# How to

The `PluginLoader` class is responsible for loading the plugin
it will search for jar files and the parse them.
It will search for a `plugin.cnf` config file in the jar.
The config file contains the name and the plugin class of the plugin.

All the jar files in the `./plugins` folder relative to the `plugin-loader-*.jar`
will be parsed this way. (you can easily make more plugins by renaming them in the pom and plugin.cnf)

## Build

```bash
mvn clean install
```

the install in this case is needed as the plugin as a compile dependency on the `plugin-loader` module as it defines the interface the plugin needs to adhere to.


an `artifact` folder will be created with the executable plugin-loader jar and the plugin in the artifact/plugins folder

## executing

```bash
cd artifact
java -jar plugin-loader-jar-with-dependencies.jar 
```

it should result in something like:

```text
Running
Running
Running
Running
Running
Running
Running
Running
Running
Running
Shutting down
```

have fun.

Cheerz,
Ivo.

