import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 *
 */
public class GUI extends JFrame implements ActionListener {
    Object createObj;
    JPanel window; // main window
    // scroll view areas for method,construct,and console
    JSplitPane methodPane = new JSplitPane();
    JScrollPane methodView = new JScrollPane();
    JScrollPane constructView = new JScrollPane();
    JScrollPane consoleView = new JScrollPane();
    // panels
    JPanel leftPanel = new JPanel();
    JPanel constructToolbar = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel methodToolbar = new JPanel();
    JToolBar header = new JToolBar(); // top toolbar showing current open class
    // layouts
    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();
    BorderLayout borderLayout3 = new BorderLayout();

    Method[] mtd;
    Constructor[] cons;
    Object obj_exe;

    JList constructList = new JList();
    JList methodList = new JList();

    // Object Button
    JButton objectBtn = new JButton();
    JLabel construct = new JLabel();
    // Method run button
    JLabel mtdLabel = new JLabel();
    JButton runBtn = new JButton();
    // class testing name field + label
    JLabel classLabel = new JLabel();
    JTextField testClass = new JTextField();
    // Bottom area console output area
    JTextArea console = new JTextArea();

    public void createWindow(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        // Begin main window //
        this.window = (JPanel)this.getContentPane();
        this.window.setLayout(this.borderLayout1);
        this.setSize(new Dimension(508, 513));
        this.setTitle("FUNCTIONAL TESTING TOOL");
        // Begin select class tool bar //
        this.testClass.setPreferredSize(new Dimension(201, 31));

        this.header.add(this.classLabel, (Object)null);
        this.header.add(this.testClass, (Object)null);
        this.classLabel.setText("  Tested Class: ");
        // Constructor left window view //
        this.leftPanel.setLayout(this.borderLayout3);
        this.leftPanel.setMinimumSize(new Dimension(220, 163));
        this.leftPanel.setPreferredSize(new Dimension(258, 163));
        this.leftPanel.add(this.constructView, "Center");
        this.leftPanel.add(this.constructToolbar, "North");
        // Button and Label
        this.objectBtn.addActionListener(e -> {
            try {
                newObject(e);
            } catch (ClassNotFoundException | NoSuchMethodException |
                    InvocationTargetException | InstantiationException |
                    IllegalAccessException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        this.objectBtn.setText("New Object");
        this.construct.setText("Constructors");
        this.constructToolbar.add(this.construct, (Object)null);
        this.constructToolbar.add(this.objectBtn, (Object)null);
        this.constructView.getViewport().add(this.constructList, (Object)null);
        // Right Method run view //
        this.methodView.setPreferredSize(new Dimension(258, 150));
        this.methodView.getViewport().add(this.methodList, (Object)null);
        this.rightPanel.setLayout(this.borderLayout2);
        this.rightPanel.add(this.methodView, "Center");
        this.rightPanel.add(this.methodToolbar, "North");
        // Method Button and label
        this.mtdLabel.setRequestFocusEnabled(true);
        this.mtdLabel.setText("Methods");
        // run clicked call run method
        this.runBtn.addActionListener(e -> {
            try {
                runMethod(e);
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                    InstantiationException | IllegalAccessException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        this.runBtn.setText("Run");
        // add button and label to panel
        this.methodToolbar.add(this.mtdLabel, (Object)null);
        this.methodToolbar.add(this.runBtn, (Object)null);
        this.methodPane.add(this.leftPanel, "left");
        this.methodPane.add(this.rightPanel, "right");
        // add views to window //
        this.window.add(this.methodPane, "Center");
        this.window.add(this.header, "North");
        this.window.add(this.consoleView, "South");
        // Create output console on bottom //
        this.console.setText("");
        this.consoleView.setMinimumSize(new Dimension(19, 90));
        this.consoleView.setPreferredSize(new Dimension(2, 90));
        this.consoleView.getViewport().add(this.console);
        // allow x click to exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildClassStr(args);
    }

    /**
     *
     * @param args from main function passed args
     * @throws ClassNotFoundException to determine if class is found
     * @throws NoSuchMethodException determine if method is found
     * @throws IllegalAccessException exception if illegal call is made
     * @throws InvocationTargetException cannot invoke method
     * @throws InstantiationException obj issue
     */
    private void buildClassStr(String[] args) throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (String arg : args) {
            Object o = Class.forName(arg).getConstructor().newInstance();
            int m = o.getClass().getModifiers();
            try {
                testClass.setText(arg);
               // System.out.println(testClass.getText());
                Class c = Class.forName(testClass.getText());
                this.cons = c.getConstructors();
                String[] str = new String[this.cons.length];

                int i;
                Class[] cls;
                for(i = 0; i < this.cons.length; ++i) {
                    cls = this.cons[i].getParameterTypes();
                    str[i] = this.cons[i].getName() + "(";

                    getClassStr(str, i, cls);
                }
                this.constructList.setListData(str);


                this.mtd = c.getDeclaredMethods();
                str = new String[this.mtd.length];

                for(i = 0; i < this.mtd.length; ++i) {
                    cls = this.mtd[i].getParameterTypes();
                    str[i] = this.mtd[i].getReturnType().getName() + " " + this.mtd[i].getName() + "(";

                    getClassStr(str, i, cls);
                }

                this.methodList.setListData(str);

            } catch (Exception ex) {
                System.out.println(ex);
            }

        }


    }

    /**
     * Class used to create a string to represent
     * methods and constructors in panels
     *
     * @param str string from arg
     * @param i modifier
     * @param cls class array
     */
    private void getClassStr(String[] str, int i, Class[] cls) {
        int j;
        for(j = 0; j < cls.length; ++j) {
            if (j == cls.length - 1) {
                str[i] = str[i] + cls[j].getName();
            } else {
                str[i] = str[i] + cls[j].getName() + ",";
            }
        }

        str[i] = str[i] + ")";
    }

   public Object[] makeObj(Class[] c) throws ClassNotFoundException, NoSuchMethodException,
           IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("This is in the make object method");
       Object[] object = new Object[c.length];

       return object;
   }

    void newObject(ActionEvent e) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int idx = this.constructList.getSelectedIndex();
        Class[] c = this.cons[idx].getParameterTypes();
        Object[] obj = this.makeObj(c);

        try {
            this.createObj = this.cons[idx].newInstance(obj);
        } catch (Exception ex) {
            System.out.println(e);
        }

    }

    /**
     *
     * @param e action event passed --btn clk
     * @throws ClassNotFoundException class not found
     * @throws NoSuchMethodException method not found
     * @throws InvocationTargetException cannot invoke method
     * @throws InstantiationException cannot create obj
     * @throws IllegalAccessException cannot access param
     */
    void runMethod(ActionEvent e) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {

        // use obj created
        // find method selected
        // run method selected
        // if method has parameters need to ask for vals
        // created a jdialog for on click
        // take those values in and create the method call
        // use all input values
        int idx = this.methodList.getSelectedIndex();
        Class[] cls = this.mtd[idx].getParameterTypes();
        System.out.println("This is in the runMethod");
        Object[] obj = this.makeObj(cls);

        Object result = this.mtd[idx].invoke(this.createObj, obj);
        if(result != null){
            this.console.append(result.toString() + "\n");
        } else {
            this.console.append("null\n");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {// not used
    }
}