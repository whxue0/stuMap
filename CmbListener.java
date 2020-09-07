package stuMap;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 下拉选择框的监听器，监听得到的地点序号和地点名
 */
public class CmbListener implements ItemListener {

    int order;      //地点序号
    String name;    //地点名
    JComboBox jcmb; //下拉菜单

    CmbListener(JComboBox jcmb){
        this.jcmb = jcmb;
    }

    @Override
    //重写的方法
    public void itemStateChanged(ItemEvent e) {
        //条件e.getStateChange() == ItemEvent.SELECTED 语句避免执行两次该方法
        if(e.getStateChange() == ItemEvent.SELECTED){
            name = jcmb.getSelectedItem().toString();
            order = jcmb.getSelectedIndex()-1;
        }

    }
}
