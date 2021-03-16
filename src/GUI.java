import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    // create swing window
    // make window jpanel
    // create two sides
    // create two windows
    // create a file chooser jfile

    static JFrame window;
    static JTextArea t1, t2;
    JMenuBar jMenu1 = new JMenuBar();
    JMenu MenuFile = new JMenu();
    JMenuItem MenuExit = new JMenuItem();
    JToolBar toolbar = new JToolBar();
    JList list1 = new JList();
    JScrollPane scrollPane = new JScrollPane();

    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JButton jButton3 = new JButton();


    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JSplitPane pane = new JSplitPane
            (JSplitPane.HORIZONTAL_SPLIT, leftPanel,rightPanel);
    JSplitPane top = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    JSplitPane bottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

    Button construct;
    Button methodRunner;

    public void createWindow() throws Exception {
        pane.setBottomComponent(bottom);
        this.MenuFile.setText("File");
        this.MenuExit.setText("Exit");
        this.MenuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        this.jButton1.setToolTipText("Open File");
        this.jButton2.setToolTipText("Close File");
        window = new JFrame("FUNCTIONAL TESTING TOOL");
        GUI gui = new GUI();

        t1 = new JTextArea(10,10);
        t2 = new JTextArea(10,10);
        t1.setText("Yes this is text");
        t2.setText("No, more test here");

        leftPanel.add(t1);
        rightPanel.add(t2);

        pane.setDividerLocation(150);

        window.setSize(300, 500);

        rightPanel.setSize(200, 500);
        leftPanel.setSize(200, 500);

        window.add(pane);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // button record
        construct = new Button("New Object");
        construct.setBounds(0, 0, 100, 30);

        methodRunner = new Button("Run");
        methodRunner.setBounds(0, 0, 100, 30);

        // Add buttons to GUI Window
        pane.setRightComponent(rightPanel);
        pane.setLeftComponent(leftPanel);
        //pane.setTopComponent(top);
        leftPanel.add(construct);
        rightPanel.add(methodRunner);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = window.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }

        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }

        window.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);


        window.setVisible(true);
    }


}
