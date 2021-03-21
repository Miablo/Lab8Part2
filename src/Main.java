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
        gui.createWindow(args);
        gui.setVisible(true);

    }


}
