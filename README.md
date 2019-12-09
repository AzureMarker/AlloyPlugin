# Alloy Plugin for the IntelliJ Platform
This plugin adds support for writing and evaluating Alloy code. Alloy is a
modeling language and analyzer: http://alloytools.org/

## Building
This project uses Gradle for building. It is recommended to use the Gradle
wrapper script, `gradlew` or `gradlew.bat`.

Use the following command to build the project:
```
./gradlew build
```

This will build the plugin and save a distributable zip in
`build/distributions`.

## Running
There are two ways to run the plugin. It can either be run through a temporary
IDE instance or an existing JetBrains IDE (2019.2.* or 2019.3.*).

### Temporary IDE
This command will run a temporary IDE instance with the plugin loaded.

```
./gradlew runIde
```

### Existing IDE
First, build the plugin (see above section) and then follow these steps:
1. Open your existing IDE
2. Go to Settings -> Plugins
3. Click the cog icon and choose "Install Plugin from Disk..."
4. Select this plugin's zip file (in `build/distributions`)
5. Click the "Restart IDE" button to finish the plugin installation
