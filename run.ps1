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

# Detect Java major version
$javaVersion = (& java -version 2>&1)[0]
if ($javaVersion -match '(\d+)(\.\d+)?') {
    $major = [int]$matches[1]
} else {
    $major = 8  # fallback
}
Write-Host "Detected Java version: $major"

# Prepare command
if ($major -gt 9) {
    $cmd = "java --add-opens java.base/java.lang=ALL-UNNAMED -jar `"$JAR`""
} else {
    $cmd = "java -jar `"$JAR`""
}

Write-Host "Running: $cmd"
Invoke-Expression $cmd