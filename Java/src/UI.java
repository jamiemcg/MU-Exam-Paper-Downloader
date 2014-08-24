import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

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
    private File location = new File(".");
    private int start_year = Calendar.getInstance().get(Calendar.YEAR);
    private int end_year = 2004;

    private boolean downloadRepeats = true;

    public UI() {
        downloadButton.setEnabled(false);

        includeRepeatsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(downloadRepeats) {
                    downloadRepeats = false;
                    System.out.println("Don't download repeats");
                }
                else {
                    downloadRepeats = true;
                    System.out.println("Download repeats");
                }
            }
        });

        chooseSaveLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(location);
                chooser.setDialogTitle("Choose Download Location");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                //
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    System.out.println("Location : "
                            + chooser.getSelectedFile());
                    downloadButton.setEnabled(true);
                    location = chooser.getSelectedFile();
                    String[] temp = location.getAbsolutePath().split("\\\\");
                    chooseSaveLocationButton.setText("Save to: " + temp[temp.length - 1]);
                } else {
                    System.out.println("No location selected");
                }
            }
        });

        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                downloadButton.setEnabled(false);
                downloadButton.setText("Downloading");
                String[] input = textField1.getText().split(",");
                for (String s : input) {
                    s = s.toUpperCase().replaceAll(" ", "");
                    System.out.print(s + "\t");
                }

                //Starts download at current year (as set by the machine)
                for (String module : input) {
                    Downloader[] threads = new Downloader[start_year - end_year + 1];
                    int i = start_year;
                    while(i > end_year) {
                        threads[i - end_year] = new Downloader(i, module.toUpperCase().replaceAll(" ", ""), location, downloadRepeats);
                        threads[i - end_year].start();
                        i--;
                    }
                    i = start_year;
                    while(i > end_year) {
                        try {
                            threads[i - end_year].join();   //Wait on all threads of current module to finish
                                                            //before starting to download new module
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        i--;
                    }
                }
                downloadButton.setEnabled(true);
                downloadButton.setText("Start Download");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("NUIM Exam Paper Downloader");
        frame.setContentPane(new UI().exam_paper_form);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("icon.png");
        try {
            frame.setIconImage(ImageIO.read(UI.class.getResource("icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setVisible(true);
    }
}
