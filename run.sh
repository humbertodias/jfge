#!/bin/bash

echo "Choose a game to run:"
echo "1) Street Fighter 2 (sf2)"
echo "2) Mortal Kombat 2 (mk2)"
echo "3) SF vs MK 2 (sfvsmk2)"
read -p "Enter your choice [1-3]: " choice

case "$choice" in
  1)
    JAR="org.jfge.games.sf2/target/sf2-0.0.1-SNAPSHOT.jar"
    MAIN_CLASS="org.jfge.games.sf2.game.StreetFighter2"
    ;;
  2)
    JAR="org.jfge.games.mk2/target/mk2-0.0.1-SNAPSHOT.jar"
    MAIN_CLASS="org.jfge.games.mk2.game.MortalKombat2"
    ;;
  3)
    JAR="org.jfge.games.sfvsmk2/target/sfvsmk2-0.0.1-SNAPSHOT.jar"
    MAIN_CLASS="org.jfge.games.sfvsmk2.game.SFVSMK2"
    ;;
  *)
    echo "Invalid choice. Exiting."
    exit 1
    ;;
esac

# Get major Java version as integer, compatible with macOS
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | sed -E 's/.*version "([0-9]+).*/\1/')
echo "Detected Java version: $JAVA_VERSION"

EXTRA_OPTS="-classpath .:mk2.zip:sf2.zip:sfvsmk2.zip:$JAR"

if [ "$JAVA_VERSION" -gt 9 ]; then
  EXTRA_OPTS+=" --add-opens java.base/java.lang=ALL-UNNAMED"
fi

echo "Running: java $EXTRA_OPTS -jar $JAR"
java $EXTRA_OPTS $MAIN_CLASS
