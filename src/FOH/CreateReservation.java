package FOH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateReservation {
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    
    private JComboBox prefix;
    private JTextField forename;
    private JTextField surname;
    private JTextField telephone;

    public CreateReservation() {
        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("Create Reservation");

        String[] prefixList = {"Mr.", "Ms.", "Mrs.", "Dr."};
        prefix = new JComboBox<>(prefixList);

        forename = new JTextField(20);
        surname = new JTextField(20);
        telephone = new JTextField(20);
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BorderLayout());

        setTitle();
        setForm();
        loadButtons();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(500, 650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setTitle() {
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(200, 200, 200));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        panel.add(title, BorderLayout.NORTH);
    }

    public void setForm() {
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        formPanel.setBackground(new Color(43, 51, 54));

        // forename text field
        JPanel prefixPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 11, 5));
        prefixPanel.setBackground(new Color(43, 51, 54));
        JLabel prefixLabel = new JLabel("Prefix:");
        prefixLabel.setForeground(Color.WHITE);
        
        prefixPanel.add(prefixLabel);
        prefixPanel.add(prefix);
        formPanel.add(prefixPanel);
        
        // forename text field
        JPanel forenamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 11, 5));
        forenamePanel.setBackground(new Color(43, 51, 54));
        JLabel forenameLabel = new JLabel("Forename:");
        forenameLabel.setForeground(Color.WHITE);
        forename.setPreferredSize(new Dimension(forename.getPreferredSize().width, 25));

        forenamePanel.add(forenameLabel);
        forenamePanel.add(forename);
        formPanel.add(forenamePanel);

        // surname text field
        JPanel surnamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 18, 5));
        surnamePanel.setBackground(new Color(43, 51, 54));
        JLabel surnameLabel = new JLabel("Surname:");
        surnameLabel.setForeground(Color.WHITE);
        surname.setPreferredSize(new Dimension(surname.getPreferredSize().width, 25));

        surnamePanel.add(surnameLabel);
        surnamePanel.add(surname);
        formPanel.add(surnamePanel);
        
        // telephone text field
        JPanel telephonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        telephonePanel.setBackground(new Color(43, 51, 54));
        JLabel telephoneLabel = new JLabel("Telephone:");
        telephoneLabel.setForeground(Color.WHITE);
        telephone.setPreferredSize(new Dimension(telephone.getPreferredSize().width, 25));

        telephonePanel.add(telephoneLabel);
        telephonePanel.add(telephone);
        formPanel.add(telephonePanel);

        panel.add(formPanel, BorderLayout.CENTER);
    }

    public void loadButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(43, 51, 54));

        JButton submit = new JButton("submit");
        submit.setPreferredSize(new Dimension(125, 40));
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submit) {
                    //TODO: connect to database and submit form
                    System.out.println("form submitted");
                }
            }
        });

        JButton cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(125, 40));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cancel) {
                    frame.dispose();
                    Home home = new Home();
                    try {
                        home.start();
                    
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 50, 0));

        buttonPanel.add(cancel);
        buttonPanel.add(submit);
        panel.add(buttonPanel, BorderLayout.SOUTH);
   }
}
