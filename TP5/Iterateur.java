package TP5;

public interface Iterateur<T>{
    boolean aProchain();
    T prochain();
    void supprime();
}