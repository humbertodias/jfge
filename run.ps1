Write-Host "Choose a game to run:"
Write-Host "1) Street Fighter 2 (sf2)"
Write-Host "2) Mortal Kombat 2 (mk2)"
Write-Host "3) SF vs MK 2 (sfvsmk2)"
$choice = Read-Host "Enter your choice [1-3]"

switch ($choice) {
    '1' { $JAR = "org.jfge.games.sf2/target/sf2-0.0.1-SNAPSHOT.jar" }
    '2' { $JAR = "org.jfge.games.mk2/target/mk2-0.0.1-SNAPSHOT.jar" }
    '3' { $JAR = "org.jfge.games.sfvsmk2/target/sfvsmk2-0.0.1-SNAPSHOT.jar" }
    default {
        Write-Host "Invalid choice. Exiting."
        exit 1
    }
}

# Get Java version output (redirect both stdout and stderr)
$javaVersionOutput = & java -version 2>&1

# Look for first match of version "X" or X.0.0 in all lines
$versionLine = $javaVersionOutput | Where-Object { $_ -match '\d+\.\d+(\.\d+)?' } | Select-Object -First 1

if ($versionLine -match '(\d+)\.(\d+)') {
    # Java 9+ reports as "9.0" or "11.0", etc
    $JAVA_VERSION = [int]$matches[1]
    Write-Host "Detected Java version: $JAVA_VERSION"
} else {
    Write-Host "Could not determine Java version. Assuming version <= 9."
    $JAVA_VERSION = 9
}

$EXTRA_OPTS = ""
if ($JAVA_VERSION -gt 9) {
    $EXTRA_OPTS = "--add-opens java.base/java.lang=ALL-UNNAMED"
}

Write-Host "Running: java $EXTRA_OPTS -jar $JAR"

# Run with or without extra options
if ($EXTRA_OPTS) {
    & java $EXTRA_OPTS -jar $JAR
} else {
    & java -jar $JAR
}