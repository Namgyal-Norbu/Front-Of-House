package FOH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

interface TableSelectionListener {
    void onTableSelected(List<Integer> selectedTables);
}

public class SelectTable {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel tablesLabel;
    private static List<Integer> selectedTables;
    private static TableSelectionListener selectionListener;

    public SelectTable() {
        frame = new JFrame();
        panel = new JPanel();
        tablesLabel = new JLabel("Selected Tables: ");
        tablesLabel.setForeground(Color.WHITE);
        selectedTables = new ArrayList<>();
    }

    public void start(TableSelectionListener listener) {
        selectionListener = listener;
        panel.setBackground(new Color(43, 51, 54));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        panel.setLayout(new BorderLayout());

        tableChoices();
        panel.add(tablesLabel, BorderLayout.NORTH);
        setButton();
        

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Select Tables");
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void setButton() {
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, submitButton.getPreferredSize().height));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectionListener.onTableSelected(selectedTables);
                frame.dispose();
            }
        });
        panel.add(submitButton, BorderLayout.SOUTH);
    }

    public void tableChoices() {
        JPanel buttonsPanel = new JPanel(new GridLayout(0, 4, 5, 5));
        buttonsPanel.setBackground(new Color(43, 51, 54));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 1; i <= 15; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setPreferredSize(new Dimension(40, 40));

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    int tableNumber = Integer.parseInt(clickedButton.getText());
                    
                    if (selectedTables.contains(tableNumber)) {
                        selectedTables.remove(Integer.valueOf(tableNumber));
                    
                    } else {
                        selectedTables.add(tableNumber);
                    }
                    updateSelectedTablesLabel();
                }
            });
            buttonsPanel.add(button);
        }

        panel.add(buttonsPanel, BorderLayout.CENTER);
    }

    private void updateSelectedTablesLabel() {
        StringBuilder selectedTablesText = new StringBuilder("Selected Tables: ");
        for (Integer table : selectedTables) {
            selectedTablesText.append(String.format("%02d", table)).append(", ");
        }
        if (!selectedTables.isEmpty()) {
            selectedTablesText.setLength(selectedTablesText.length() - 2);
        }
        tablesLabel.setText(selectedTablesText.toString());
    }
}