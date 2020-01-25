package TP2;

import TP4.Iterateur;

public interface Sequence{

    void insereTete(int element);
    void insereQueue(int element);
    int extraitTete();
    boolean estVide();

    Iterateur iterateur();

}