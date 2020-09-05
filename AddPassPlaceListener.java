package stuMap;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddPassPlaceListener implements ItemListener, ActionListener {

    ArrayList<Integer> pass_places = new ArrayList<Integer>();
    JTextArea text = new JTextArea();
    JComboBox jcmb;

    AddPassPlaceListener(JTextArea text, JComboBox pass_cmb){
        this.jcmb =pass_cmb;
        this.text = text;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getStateChange() == ItemEvent.SELECTED){
            String name = jcmb.getSelectedItem().toString();
            int order = jcmb.getSelectedIndex()-1;
            text.setText(text.getText() +name + ",");
            pass_places.add(order);
//            System.out.println(order+" "+name);
//            System.out.println(pass_places.toString());
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pass_places.clear();
        text.setText("");
//        System.out.println(pass_places.toString());
    }
}
