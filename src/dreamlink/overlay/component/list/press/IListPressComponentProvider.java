package dreamlink.overlay.component.list.press;

import dreamlink.overlay.component.list.ListRowData;

public interface IListPressComponentProvider {

    public int getRowCount();

    public void onHover(int index);

    public void setRowData(int index, ListRowData data);

    public void onPress(int index);
    
}
