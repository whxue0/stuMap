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
    //清空按钮按下，清楚pass_places所有元素，并将文本域清空，撤销按钮按下，清空一个元素
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().toString().equals("清空") || pass_places.size() == 0){
            pass_places.clear();
            text.setText("");
        }
        else if(e.getActionCommand().toString().equals("撤销")){
            pass_places.remove(pass_places.size()-1);
            String[] newTextGroup = text.getText().split(",");
            String newText = "";
            for(int i = 0; i < newTextGroup.length-1; i++){
                newText += newTextGroup[i] + ",";
            }
            text.setText(newText);
        }
    }
}
