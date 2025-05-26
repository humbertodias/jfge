#!/bin/bash

GAMES="sf2 mk2 sfvsmk2"
BASE_URL="https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine"

for game in $GAMES; do
  zip_file="$game.zip"
  url="$BASE_URL/${game}_images_0.1.zip"
  dir="org.jfge.games.$game/assets"
  echo "Downloading $url..."
  curl -s -o "$zip_file" "$url"
  mkdir -p "$dir"
  unzip -q "$zip_file" -d "$dir"
  rm -f "$zip_file"
done