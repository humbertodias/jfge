#!/bin/bash

# Check if required commands are installed
for cmd in curl unzip mkdir; do
  if ! command -v "$cmd" >/dev/null 2>&1; then
    echo "Error: '$cmd' is not installed or not in your PATH."
    exit 1
  fi
done

# Function to download and optionally unzip
download_and_extract() {
  local url="$1"
  local zip_name="$2"
  local target_dir="$3"
  local uncompress="$4"

  echo "Downloading $zip_name..."
  if curl -k -o "$zip_name" "$url"; then
    echo "Downloaded $zip_name successfully."
    if [ "$uncompress" = true ]; then
      echo "Unzipping $zip_name to $target_dir..."
      mkdir -p "$target_dir"
      unzip -o "$zip_name" -d "$target_dir"
    else
      echo "Skipping unzipping $zip_name"
    fi
  else
    echo "Failed to download $zip_name from $url"
  fi
}

# Set this to true or false based on your need
UNCOMPRESS=false

# Downloads
download_and_extract \
  "https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine/sf2_images_0.1.zip" \
  "sf2.zip" \
  "org.jfge.games.sf2/assets" \
  "$UNCOMPRESS"

download_and_extract \
  "https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine/mk2_images_0.1.zip" \
  "mk2.zip" \
  "org.jfge.games.mk2/assets" \
  "$UNCOMPRESS"

download_and_extract \
  "https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine/sfvsmk2_images_0.1.zip" \
  "sfvsmk2.zip" \
  "org.jfge.games.sfvsmk2/assets" \
  "$UNCOMPRESS"
