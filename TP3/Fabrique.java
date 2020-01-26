package TP3;

import TP5.*;

public class Fabrique<T> {

    public Sequence<T> sequence(String type)  {
        if(type.equals("liste")) {
          return new SequenceListe<>();
        } 
        else if(type.equals("tableau")) {
          return new SequenceTableau<>();
        } 
        System.out.println("Impossible de créer une sequence " + type);
        return null;
      }

      public FAP<T> fap(String type) {
        if(type.equals("liste")) {
          return new FAPListe<>();
        } 
        else if(type.equals("tableau")) {
          return new FAPTableau<>();
        } 
        System.out.println("Impossible de créer une FAP " + type);
        return null;
      }

}