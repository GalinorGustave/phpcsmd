/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phpcsmd;

import de.foopara.phpcsmd.ui.reports.PdependReportTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.util.NbBundle.Messages;

@ActionID(id = "de.foopara.phpcsmd.PdependScanner", category = "PHP")
@ActionRegistration(displayName = "#CTL_PHPdependScanner",
        iconBase = "de/foopara/phpcsmd/resources/icon.png")
@ActionReferences({
    @ActionReference(path = "Loaders/folder/any/Actions", position = 875),
    @ActionReference(path = "Loaders/text/x-php5/Actions", position = 875)
})
@Messages("CTL_PHPdependScanner=Scan with Pdepend")
public final class PdependScanner implements ActionListener {

    private final DataObject context;

    public PdependScanner(DataObject context) {
        this.context = context;
    }

    private void performOnFileObject(FileObject fo) {
        if (fo.isFolder()) {
            PdependReportTopComponent form = new PdependReportTopComponent();
            form.setFileObject(fo);
            form.open();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileObject fo = this.context.getPrimaryFile();
        this.performOnFileObject(fo);
        //throw new UnsupportedOperationException("Scanning directory " + this.context.getPrimaryFile().getPath() + " not supported yes");
    }

    private FileObject getFileObject(Node node)
    {
        assert node != null;

        FileObject fileObj = node.getLookup().lookup(FileObject.class);
        if (fileObj != null && fileObj.isValid()) {
            return fileObj;
        }
        DataObject dataObj = node.getCookie(DataObject.class);
        if (dataObj == null) {
            return null;
        }
        fileObj = (FileObject) dataObj.getPrimaryFile();
        if (fileObj != null && fileObj.isValid()) {
            return fileObj;
        }
        return null;
    }
}