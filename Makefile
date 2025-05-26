GOOGLE_ARCHIVE_BASE_URL := https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine

GAMES := sf2 mk2 sfvsmk2

assets/get:
	@for game in $(GAMES); do \
		zip_file=$$game.zip; \
		url=$(GOOGLE_ARCHIVE_BASE_URL)/$${game}_images_0.1.zip; \
		dir=org.jfge.games.$$game/assets; \
		echo "Downloading $$url..."; \
		curl -s -o $$zip_file $$url; \
		mkdir -p $$dir; \
		unzip -q $$zip_file -d $$dir; \
		rm -f $$zip_file; \
	done

assets/clean:
	@for game in $(GAMES); do \
		dir=org.jfge.games.$$game/assets; \
		echo "Cleaning $$dir..."; \
		rm -rf $$dir/*; \
	done

java/format:
	curl -L -o google-java-format.jar https://github.com/google/google-java-format/releases/download/v1.17.0/google-java-format-1.17.0-all-deps.jar
	find . -name "*.java" | xargs java -jar google-java-format.jar -r