#!/bin/bash

# Check if required commands are installed
for cmd in curl unzip mkdir; do
  if ! command -v "$cmd" >/dev/null 2>&1; then
    echo "Error: '$cmd' is not installed or not in your PATH."
    exit 1
  fi
done

# Define the games and base URL
GAMES="sf2 mk2 sfvsmk2"
BASE_URL="https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine"

# Download and extract each game's assets
for game in $GAMES; do
  zip_file="$game.zip"
  url="$BASE_URL/${game}_images_0.1.zip"
  dir="org.jfge.games.$game/assets"
  echo "Downloading $url..."
  curl -s -o "$zip_file" "$url"
  mkdir -p "$dir"
  unzip -oq "$zip_file" -d "$dir"
  rm -f "$zip_file"
done
