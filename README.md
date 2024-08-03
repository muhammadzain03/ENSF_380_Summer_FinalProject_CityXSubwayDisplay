# ENSF_380_Summer_FinalProject_CityXSubwayDisplay

To run the project from cmd: 
    1) Go to SubwayScreen Folder:
    2) Compile all files using lib folder:
        javac -cp "lib/*" -d bin src/ca/ucalgary/edu/ensf380/*.java src/ca/ucalgary/edu/ensf380/controller/*.java src/ca/ucalgary/edu/ensf380/model/*.java src/ca/ucalgary/edu/ensf380/util/*.java src/ca/ucalgary/edu/ensf380/view/*.java

    3) Run the files:
        java -cp "bin;lib/*;exe/SubwaySimulator.jar" ca.ucalgary.edu.ensf380.view.SubwayScreenApp T1 red

        T1 red are command line arguments
