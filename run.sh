#!/bin/bash
trap 'echo -e "\n❌ Error on line $LINENO. Pausing..."; read -p "Press Enter to exit." dummy' ERR
set -e  # Exit immediately if any command fails

# Move to the directory where the script is located
cd "$(dirname "$(realpath "$0")")"

# Base URL for game assets
ASSETS_URL="https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine"

# Function to download a file if it's missing
download_if_missing() {
  local zip_file=$1
  local remote_file=$2
  if [[ -f "$zip_file" ]]; then
    echo "✓ $zip_file already exists. Skipping download."
  else
    echo "↓ Downloading $zip_file..."
    curl -k -L -o "$zip_file" "$ASSETS_URL/$remote_file"
  fi
}

# Download necessary assets
download_if_missing "sf2.zip"      "sf2_images_0.1.zip"
download_if_missing "mk2.zip"      "mk2_images_0.1.zip"
download_if_missing "sfvsmk2.zip"  "sfvsmk2_images_0.1.zip"

# Prompt user to select a game
echo -e "\n🎮 Choose a game to run:"
echo "1) Street Fighter 2 (sf2)"
echo "2) Mortal Kombat 2 (mk2)"
echo "3) SF vs MK 2 (sfvsmk2)"
read -p "Enter your choice [1-3]: " choice

# Determine the JAR to run based on user input
case "$choice" in
  1)
    GAME="sf2"
    ;;
  2)
    GAME="mk2"
    ;;
  3)
    GAME="sfvsmk2"
    ;;
  *)
    echo "❌ Invalid choice. Exiting."
    exit 1
    ;;
esac

# Detect major Java version (e.g., 8, 11, 17)
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | sed -E 's/.*version "([0-9]+).*/\1/')
echo "🧠 Detected Java version: $JAVA_VERSION"

EXTRA_OPTS=""
if [[ "$JAVA_VERSION" -gt 9 ]]; then
  EXTRA_OPTS+=" --add-opens java.base/java.lang=ALL-UNNAMED"
fi

# Run the game
JAR="$GAME.jar"
echo -e "\n🚀 Launching game with command:"
echo "java $EXTRA_OPTS -jar $JAR"
java $EXTRA_OPTS -jar "$JAR"