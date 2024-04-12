package FOH;
import FOH.Courses.FirstCourse;
import FOH.Courses.SecondCourse;
import FOH.Courses.ThirdCourse;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class ServeTable extends ServeSearch{

    private static JFrame frame;
    private static JPanel panel;
    private final JLabel tableInfo;
    private final JLabel customerName;
    private final JLabel time;

    private final Font commonFont = new Font("Arial", Font.BOLD, 36); // Example size
    private final Color fontColor = Color.WHITE;


    public ServeTable() {
        frame = new JFrame();
        panel = new JPanel(new BorderLayout());
        tableInfo = new JLabel("Table ID: ");
        customerName = new JLabel("Customer Name: ");
        time = new JLabel("Time: ");
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(950, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        loadbutton();
        setTitle();
    }


    public void setTitle(){
            JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
            titlePanel.setBackground(new Color(43, 51, 54));
            tableInfo.setFont(commonFont);
            tableInfo.setForeground(fontColor);
            customerName.setFont(commonFont);
            customerName.setForeground(fontColor);
            time.setFont(commonFont);
            time.setForeground(fontColor);
            tableInfo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 10));
            customerName.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 10));
            time.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 10));
            titlePanel.add(tableInfo);
            titlePanel.add(customerName);
            titlePanel.add(time);
            panel.add(titlePanel, BorderLayout.NORTH);
        }


    public void loadbutton() {

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBackground(new Color(43, 51, 54));
        buttonPanel.setPreferredSize(new Dimension(950, 250));

        JButton exit = new JButton("Exit");
        exit.setBounds(50, 480, 100, 50);
        buttonPanel.add(exit);

        exit.addActionListener(e -> {
            frame.dispose();
            ServeSearch serveSearch = new ServeSearch();
            try {
                System.out.println("[event]: Exit button clicked");
                serveSearch.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton bill = new JButton("View Order");
        bill.setBounds(800, 480, 100, 50);
        buttonPanel.add(bill);
        bill.addActionListener(e -> {
            frame.dispose();
            Bill billpage = new Bill();
            try {
                System.out.println("[event]: Exit button clicked");
                billpage.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton firstCourse = new JButton("First Course");
        firstCourse.setBounds(50, 150, 200, 200);
        buttonPanel.add(firstCourse);
        firstCourse.addActionListener(e -> {
            frame.dispose();
            FirstCourse firstCourse1 = new FirstCourse();
            try {
                System.out.println("[event]: Exit button clicked");
                firstCourse1.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton secondCourse = new JButton("Second Course");
        secondCourse.setBounds(260, 150, 200, 200);
        buttonPanel.add(secondCourse);
        secondCourse.addActionListener(e -> {
            frame.dispose();
            SecondCourse secondCourse1 = new SecondCourse();
            try {
                System.out.println("[event]: Exit button clicked");
                secondCourse1.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton thirdCourse = new JButton("Third Course");
        thirdCourse.setBounds(470, 150, 200, 200);
        buttonPanel.add(thirdCourse);
        thirdCourse.addActionListener(e -> {
            frame.dispose();
            ThirdCourse thirdCourse1 = new ThirdCourse();
            try {
                System.out.println("[event]: Exit button clicked");
                thirdCourse1.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton beverages = new JButton("Beverages");
        beverages.setBounds(680, 150, 200, 200);
        buttonPanel.add(beverages);
        beverages.addActionListener(e -> {
            frame.dispose();
            Beverages beverages1 = new Beverages();
            try {
                System.out.println("[event]: Exit button clicked");
                beverages1.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        panel.add(buttonPanel);
    }
}