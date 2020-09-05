package stuMap;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CmbListener implements ItemListener {

    int order;
    String name;
    JComboBox jcmb;

    CmbListener(JComboBox jcmb){
        this.jcmb = jcmb;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getStateChange() == ItemEvent.SELECTED){
            name = jcmb.getSelectedItem().toString();
            order = jcmb.getSelectedIndex()-1;
            //System.out.println(name);
        }

    }
}
