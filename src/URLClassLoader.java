import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class URLClassLoader extends ClassLoader {
    File f = null;
    JFileChooser jfc = new JFileChooser(this.testClass.getText());
    jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        f = jfc.getSelectedFile();
    }
    String fileName = f.getName().split("\\.")[0]; // removing the ".class" suffix
    String parentFileName = f.getParentFile().getName();
    URL url;

    {
        try {
            url = f.getParentFile().getParentFile().toURI().toURL();
            URL[] urla = {url};
            URLClassLoader ucl = new URLClassLoader(urla);
            Class c = Class.forName(parentFileName + "." + fileName, true, ucl);
            System.out.println("First time: " + c.newInstance());

            c = Class.forName(fileName, true, ucl);
            System.out.println("Second time: " + c.newInstance());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

