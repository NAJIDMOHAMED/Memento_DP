package ma.najid.mementodp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestMemento extends JFrame {
    private JButton saveButton, undoButton, redoButton;
    private JTextArea theArticle = new JTextArea(20, 40);

    CareTaker careTaker = new CareTaker();
    Originater originater = new Originater();

    int savedFile = 0, currentFile = -1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestMemento::new);
    }

    public TestMemento() {
        this.setSize(500, 500);
        this.setTitle("Memento Design Pattern");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.CYAN);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Article:"));
        panel.add(new JScrollPane(theArticle));

        saveButton = new JButton("Save");
        undoButton = new JButton("Undo");
        redoButton = new JButton("Redo");

        saveButton.addActionListener(new ButtonListener());
        undoButton.addActionListener(new ButtonListener());
        redoButton.addActionListener(new ButtonListener());

        panel.add(saveButton);
        panel.add(undoButton);
        panel.add(redoButton);

        undoButton.setEnabled(false);
        redoButton.setEnabled(false);

        this.add(panel);
        this.setVisible(true);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveButton && !theArticle.getText().equals("")) {
                String text = theArticle.getText();
                originater.set(text);
                careTaker.addMemento(originater.storeInMemento());

                savedFile = careTaker.size();
                currentFile = savedFile - 1;

                System.out.println("Saved file: " + savedFile);
                undoButton.setEnabled(currentFile > 0);
                redoButton.setEnabled(false);
            } else if (e.getSource() == undoButton) {
                if (currentFile > 0) {
                    currentFile--;
                    String text = originater.restoreFromMemento(careTaker.getMemento(currentFile));
                    theArticle.setText(text);
                    redoButton.setEnabled(true);
                    undoButton.setEnabled(currentFile > 0);
                }
            } else if (e.getSource() == redoButton) {
                if (currentFile < savedFile - 1) {
                    currentFile++;
                    String text = originater.restoreFromMemento(careTaker.getMemento(currentFile));
                    theArticle.setText(text);
                    undoButton.setEnabled(true);
                    redoButton.setEnabled(currentFile < savedFile - 1);
                }
            }
        }
    }
}
