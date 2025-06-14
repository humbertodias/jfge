# Enable error stopping
$ErrorActionPreference = 'Stop'

try {
    # Move to the directory where the script is located
    Set-Location -Path $PSScriptRoot

    # Base URL for game assets
    $ASSETS_URL = "https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine"

    # Function to download file if missing
    function Download-IfMissing {
        param (
            [string]$ZipFile,
            [string]$RemoteFile
        )

        if (Test-Path $ZipFile) {
            Write-Host "✓ $ZipFile already exists. Skipping download."
        }
        else {
            Write-Host "↓ Downloading $ZipFile..."
            Invoke-WebRequest -Uri "$ASSETS_URL/$RemoteFile" -OutFile $ZipFile -UseBasicParsing
        }
    }

    # Download assets if missing
    Download-IfMissing -ZipFile "sf2.zip" -RemoteFile "sf2_images_0.1.zip"
    Download-IfMissing -ZipFile "mk2.zip" -RemoteFile "mk2_images_0.1.zip"
    Download-IfMissing -ZipFile "sfvsmk2.zip" -RemoteFile "sfvsmk2_images_0.1.zip"

    # Prompt user to select a game
    Write-Host "`n🎮 Choose a game to run:"
    Write-Host "1) Street Fighter 2 (sf2)"
    Write-Host "2) Mortal Kombat 2 (mk2)"
    Write-Host "3) SF vs MK 2 (sfvsmk2)"
    $choice = Read-Host "Enter your choice [1-3]"

    switch ($choice) {
        '1' { $GAME = "sf2" }
        '2' { $GAME = "mk2" }
        '3' { $GAME = "sfvsmk2" }
        default {
            Write-Host "❌ Invalid choice. Exiting."
            exit 1
        }
    }

    # Detect major Java version
    $javaVersionOutput = & java -version 2>&1 | Select-Object -First 1
    if ($javaVersionOutput -match 'version "(\d+)') {
        $JAVA_VERSION = [int]$matches[1]
        Write-Host "🧠 Detected Java version: $JAVA_VERSION"
    }
    else {
        Write-Host "⚠️ Could not detect Java version."
        $JAVA_VERSION = 0
    }

    $EXTRA_OPTS = ""
    if ($JAVA_VERSION -gt 9) {
        $EXTRA_OPTS = "--add-opens java.base/java.lang=ALL-UNNAMED"
    }

    $JAR = "$GAME.jar"
    Write-Host "`n🚀 Launching game with command:"
    Write-Host "java $EXTRA_OPTS -jar $JAR"

    # Run the game
    java $EXTRA_OPTS -jar $JAR
}
catch {
    Write-Host "`n❌ Error: $($_.Exception.Message)"
    if ($_.InvocationInfo) {
        Write-Host "📍 On line: $($_.InvocationInfo.ScriptLineNumber)"
    }
    else {
        Write-Host "📍 Error location could not be determined."
    }
    Read-Host "⏸ Press Enter to exit"
    exit 1
}
