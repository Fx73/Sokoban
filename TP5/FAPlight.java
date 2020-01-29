package TP5;

public interface FAPlight<T extends Comparable<T>> {

    void Enfiler(T element);
    T Defiler();
    boolean estVide();

}