package FOH;
import FOH.Courses.Beverages;
import FOH.Courses.FirstCourse;
import FOH.Courses.SecondCourse;
import FOH.Courses.ThirdCourse;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class ServeTable extends ServeSearch{

    private static JFrame frame;
    private static JPanel panel;

    private final int bookingID;
    private final String prefix;
    private final String forename;
    private final String surname;
    private final int occupants;
    private final String tables;

    public ServeTable(int bookingID, String prefix, String forename, String surname, int occupants, String tables) {
        frame = new JFrame();
        panel = new JPanel(new BorderLayout());

        this.bookingID = bookingID;
        this.prefix = prefix;
        this.forename = forename;
        this.surname = surname;
        this.occupants = occupants;
        this.tables = tables;
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setText();
        loadButtons();

        frame.setTitle("FOH Service Software");
        frame.setSize(950, 550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    public void setText() {
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        textPanel.setBackground(new Color(43, 51, 54));
        JLabel text = new JLabel(
                "<html>" +
                        "<div style='text-align: center;'>" +
                        "Name: " + prefix + " " + forename + " " + surname + "<br>" +
                        "Booking ID: " + bookingID + "<br>" +
                        "Table(s): " + tables + "<br>" +
                        "Diners: " + occupants + "<br>" +
                        "</div>" +
                        "</html>");

        text.setFont(new Font("Arial", Font.BOLD, 15));
        text.setForeground(new Color(200, 200, 200));
        text.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        textPanel.add(text);

        loadActionButton(textPanel);
        panel.add(textPanel);
    }


    public void loadActionButton(JPanel p) {
        int buttonWidth = 200;
        int spacing = 20;
        int windowWidth = 950;
        int totalWidth = buttonWidth * 4 + spacing * 3;
        int xOffset = (windowWidth - totalWidth) / 2;

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBackground(new Color(43, 51, 54));
        buttonPanel.setPreferredSize(new Dimension(windowWidth, 250));

        JButton firstCourse = new JButton("First Course");
        firstCourse.setBounds(xOffset, 50, buttonWidth, 200);
        buttonPanel.add(firstCourse);
        firstCourse.addActionListener(e -> {
            frame.dispose();
            FirstCourse firstCourse1 = new FirstCourse();
            try {
                System.out.println("[event]: First Course button clicked");
                firstCourse1.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton secondCourse = new JButton("Second Course");
        secondCourse.setBounds(xOffset + buttonWidth + spacing, 50, buttonWidth, 200);
        buttonPanel.add(secondCourse);
        secondCourse.addActionListener(e -> {
            frame.dispose();
            SecondCourse secondCourse1 = new SecondCourse();
            try {
                System.out.println("[event]: Second Course button clicked");
                secondCourse1.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton thirdCourse = new JButton("Third Course");
        thirdCourse.setBounds(xOffset + 2 * (buttonWidth + spacing), 50, buttonWidth, 200);
        buttonPanel.add(thirdCourse);
        thirdCourse.addActionListener(e -> {
            frame.dispose();
            ThirdCourse thirdCourse1 = new ThirdCourse();
            try {
                System.out.println("[event]: Third Course button clicked");
                thirdCourse1.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton beverages = new JButton("Beverages");
        beverages.setBounds(xOffset + 3 * (buttonWidth + spacing), 50, buttonWidth, 200);
        buttonPanel.add(beverages);
        beverages.addActionListener(e -> {
            frame.dispose();
            Beverages beverages1 = new Beverages();
            try {
                System.out.println("[event]: Beverages button clicked");
                beverages1.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        p.add(buttonPanel);
    }


    public void loadButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton submit = new JButton("submit");
        submit.setPreferredSize(new Dimension(125, 40));
        submit.addActionListener(e -> {
            if (e.getSource() == submit) {
                System.out.println("[event]: submit button clicked");
            }
        });

        JButton cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(125, 40));
        cancel.addActionListener(e -> {
            if (e.getSource() == cancel) {
                Home home;
                try {
                    frame.dispose();
                    System.out.println("[event]: cancel button clicked");
                    home = new Home();
                    home.start();

                } catch (SQLException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        buttonPanel.add(cancel);
        buttonPanel.add(submit);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }
}