package FOH;

import java.text.DecimalFormat;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * The TimeModel class provides a model for representing time values in a JComboBox.
 * It generates time slots from 17:00 to 22:45 in 15-minute intervals.
 */
public class TimeModel extends AbstractListModel<String> implements ComboBoxModel<String> {
    /**
     * Stores the currently selected time.
     */
    private String selectedTime;

    /**
     * Stores all the time values in a range.
     */
    private String[] timeValues;

    /**
     * Constructs a new TimeModel instance with the initial selected time.
     *
     * @param initTime The initial selected time in "HH:mm" format.
     */
    public TimeModel(String initTime) {
        generateTimeValues();
        setSelectedItem(initTime);
    }

    /**
     * Generates time values from 17:00 to 22:45 in 15-minute intervals.
     */
    private void generateTimeValues() {
        DecimalFormat df = new DecimalFormat("00");
        int totalSlots = (23 - 17) * 4;
        timeValues = new String[totalSlots];
        for (int i = 0; i < totalSlots; i++) {
            int hour = (i / 4) + 17;
            int minute = (i % 4) * 15;
            timeValues[i] = df.format(hour) + ":" + df.format(minute);
        }
    }


    @Override
    public int getSize() {
        return timeValues.length;
    }

    @Override
    public String getElementAt(int index) {
        return timeValues[index];
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedTime = (String) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedTime;
    }
}