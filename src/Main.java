import java.awt.*;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Main class
 * program driver - calls main method
 *
 * @version 1.0
 * @author Mio Diaz, Cody Walker
 *
 */
public class Main {
    /**
     * Main method
     *
     * @param args Name of classes object
     */
    public static void main(String[] args) throws Exception {
        GUI gui = new GUI();
        gui.createWindow();

        try {
            // Loop through arguments passed through CLI
            for (String arg : args) {
                Object c = Class.forName(arg).getConstructor().newInstance();
                int m = c.getClass().getModifiers();

                getClass(c, m);
                getClassFields(c);
                getConstructor(c);
                getMethodFields(c);

                System.out.println( "} ");
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * Print superclass name
     *
     * @param c class
     * @param mod modifier value
     */
    public static void getClass(Object c, int mod){
        System.out.println(Modifier.toString(mod) + " class "
                + c.getClass().getName() + " { ");  //add protection type
    }

    /**
     * Gets field data type and name
     *
     * @param c class object
     */
    public static void getClassFields(Object c){
        Field[] f =c.getClass().getDeclaredFields();
        for(Field fs:f){
            System.out.println(fs.getType() + " " + fs.getName() +"; "); // add protection type
        }
    }

    /**
     * Finds and prints constructors
     * adds modifier
     *
     * @param c class object
     */
    public static void getConstructor(Object c){
        for(Constructor cons: c.getClass().getConstructors()){
            System.out.print(Modifier.toString(cons.getModifiers())
                    + " "  + cons.getName() + "("); //add protection type

            for(Object cla : cons.getParameterTypes()){
                System.out.print(Modifier.toString(cons.getModifiers())
                        + " " + cla.getClass().getName() + " "); // add field types and name
            }
            System.out.print(");\n");
        }
    }

    /**
     * Prints methods from class
     * includes modifier, param and return type
     *
     * @param c class object
     */
    public static void getMethodFields(Object c){
        // Prints method and method field parameter type
        for(Method i : c.getClass().getDeclaredMethods()){
            System.out.print(Modifier.toString(i.getModifiers()) + " " + i.getAnnotatedReturnType() + " "
                    + i.getName() + " ( " + Arrays.toString(i.getParameters()) + " " + " )");
            getThrowException(c);
            System.out.print("\n");
        }
    }

    /**
     * Prints exceptions thrown by methods
     *
     * @param c class object
     */
    public static void getThrowException(Object c){
        for(Method i : c.getClass().getDeclaredMethods()){
            System.out.print(Arrays.toString(i.getExceptionTypes()));
        }
    }


}
