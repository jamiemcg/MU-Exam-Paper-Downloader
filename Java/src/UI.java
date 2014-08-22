import javax.swing.*;
import javax.swing.plaf.FileChooserUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jamie McGowan
 * @version 2014.8
 */
public class UI {
    private JButton downloadButton;
    private JPanel exam_paper_form;
    private JTextField textField1;
    private JButton chooseSaveLocationButton;
    private JCheckBox includeRepeatsCheckBox;

    private static boolean downloadRepeats = true;

    public UI() {
        downloadButton.setEnabled(false);

        chooseSaveLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Choose Download Location");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                //
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Location : "
                            +  chooser.getSelectedFile());
                    downloadButton.setEnabled(true);
                }
                else {
                    System.out.println("No Selection ");
                }
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] input = textField1.getText().split(",");
                for(String s : input) {
                    s = s.toUpperCase().replaceAll(" ", "");
                    System.out.print(s + "\t");
                }
                System.out.println();

            }
        });
        includeRepeatsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleButton();
            }
        });
    }

    public static void toggleButton() {
        if(downloadRepeats) {
            downloadRepeats = false;
        }
        else {
            downloadRepeats = true;
        }
        System.out.println(downloadRepeats);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NUIM Exam Paper Downloader");
        frame.setContentPane(new UI().exam_paper_form);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
