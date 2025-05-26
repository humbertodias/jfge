#!/bin/bash

# Check if required commands are installed
for cmd in curl unzip mkdir; do
  if ! command -v "$cmd" >/dev/null 2>&1; then
    echo "Error: '$cmd' is not installed or not in your PATH."
    exit 1
  fi
done

# Define the games and base URL
curl -ks -o sf2.zip https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine/sf2_images_0.1.zip
unzip -oq sf2.zip -d "org.jfge.games.sf2/assets"
rm -f sf2.zip

curl -ks -o mk2.zip https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine/mk2_images_0.1.zip
unzip -oq mk2.zip -d "org.jfge.games.mk2/assets"
rm -f mk2.zip

curl -ks -o sfvsmk2.zip https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine/sfvsmk2_images_0.1.zip
unzip -oq sfvsmk2.zip -d "org.jfge.games.sfvsmk2/assets"
rm -f sfvsmk2.zip