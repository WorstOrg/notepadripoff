import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.*;
import java.awt.event.*;

public class TextEditor extends JFrame implements ActionListener {
    JTextArea textArea;

    TextEditor() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Custom notepad by WP45");
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        textArea = new JTextArea();

        this.add(textArea);
        textArea.setPreferredSize(new Dimension(450, 450));
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
