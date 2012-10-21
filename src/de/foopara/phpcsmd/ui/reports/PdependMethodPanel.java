package de.foopara.phpcsmd.ui.reports;

import de.foopara.phpcsmd.exec.pdepend.PdependTypes;
import javax.swing.JProgressBar;
import org.openide.util.Lookup;

/**
 *
 * @author nspecht
 */
public class PdependMethodPanel extends PdependGenericResultPanel {

    /**
     * Creates new form PdependMethodPanel
     */
    public PdependMethodPanel(Lookup lkp) {
        super(lkp);
        initComponents();
        this.addLabel("name", "Name", "Method");

        this.addSeparator(null, "Codelines", "Method");
        this.addLabel("loc", "Lines of Code", "Method");
        this.addProgressbar("ncloc", "Non Comment Lines of Code", "Method");
        this.addProgressbar("cloc", "Comment Lines of Code", "Method");
        this.addProgressbar("eloc", "Executable Lines of Code", "Method");
        this.addProgressbar("lloc", "Logical Lines Of Code", "Method");


        this.addSeparator(null, "Complexity", "Different Metrics");
        this.addLabel("ccn", "Cyclomatic Complexity", "Different Metrics");
        this.addLabel("ccn2", "Cyclomatic Complexity Number", "Different Metrics");
        this.addLabel("npath", "NPath Complexity", "Different Metrics");
    }

    public void setMethod(PdependTypes.PdependMethod method) {
        ((JProgressBar)this.elements.get("ncloc")).setMaximum(method.loc);
        ((JProgressBar)this.elements.get("cloc")).setMaximum(method.loc);
        ((JProgressBar)this.elements.get("eloc")).setMaximum(method.loc);
        ((JProgressBar)this.elements.get("lloc")).setMaximum(method.loc);
        this.setFields(method);
        this.setEditorButton(method.getFilename());
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridBagLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
