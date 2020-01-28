#!bin/sh

javac Sokoban.java
javac Global/*.java
javac Structures/*.java
javac TP3/Properties.java


jar cfe Sokoban.jar Sokoban *.class Global/*.class Structures/*.class rsc/* TP3/Properties.class

find . -type f -name '*.class' -delete