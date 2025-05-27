Write-Host "Choose a game to run:"
Write-Host "1) Street Fighter 2 (sf2)"
Write-Host "2) Mortal Kombat 2 (mk2)"
Write-Host "3) SF vs MK 2 (sfvsmk2)"
$choice = Read-Host "Enter your choice [1-3]"

switch ($choice) {
    "1" {
        $JAR = "sf2.jar"
        Copy-Item "org.jfge.games.sf2\target\sf2-0.0.1-SNAPSHOT.jar" $JAR
    }
    "2" {
        $JAR = "mk2.jar"
        Copy-Item "org.jfge.games.mk2\target\mk2-0.0.1-SNAPSHOT.jar" $JAR
    }
    "3" {
        $JAR = "sfvsmk2.jar"
        Copy-Item "org.jfge.games.sfvsmk2\target\sfvsmk2-0.0.1-SNAPSHOT.jar" $JAR
    }
    Default {
        Write-Host "Invalid choice. Exiting."
        exit 1
    }
}

# Get Java version
$javaVersionOutput = & java -version 2>&1 | Select-Object -First 1
if ($javaVersionOutput -match '\"(\d+).*\"') {
    $JAVA_VERSION = [int]$matches[1]
    Write-Host "Detected Java version: $JAVA_VERSION"
} else {
    Write-Host "Unable to detect Java version."
    exit 1
}

$EXTRA_OPTS = ""

if ($JAVA_VERSION -gt 9) {
    $EXTRA_OPTS = "--add-opens java.base/java.lang=ALL-UNNAMED"
}

Write-Host "Running: java $EXTRA_OPTS -jar $JAR"
& java $EXTRA_OPTS -jar $JAR
