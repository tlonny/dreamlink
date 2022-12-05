package dreamlink.overlay.component.list.drag;

import dreamlink.overlay.component.list.ListRowData;

public interface IListDragComponentProvider {

    public int getRowCount();

    public void setRowData(int index, ListRowData data);

    public Object getDragPayload(int index); 
    
}
