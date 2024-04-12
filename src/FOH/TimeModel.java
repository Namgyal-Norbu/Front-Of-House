package FOH;

import java.text.DecimalFormat;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class TimeModel  extends AbstractListModel<String> implements ComboBoxModel<String> {
    private String[] timeValues;
    private String selectedTime;

    public TimeModel(String initTime) {
        generateTimeValues();
        setSelectedItem(initTime);
    }

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