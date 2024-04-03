package FOH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

interface TableSelectionListener {
    void onTableSelected(List<Integer> selectedTables);
}

public class CreateReservation {
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    
    private JComboBox<String> prefix;
    private JTextField forename;
    private JTextField surname;
    private JTextField telephone;
    private JDatePickerImpl date;
    private JComboBox<String> time;
    private JTextField occupants;
    private JButton tableNo;

    public CreateReservation() {
        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("Create Reservation");

        String[] prefixList = {"Mr.", "Ms.", "Mrs.", "Dr."};
        prefix = new JComboBox<>(prefixList);

        forename = new JTextField(20);
        surname = new JTextField(20);
        telephone = new JTextField(20);
        occupants = new JTextField(3);

        UtilDateModel model = new UtilDateModel();
        date = new JDatePickerImpl(new JDatePanelImpl(model));

        time = new JComboBox<>(new TimeModel());

        tableNo = new JButton("...");
    }

    public void start() throws IOException {
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.setLayout(new BorderLayout());

        setTitle();
        loadButtons();
        setForm();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("FOH Service Software");
        frame.setSize(550, 650);
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

    public void addField(JPanel panel, FlowLayout layout ,String label, JTextField field) {
        JPanel fieldPanel = new JPanel(layout);
        fieldPanel.setBackground(new Color(43, 51, 54));
        
        JLabel text = new JLabel(label);
        text.setForeground(Color.WHITE);

        fieldPanel.add(text);
        fieldPanel.add(field);
        panel.add(fieldPanel);
    }

    public void addDropDown(JPanel panel, FlowLayout layout ,String label, JComboBox<String> dropBox) {
        JPanel fieldPanel = new JPanel(layout);
        fieldPanel.setBackground(new Color(43, 51, 54));
        
        JLabel text = new JLabel(label);
        text.setForeground(Color.WHITE);

        fieldPanel.add(text);
        fieldPanel.add(dropBox);
        panel.add(fieldPanel);
    }

    public void setForm() {
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        formPanel.setBackground(new Color(43, 51, 54));

        // prefix drop-down list
        addDropDown(formPanel, new FlowLayout(FlowLayout.LEFT, 30, 5), "Prefix:", prefix);

        // forename text field
        addField(formPanel, new FlowLayout(FlowLayout.LEFT, 11, 5), "Forename:", forename);

        // surname text field
        addField(formPanel, new FlowLayout(FlowLayout.LEFT, 18, 5), "Surname:", surname);
        
        // telephone text field
        addField(formPanel, new FlowLayout(FlowLayout.LEFT, 8, 5), "Telephone:", telephone);

        // calander date selector
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        datePanel.setBackground(new Color(43, 51, 54));
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setForeground(Color.WHITE);
        datePanel.add(dateLabel);
        datePanel.add(date);
        
        formPanel.add(datePanel);

        // time of day selector
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        timePanel.setBackground(new Color(43, 51, 54));
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setForeground(Color.WHITE);
        timePanel.add(timeLabel);
        timePanel.add(time);
        
        formPanel.add(timePanel);

        // occupants text field
        addField(formPanel, new FlowLayout(FlowLayout.LEFT, 10, 20), "Occupants:", occupants);

        // Table No field
        JPanel tabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        tabelPanel.setBackground(new Color(43, 51, 54));        
        JLabel tableLabel = new JLabel("Table No:");
        tableLabel.setForeground(Color.WHITE);

        tabelPanel.add(tableLabel);
        tabelPanel.add(tableNo);
        formPanel.add(tabelPanel);

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

        tableNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == tableNo) {
                    SelectTable selectTable = new SelectTable();
                    selectTable.start(new TableSelectionListener() {
                        public void onTableSelected(List<Integer> selectedTables) {
                            StringBuilder tables = new StringBuilder();

                            for (int i = 0; i < selectedTables.size(); i++) {
                                tables.append(selectedTables.get(i));
                                if (i < selectedTables.size() - 1) {
                                    tables.append(", ");
                                }
                            }
                            tableNo.setText(tables.toString());
                        }
                    });
                }
            }
        });

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));

        buttonPanel.add(cancel);
        buttonPanel.add(submit);
        panel.add(buttonPanel, BorderLayout.SOUTH);
   }
}
