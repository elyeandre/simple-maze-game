## How to Run This Game

To run this game, follow these steps:


1. Ensure that you have Java installed on your system. To check whether Java is installed, use the following command:

    ```
    java --version
    ```
    
2. Clone this repository by executing the following command:

    ```
    git clone https://github.com/elyeandre/SimpleMazeGame
    ```

3. Inside the SimpleMazeGame folder, compile all the java files located in the src directory. This will place the compiled files into the bin folder:

    ```
    javac -d bin src/*.java
    ```

3. Navigate to the `bin` folder:

    ```
    cd bin
    ```

4. Execute the following command to create a JAR file named `mygame.jar` using the `MANIFEST.MF` file:

    ```
    jar cvfm mygame.jar MANIFEST.MF .
    ```

5. You can now run the game using the following command:

    ```
    java -jar mygame.jar
    ```


