package stuMap;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * 添加途径地点的下拉框 和 清空按钮 的 监听器
 */

public class AddPassPlaceListener implements ItemListener, ActionListener {

    ArrayList<Integer> pass_places = new ArrayList<Integer>();
    JTextArea text = new JTextArea();
    JComboBox jcmb;

    AddPassPlaceListener(JTextArea text, JComboBox pass_cmb){
        this.jcmb =pass_cmb;
        this.text = text;
    }

    @Override
    //每次下拉选择的时候，将地点存入pass_places中，并在text区域显示。
    public void itemStateChanged(ItemEvent e) {

        if(e.getStateChange() == ItemEvent.SELECTED){
            String name = jcmb.getSelectedItem().toString();
            int order = jcmb.getSelectedIndex()-1;
            text.setText(text.getText() +name + ",");
            pass_places.add(order);
        }
    }

    @Override
    //清空按钮按下，清楚pass_places所有元素，并将文本域清空
    public void actionPerformed(ActionEvent e) {
        pass_places.clear();
        text.setText("");
    }
}
