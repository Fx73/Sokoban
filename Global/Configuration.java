package Global;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import Structures.*;


public final class Configuration {
    static volatile Configuration instance = null;
    public static Configuration instance(){
        if(instance == null)
            instance = new Configuration();
        return instance;
    }
    private Configuration(){
        super();
    }

    static String sequenceImplement = "Liste";




    public static Object Lis(String S) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for(Field field : fields){
            if(S.equals(field.getName()))
                try{return field.get(instance);}
                catch(Exception e){throw e;}
        }
        throw new NoSuchElementException("Element de configuration non trouvé");
    }


    public <T> Sequence<T> FabriqueSequence(){
        if(sequenceImplement.equals("Liste")) {
            return new SequenceListe<T>();
          } 
          else if(sequenceImplement.equals("Tableau")) {
            return new SequenceTableau<T>();
          } 
          System.out.println("Impossible de créer une sequence " + sequenceImplement);
          return null;
    }

}