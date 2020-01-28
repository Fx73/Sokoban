package Global;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private static String Sequence = "Liste";
    private static String LogLevel = "WARNING";

    public static Boolean Ecris(String S, String valeur) {
        Field[] fields = instance().getClass().getDeclaredFields();
        for(Field field : fields){
            if(S.equals(field.getName())){
                try{field.set(instance,valeur);}
                catch(Exception e){return false;}
                return true;
            }
        }
        throw new NoSuchElementException("Element de configuration non trouvé");

    }

    public static Object Lis(String S)  {
        Field[] fields = instance().getClass().getDeclaredFields();
        for(Field field : fields){
            if(S.equals(field.getName()))
                try{return field.get(instance);}
                catch(Exception e){Tools.Print(e);}
        }
        throw new NoSuchElementException("Element de configuration non trouvé");
    }


    public <T> Sequence<T> FabriqueSequence(){
        if(Sequence.equals("Liste")) {
            return new SequenceListe<T>();
          } 
          else if(Sequence.equals("Tableau")) {
            return new SequenceTableau<T>();
          } 
          System.out.println("Impossible de créer une sequence " + Sequence);
          return null;
    }

    public static Logger logger (){
        Logger l = Logger.getLogger("SokobanLogger");
        l.setLevel((Level.parse(LogLevel)));
        return l;

    }

}