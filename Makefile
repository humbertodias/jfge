GOOGLE_ARCHIVE_BASE_URL = https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine

GAMES = sf2 mk2 sfvsmk2

ASSETS_DIR = org.jfge.games
JAVA_FORMAT_VERSION = 1.17.0
JAVA_FORMAT_JAR = google-java-format-$(JAVA_FORMAT_VERSION)-all-deps.jar
JAVA_FORMAT_URL = https://github.com/google/google-java-format/releases/download/v$(JAVA_FORMAT_VERSION)/$(JAVA_FORMAT_JAR)

DEL = rm -f
MKDIR = mkdir -p
RMDIR = rm -rf
CURL = curl -s -L -o
UNZIP = unzip -q
NULLDEV = /dev/null

assets/get:
	@for game in $(GAMES); do \
		zip_file=$$game.zip; \
		url=$(GOOGLE_ARCHIVE_BASE_URL)/$${game}_images_0.1.zip; \
		dir=$(ASSETS_DIR)/$$game/assets; \
		echo "Downloading $$url..."; \
		$(CURL) $$zip_file $$url; \
		$(MKDIR) $$dir; \
		$(UNZIP) $$zip_file -DestinationPath $$dir 2> $(NULLDEV) || $(UNZIP) $$zip_file -d $$dir; \
		$(DEL) $$zip_file; \
	done

assets/clean:
	@for game in $(GAMES); do \
		dir=$(ASSETS_DIR)/$$game/assets; \
		echo "Cleaning $$dir..."; \
		$(RMDIR) $$dir/* 2> $(NULLDEV) || true; \
	done

java/format:
	$(CURL) $(JAVA_FORMAT_JAR) $(JAVA_FORMAT_URL)
	find . -name "*.java" | xargs java -jar $(JAVA_FORMAT_JAR) -r
