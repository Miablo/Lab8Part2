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
    JToolBar header = new JToolBar(); // top toolbar showing current open class

    BorderLayout borderLayout1 = new BorderLayout();
    BorderLayout borderLayout3 = new BorderLayout();
    BorderLayout borderLayout2 = new BorderLayout();

    Method[] mtd;
    Constructor[] cons;
    Object obj_exe;

    JList constructList = new JList();
    JList methodList = new JList();

    JTextField testClass = new JTextField();

    JSplitPane methodPane = new JSplitPane();

    JScrollPane methodView = new JScrollPane();
    JScrollPane constructView = new JScrollPane();

    JPanel leftPanel = new JPanel();
    JPanel constructToolbar = new JPanel();

    JButton objectBtn = new JButton();
    JLabel construct = new JLabel();

    JPanel rightPanel = new JPanel();

    JPanel methodToolbar = new JPanel();
    JLabel mtdLabel = new JLabel();
    JButton runBtn = new JButton();

    JLabel classLabel = new JLabel();

    JScrollPane consoleView = new JScrollPane();
    JTextArea console = new JTextArea();

    public void createWindow() throws Exception {
        // Begin main window //
        this.window = (JPanel)this.getContentPane();
        this.window.setLayout(this.borderLayout1);
        this.setSize(new Dimension(508, 513));
        this.setTitle("FUNCTIONAL TESTING TOOL");
        // Begin select class tool bar //
        this.testClass.setMaximumSize(new Dimension(201, 2147483647));
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
        //l.setText(e.getActionCommand() + " selected.");
    }

    // if combo box is selected
    public void itemStateChanged(ItemEvent e)
    {
      //  l1.setText(x.getSelectedItem() + " selected.");
    }

}
