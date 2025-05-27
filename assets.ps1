# Check if curl is available
if (-not (Get-Command curl -ErrorAction SilentlyContinue)) {
    Write-Error "Error: 'curl' is not installed or not in your PATH."
    exit 1
}

# Check if Expand-Archive (built-in unzip) is available
if (-not (Get-Command Expand-Archive -ErrorAction SilentlyContinue)) {
    Write-Error "Error: 'Expand-Archive' cmdlet is not available. Requires PowerShell 5.0+."
    exit 1
}

# Define function to download and optionally extract zip
function Download-And-Extract {
    param (
        [string]$url,
        [string]$destDir,
        [string]$zipFile,
        [bool]$uncompress
    )

    Write-Host "Downloading $url ..."
    curl -k -o $zipFile $url

    if ($uncompress) {
        if (-Not (Test-Path $destDir)) {
            New-Item -ItemType Directory -Path $destDir | Out-Null
        }

        Write-Host "Extracting $zipFile to $destDir ..."
        Expand-Archive -Path $zipFile -DestinationPath $destDir -Force
    } else {
        Write-Host "Skipping extraction of $zipFile"
    }
}

# URLs and paths
$baseUrl = "https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine"
$games = @{
    "sf2"     = "org.jfge.games.sf2/assets"
    "mk2"     = "org.jfge.games.mk2/assets"
    "sfvsmk2" = "org.jfge.games.sfvsmk2/assets"
}

foreach ($game in $games.Keys) {
    $url = "$baseUrl/${game}_images_0.1.zip"
    $destDir = $games[$game]
    $zipFile = "$game.zip"

    Download-And-Extract -url $url -destDir $destDir -zipFile $zipFile -uncompress:$false
}