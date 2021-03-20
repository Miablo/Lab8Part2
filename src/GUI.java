import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GUI extends JFrame implements ActionListener, ItemListener {
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

    public void createWindow() throws Exception {
        // Begin main window //
        this.window = (JPanel)this.getContentPane();
        this.window.setLayout(this.borderLayout1);
        this.setSize(new Dimension(508, 513));
        this.setTitle("FUNCTIONAL TESTING TOOL");
        // Begin select class tool bar //
        this.testClass.addActionListener(this::buildClassStr);
        this.testClass.setPreferredSize(new Dimension(201, 31));
        this.testClass.setText(" test.class");
        this.header.add(this.classLabel, null);
        this.header.add(this.testClass, null);
        this.classLabel.setText("  Tested Class: ");
        // Constructor left window view //
        this.leftPanel.setLayout(this.borderLayout3);
        this.leftPanel.setMinimumSize(new Dimension(220, 163));
        this.leftPanel.setPreferredSize(new Dimension(258, 163));
        this.leftPanel.add(this.constructView, "Center");
        this.leftPanel.add(this.constructToolbar, "North");
        // Button and Label
        this.objectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeObject(e);
            }
        });
        this.objectBtn.setText("New Object");
        this.construct.setText("Constructors");
        this.constructToolbar.add(this.construct, null);
        this.constructToolbar.add(this.objectBtn, null);
        this.constructView.getViewport().add(this.constructList, null);
        // Right Method run view //
        this.methodView.setPreferredSize(new Dimension(258, 150));
        this.methodView.getViewport().add(this.methodList, null);
        this.rightPanel.setLayout(this.borderLayout2);
        this.rightPanel.add(this.methodView, "Center");
        this.rightPanel.add(this.methodToolbar, "North");
        // Method Button and label
        this.mtdLabel.setRequestFocusEnabled(true);
        this.mtdLabel.setText("Methods");
        this.runBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runMethod(e);
            }
        });
        this.runBtn.setText("Run");
        this.methodToolbar.add(this.mtdLabel, null);
        this.methodToolbar.add(this.runBtn, null);
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
    }

    // if button is pressed
    public void actionPerformed(ActionEvent e)
    {
        //console.setText(e.getActionCommand() + " selected.");
    }

    // if combo box is selected
    public void itemStateChanged(ItemEvent e)
    {
      // console.setText(x.getSelectedItem() + " selected.");
    }

    private void buildClassStr(ActionEvent e) {
        try {
            System.out.println(testClass.getText());
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

            this.methodList.setListData(str);
            this.mtd = c.getDeclaredMethods();
            str = new String[this.mtd.length];

            for(i = 0; i < this.mtd.length; ++i) {
                cls = this.mtd[i].getParameterTypes();
                str[i] = this.mtd[i].getReturnType().getName() + " " + this.mtd[i].getName() + "(";

                getClassStr(str, i, cls);
            }

            this.constructList.setListData(str);
        } catch (Exception ex) {
            System.out.println(e);
        }

    }

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

   public Object[] makeObject(Class[] c) {

       Object[] obj = new Object[c.length];

       return obj;
   }

    void makeObject(ActionEvent e) {
        int idx = this.constructList.getSelectedIndex();
        Class[] c = this.cons[idx].getParameterTypes();
        Object[] obj = this.makeObject(c);

        try {
            this.obj_exe = this.cons[idx].newInstance(obj);
        } catch (Exception ex) {
            System.out.println(e);
        }

    }

    void runMethod(ActionEvent e) {
        int idx = this.methodList.getSelectedIndex();
        Class[] cls = this.mtd[idx].getParameterTypes();
        Object[] obj = this.makeObject(cls);


    }

}