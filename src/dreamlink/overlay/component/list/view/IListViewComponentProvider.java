package dreamlink.overlay.component.list.view;

public interface IListViewComponentProvider {
    
    public int getRowOffset();

    public void setRowData(int index, ListViewRowData data);
    
}
