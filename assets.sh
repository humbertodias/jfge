GOOGLE_ARCHIVE_BASE_URL=https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/java-fighting-game-engine
ZIP=sf2.zip
[ -f $ZIP ] || curl -k -o $ZIP $GOOGLE_ARCHIVE_BASE_URL/sf2_images_0.1.zip
ZIP=mk2.zip
[ -f $ZIP ] || curl -k -o $ZIP $GOOGLE_ARCHIVE_BASE_URL/mk2_images_0.1.zip
ZIP=sfvsmk2.zip
[ -f ${ZIP} ] || curl -k -o $ZIP $GOOGLE_ARCHIVE_BASE_URL/sfvsmk2_images_0.1.zip
