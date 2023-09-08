#!/bin/bash


# Check if java is installed
if [ -z "$(which java)" ]; then
	echo "Java is not installed. Please install Java first."
	exit 1
fi


# Define the name of the target folder
target_folder="$HOME/simple-maze-game"

# Check if the target folder already exists
if [ -d "$target_folder" ]; then
    echo "The '$target_folder' folder already exists. Skipping the clone step."
else
    # Clone the repository
    git clone https://github.com/elyeandre/simple-maze-game "$target_folder"
    echo "Repository cloned successfully."
fi

# Navigate into the target folder
cd "$target_folder"

# Compile the Java classes
javac -d bin src/**/*.java
echo "Java classes compiled successfully."


# Navigate into the bin folder
cd bin

# Create a JAR file named mygame.jar
jar cvfm mygame.jar MANIFEST.MF .
echo "JAR file 'mygame.jar' created successfully."

# Run the game
java -jar mygame.jar

# Exit
exit 0

