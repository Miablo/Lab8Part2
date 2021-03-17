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
        gui.setVisible(true);

        try {
            // Loop through arguments passed through CLI
            for (String arg : args) {
                Object c = Class.forName(arg).getConstructor().newInstance();
                int m = c.getClass().getModifiers();

                ClassComponents.getClass(c, m);
                ClassComponents.getClassFields(c);
                ClassComponents.getConstructor(c);
                ClassComponents.getMethodFields(c);

                System.out.println( "} ");
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }


}
