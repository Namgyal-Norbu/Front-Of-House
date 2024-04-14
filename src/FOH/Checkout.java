package FOH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

/**
 * The Checkout class manages the checkout interface for the FOH Service Software.
 * This class provides a GUI for user interaction during the checkout process, including
 * options to pay or cancel. It is responsible for setting up the frame, panels, and buttons
 * necessary for the checkout interface.
 */


public class Checkout {
    private final JFrame frame;
    private final JPanel panel;
    private final JLabel title;

    private final JTable summary;


   /**
   * Constructs a new Checkout instance. Initialises the main frame, panel, and title label.
   */
    
    public Checkout() {
        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("Checkout");

        summary = new JTable();
    }

     /**
     * Initializes and displays the checkout interface.
     * This method sets up the background, layout, and buttons for the panel, and
     * makes the frame visible to the user.
     * 
     * @throws IOException if an I/O error occurs when setting up the page.
     */

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BorderLayout());

        setTitle();
        loadButtons();
        setPage();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(950, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

     /**
     * Sets up the title for the checkout interface.
     * Configures the font, color, alignment, and border of the title label.
     */

    public void setTitle() {
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(200, 200, 200));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);
    }

     /**
     * Placeholder method for setting up the main content of the page.
     */

    public void setPage() {
        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 20));
        summaryPanel.setBackground(new Color(43, 51, 54));

        setSummaryTable(summaryPanel);
        panel.add(summaryPanel, BorderLayout.WEST);
    }


    public void setSummaryTable(JPanel p) {
        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        tablePanel.setBackground(new Color(43, 51, 54));

        JScrollPane scrollPane = new JScrollPane(summary);
        scrollPane.setPreferredSize(new Dimension(400, 450));

        String[] columns = {
                "Item",
                "Price"
        };

        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columns);
        summary.setModel(model);
        summary.setShowGrid(true);
        summary.setGridColor(Color.LIGHT_GRAY);

        tablePanel.add(scrollPane);
        p.add(tablePanel) ;
    }


    public void loadButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton pay = new JButton("pay");
        pay.setPreferredSize(new Dimension(125, 40));
        pay.addActionListener(e -> {
            if (e.getSource() == pay) {
                frame.dispose();
                System.out.println("[event]: pay button clicked");
            }

        });

        JButton cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(125, 40));
        cancel.addActionListener(e -> {
            if (e.getSource() == cancel) {
                frame.dispose();
                ViewReservation view;
                try {
                    frame.dispose();
                    System.out.println("[event]: cancel button clicked");
                    view = new ViewReservation();
                    view.start();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        buttonPanel.add(cancel);
        buttonPanel.add(pay);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }
}
