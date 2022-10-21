How to run the game in intellij:
  1. Compile game into jar file, this can be done in intellij by going to the maven bar on the right and going into Maven settings, symbolised by a wrench, going into the sub section "Runner" and activating "Delegate IDE build/run actions to Maven", then building using the standard intellij build button.
  2. Open the built jar file path in a terminal, this can be done by right clicking the file "topdownshooter-1.0.0.jar" that should appear in the "target" folder after building, clicking "Open in" -> Terminal and then inputting "java -jar topdownshooter-1.0.0.jar" into the terminal.

How to run the game in a terminal:
  1. Open a terminal with the targeted directory being the outer most folder of the game (in which you should see a file named pom.xml).
  2. Type these commands in sequence:
    A. mvn clean
    B. mvn package
  3. Then open a terminal with the targeted directory being the "target" folder inside of the outer folder and type in "java -jar topdownshooter-1.0.0.jar"

recomend using Java 18 or 19 as we know they works
