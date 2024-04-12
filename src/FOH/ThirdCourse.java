package FOH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class ThirdCourse {

    private JFrame frame;
    private JPanel mainPanel;

    private final JLabel tableInfo;
    private final JLabel customerName;
    private final JLabel time;

    private final JTable menu;

    private final Font commonFont = new Font("Arial", Font.BOLD, 24); // Example size
    private final Color fontColor = Color.WHITE;

    public ThirdCourse() {
        frame = new JFrame("FOH Service Software");
        mainPanel = new JPanel(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(mainPanel);
        tableInfo = new JLabel("Table ID: ");
        customerName = new JLabel("Customer Name: ");
        time = new JLabel("Time: ");
        menu = new JTable();
    }

    public void start() throws IOException {
        loadButton();
        setTitle();
        setBookingTable(mainPanel);
        frame.setVisible(true);
    }
    public void setTitle(){
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 40));
        titlePanel.setBackground(new Color(43, 51, 54));
        tableInfo.setFont(commonFont);
        tableInfo.setForeground(fontColor);
        customerName.setFont(commonFont);
        customerName.setForeground(fontColor);
        time.setFont(commonFont);
        time.setForeground(fontColor);
        tableInfo.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 10));
        customerName.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 10));
        time.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 10));
        titlePanel.add(tableInfo);
        titlePanel.add(customerName);
        titlePanel.add(time);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
    }

    private void loadButton() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        buttonPanel.setBackground(new Color(43, 51, 54));
        JButton exit = new JButton("Exit");
        exit.setPreferredSize(new Dimension(100, 50));
        exit.addActionListener(e -> {
            frame.dispose();
            try {
                ServeTable serveTable = new ServeTable();
                serveTable.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        JButton add = new JButton("Add");
        add.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(exit);
        buttonPanel.add(add);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    public void setBookingTable(JPanel p) {
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tablePanel.setBackground(new Color(43, 51, 54));

        JScrollPane scrollPane = new JScrollPane(menu);
        scrollPane.setPreferredSize(new Dimension(800, 350));

        String[] columns = {
                "Dish",
                "Price",
                "Quantity",
                "Total"
        };

        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columns);
        menu.setModel(model);

        tablePanel.add(scrollPane);
        p.add(tablePanel);
    }

}
