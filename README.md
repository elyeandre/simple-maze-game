
<p align="left">
<img  alt="screenshot" width="500" src="https://github.com/elyeandre/SimpleMazeGame/blob/11fed763cf14c6d31fc77fc247dad53cc2e90a73/screenshot.png">
</p>

## How to Run This Game

To run this game, follow these steps:


1. Ensure that you have Java installed on your system. To check whether Java is installed, use the following command:

    ```
    java --version
    ```
    
2. Clone this repository by executing the following command:

    ```
    git clone https://github.com/elyeandre/simple-maze-game
    ```

3. Inside the simple-maze-game folder, compile all the classes located in the src directory. This will place the compiled files into the bin folder:

    ```
    cd simple-maze-game
    ```
    ```
    javac -d bin src/**/*.java
    ```

4. Inside of bin folder, execute the following command to create a JAR file named `mygame.jar`:
    ```
    cd bin
    ```
    ```
    jar cvfm mygame.jar MANIFEST.MF .
    ```

5. You can now run the game using the following command:

    ```
    java -jar mygame.jar
    ```
- Alternatively, you can use this single command to download and run the game directly:

     ```
     wget -qO- https://github.com/elyeandre/simple-maze-game/blob/022e82e660a9258feaba51a01c631e401d01df96/install_game.sh | bash
     ```


