Write-Host "Choose a game to run:"
Write-Host "1) Street Fighter 2 (sf2)"
Write-Host "2) Mortal Kombat 2 (mk2)"
Write-Host "3) SF vs MK 2 (sfvsmk2)"
$choice = Read-Host "Enter your choice [1-3]"

switch ($choice) {
    '1' {
        $JAR = "org.jfge.games.sf2/target/sf2-0.0.1-SNAPSHOT.jar"
        $MAIN_CLASS = "org.jfge.games.sf2.game.StreetFighter2"
    }
    '2' {
        $JAR = "org.jfge.games.mk2/target/mk2-0.0.1-SNAPSHOT.jar"
        $MAIN_CLASS = "org.jfge.games.mk2.game.MortalKombat2"
    }
    '3' {
        $JAR = "org.jfge.games.sfvsmk2/target/sfvsmk2-0.0.1-SNAPSHOT.jar"
        $MAIN_CLASS = "org.jfge.games.sfvsmk2.game.SFVSMK2"
    }
    default {
        Write-Host "Invalid choice. Exiting."
        exit 1
    }
}

# Detect Java major version
$javaVersion = (& java -version 2>&1)[0]
if ($javaVersion -match '(\d+)(\.\d+)?') {
    $major = [int]$matches[1]
} else {
    $major = 8  # fallback
}
Write-Host "Detected Java version: $major"

# Build classpath (use ; for Windows)
$CLASSPATH = ".;sf2.zip;mk2.zip;sfvsmk2.zip;$JAR"

# Create argument array
$javaArgs = @()
$javaArgs += "-cp"
$javaArgs += "$CLASSPATH"
if ($major -gt 9) {
    $javaArgs += "--add-opens"
    $javaArgs += "java.base/java.lang=ALL-UNNAMED"
}
$javaArgs += "$MAIN_CLASS"

# Show and run command
Write-Host "Running: java $($javaArgs -join ' ')"
& java @javaArgs
