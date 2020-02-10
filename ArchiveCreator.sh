#!/bin/bash

FOLDERS=(Global Structures TP1 TP6)
echo "Compilation des modules"
for folder in ${FOLDERS[*]}
do
 javac $folder/*.java
done

javac Sokoban.java

echo "Creation de l'archive"
jar cfe Sokoban.jar Sokoban *.class ${FOLDERS[*]}/*.class resources/* 

echo "Nettoyage"
find . -type f -name '*.class' -delete