# Train Simulator

A Java program that models the movement of 12 trains on 3 subway lines. Here's an outline of what the program will do:

1. Parse the CSV file and store the station information in data structures that make it easy to access.
2. Initialize the positions of the 12 trains on the subway lines, making sure to keep 4 stations distance between them.
3. Simulate the movement of the trains every 15 seconds by updating their positions based on their current direction and speed.
4. Print out the current positions of the trains on the subway lines to the console and output folder. 

## How to run?

java -jar .\exe\SubwaySimulator.jar --in "..\data\subway.csv" --out "..\out"

# ==============================================================================================================
# Absolute Path Verification:

    To troubleshoot, try using an absolute path instead of a relative one to ensure the program can find the file. For example:

        java -jar .\exe\SubwaySimulator.jar --in "C:\Zain\University of Calgary\Summer 2024\ENSF-380 - Object Oriented Principles for Software Development\Final Project\SubwayScreen\data\subway.csv" --out "C:\Zain\University of Calgary\Summer 2024\ENSF-380 - Object Oriented Principles for Software Development\Final Project\SubwayScreen\out"
