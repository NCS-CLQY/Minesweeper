Brief explanation of the program design:

To create the classic minesweeper game, I have chosen to seperate it into the 2 different classes.

1) MinesweeperApp Class: This class will be responsible for the UI (user interface) of the Minesweeper game. It deals with the following:

Configuration - Size of the grid and number of mines.
Interaction - Users will select squares that the wish to uncover.
Conclusion - If a player wins/loses, they will be given the display message accordingly.

2) MinesweeperGame Class: The class is the backend that encapsulates all game rules, which includes creating a grid, locating mines, revealing boxes, calculating the number of nearby mines, determining whether one has won or lost and providing access methods (such as getGrid() and getMines() etc). This will help to manage the logic and state of the program.

-----------------------

Instructions to compile and run the program:

Environment required to run the program: Windows.

*Install JDK: Make sure you have the JDK installed on your system. You can download it from the official Oracle website or use an alternative like OpenJDK.

Save the following files MinesweeperApp.java and MinesweeperGame.java in any of the folder on your computer.
Open a command prompt/CMD (preferably in Administrator mode). You can type CMD in the search bar and right click > Run as Administrator.
Navigate to the directory where you saved all of the files. (You can use DIR to double check the files that are in the directory).

Example - cd C:\Users\<User>\Desktop\Minesweeper

To compile:
javac MinesweeperGame.java MinesweeperApp.java

If you meet this compilation error:'javac' is not recognized as an internal or external command, operable program or batch file.exists>, it means that the compiler is not found in the system path.

Find the bin from the jdk you installed, should be in C:\Program Files or C:\Program Files (x86). That is the path to be used.

You can fix it by specifying the path required by entering the following command in the terminal: 
set PATH=C:\Program Files\Java\<Your jdk version>\bin 
Example: set PATH=C:\Program Files\Java\jdk1.8.0_202\bin 

To run the program:
java MinesweeperApp

Have fun with Minesweeper!



