package vrcurso.framework.view;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import vrcurso.framework.vo.ItemComboBoxVO;

public class ComboBox extends JComboBox {

    private ArrayList<ItemComboBoxVO> vItem;

    public ComboBox() {
        super();
    }

    public void setModel(ArrayList i_vItem) {
        vItem = i_vItem;
        super.setModel(new DefaultComboBoxModel(vItem.toArray()));
    }

    public int getId() {
        return ((ItemComboBoxVO) getSelectedItem()).getId();
    }

    public void setId(int i_id) {
        for (ItemComboBoxVO oItem : vItem) {
            if(oItem.getId() == i_id){
                setSelectedItem(oItem);
            }
        }
    }

}
