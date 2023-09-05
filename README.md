## How to Run This Game

To run this game, follow these steps:

1. Inside the `SimpleMazeGame` folder, execute the following command to compile all the classes inside the `src` directory and place the compiled files in the `bin` folder:

    ```
    javac -d bin src/*.java
    ```

2. Navigate to the `bin` folder:

    ```
    cd bin
    ```

   Execute the following command to create a JAR file named `mygame.jar` using the `MANIFEST.MF` file:

    ```
    jar cvfm mygame.jar MANIFEST.MF .
    ```

3. You can now run the game using the following command:

    ```
    java -jar mygame.jar
    ```

Make sure you have Java installed on your system before running the game.
