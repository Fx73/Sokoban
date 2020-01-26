package Structures;

public interface FAP<T>{

    void Enfiler(T element, int priorite);
    T Defiler();
    boolean estVide();

}