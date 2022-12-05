package dreamlink.overlay.component.list.select;

import dreamlink.overlay.component.list.ListRowData;

public interface IListSelectComponentProvider {

    public int getRowCount();

    public int getSelectedRowIndex();

    public void setRowData(int index, ListRowData data);

    public void onChange(int index);
    
}
