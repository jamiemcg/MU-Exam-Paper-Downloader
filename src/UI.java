import javax.swing.*;

/**
 * Created by Jamie on 22/08/2014.
 */
public class UI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("NUIM Exam Paper Downloader");
        frame.setContentPane(new UI().exam_paper_form);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton downloadButton;
    private JPanel exam_paper_form;
    private JTextField textField1;
}
