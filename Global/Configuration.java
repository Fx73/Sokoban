package Global;

import java.lang.reflect.Field;
import java.lang.ClassLoader;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.FileReader;

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



    /** Liste des paramètres configurables et sauvegardable
     *  A faire correspondre avec la class Properties pour la sauvegarde
     */
    private static String Sequence = "Liste";
    private static String LogLevel = "WARNING";
    private static Boolean Maximized = false;
    private static Integer NiveauCourant = 0;
    private static Boolean Animations = true;


    /**
    */


    public static Boolean Ecris(String S, Object valeur) {
        Field[] fields = instance().getClass().getDeclaredFields();
        for(Field field : fields){
            if(S.equals(field.getName())){
                try{field.set(instance,valeur);}
                catch(Exception e){
                    try{field.set(instance,Boolean.parseBoolean(valeur.toString()));}
                    catch(Exception e2){
                        try{field.set(instance,Integer.parseInt(valeur.toString()));}
                        catch(Exception e3){
                            return false;
                        }
                    }
                }
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

    public static InputStream charge(String path){
        InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
        return stream;
    }

}