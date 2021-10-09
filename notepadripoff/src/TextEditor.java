import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class TextEditor extends JFrame implements ActionListener {
    JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner fontSizeSpinner;
    JLabel fontLabel;
    JButton fontColorButton;
    JComboBox fontBox;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem saveItem;
    JMenuItem openItem;
    JMenuItem exitItem;

    JMenu backgroundMenu;
    JMenuItem Yellowbg;
    JMenuItem Blackbg;
    JMenuItem Whitebg;
    JMenuItem purplebg;
    JMenuItem cyanbg;

    TextEditor() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Custom notepad by WP45");
        this.setSize(500, 500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("MV Boli", Font.PLAIN, 20));
        textArea.setForeground(Color.WHITE);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450, 450));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        fontLabel = new JLabel("Font: ");

        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
        fontSizeSpinner.setValue(20);
        fontSizeSpinner.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(
                        new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue()));

            }

        });

        fontColorButton = new JButton("Color: ");
        fontColorButton.addActionListener(this);

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        fontBox = new JComboBox(fonts);
        fontBox.addActionListener(this);
        fontBox.setSelectedItem("MV Boli");

        // ------ menubar ------

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");

        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        // ------ apperance menubar ------
        backgroundMenu = new JMenu("Background");

        Yellowbg = new JMenuItem("Yellow");

        Blackbg = new JMenuItem("Black");

        Whitebg = new JMenuItem("White");

        purplebg = new JMenuItem("Purple");

        cyanbg = new JMenuItem("Cyan");

        Yellowbg.addActionListener(this);

        Blackbg.addActionListener(this);

        Whitebg.addActionListener(this);

        purplebg.addActionListener(this);

        cyanbg.addActionListener(this);

        backgroundMenu.add(Yellowbg);
        backgroundMenu.add(Blackbg);
        backgroundMenu.add(Whitebg);
        backgroundMenu.add(purplebg);
        backgroundMenu.add(cyanbg);
        menuBar.add(backgroundMenu);
        // ------ /menubar ------
        this.setJMenuBar(menuBar);
        this.add(fontLabel);
        this.add(fontSizeSpinner);
        this.add(fontColorButton);
        this.add(fontBox);
        this.add(scrollPane);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setOpaque(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fontColorButton) {
            JColorChooser colorChooser = new JColorChooser();

            Color color = colorChooser.showDialog(null, "Choose a color", Color.black);

            textArea.setForeground(color);
        }
        if (e.getSource() == fontBox) {
            textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
        }
        if (e.getSource() == openItem) {
            JFileChooser filechooser = new JFileChooser();
            filechooser.setCurrentDirectory(new File("Documents"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
            filechooser.setFileFilter(filter);

            int response = filechooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(filechooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()) {
                        while (fileIn.hasNextLine()) {
                            String line = fileIn.nextLine() + "\n";
                            textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException e1) {

                    e1.printStackTrace();
                } finally {
                    fileIn.close();
                }
            }
        }
        if (e.getSource() == saveItem) {
            JFileChooser filechooser = new JFileChooser();
            filechooser.setCurrentDirectory(new File("Documents"));
            int response = filechooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                File file;
                PrintWriter fileOut = null;
                file = new File(filechooser.getSelectedFile().getAbsolutePath());
                try {
                    fileOut = new PrintWriter(file);
                    fileOut.println(textArea.getText());
                } catch (FileNotFoundException e1) {

                    e1.printStackTrace();
                } finally {
                    fileOut.close();
                }
            }
        }
        if (e.getSource() == exitItem) {
            System.exit(0);

        }
        if (e.getSource() == Yellowbg) {
            this.getContentPane().setBackground(Color.YELLOW);
            textArea.setBackground(Color.YELLOW);
        }
        if (e.getSource() == Blackbg) {
            this.getContentPane().setBackground(Color.BLACK);
            textArea.setBackground(Color.BLACK);
        }
        if (e.getSource() == Whitebg) {
            this.getContentPane().setBackground(Color.WHITE);
            textArea.setBackground(Color.WHITE);
        }
        if (e.getSource() == purplebg) {
            this.getContentPane().setBackground(new Color(128, 0, 128));
            textArea.setBackground(new Color(128, 0, 128));
        }
        if (e.getSource() == cyanbg) {
            this.getContentPane().setBackground(new Color(0, 255, 255));
            textArea.setBackground(new Color(0, 255, 255));
        }
    }

}
