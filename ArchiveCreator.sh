#!/bin/bash

FOLDERS=(Global Structures)
echo "Compilation des modules"
for folder in ${FOLDERS[*]}
do
 javac $folder/*.java
done

javac Sokoban.java
javac TP3/Properties.java


echo "Creation de l'archive"
jar cfe Sokoban.jar Sokoban *.class ${FOLDERS[*]}/*.class rsc/* TP3/Properties.class

echo "Nettoyage"
find . -type f -name '*.class' -delete