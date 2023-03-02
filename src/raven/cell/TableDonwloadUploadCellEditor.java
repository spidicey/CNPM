package raven.cell;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author RAVEN
 */
public class TableDonwloadUploadCellEditor extends DefaultCellEditor {

    private TableDownloadUploadEvent event;

    public TableDonwloadUploadCellEditor(TableDownloadUploadEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        DownloadUpload action = new DownloadUpload();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
