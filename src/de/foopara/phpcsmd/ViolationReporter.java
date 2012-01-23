/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.foopara.phpcsmd;

import de.foopara.phpcsmd.exec.phpcs.Phpcs;
import java.util.List;
import org.netbeans.spi.tasklist.PushTaskScanner;
import org.netbeans.spi.tasklist.Task;
import org.netbeans.spi.tasklist.TaskScanningScope;
import org.openide.filesystems.FileObject;


/**
 *
 * @author nspecht
 */
public class ViolationReporter extends PushTaskScanner {

    public ViolationReporter() {
        super(
                "PHP Violations",
                "PHP Checkstyle and MessDetector Violations",
                null
        );
    }

    @Override
    public void setScope(TaskScanningScope tss, Callback clbck) {
        if (tss == null || clbck == null) {
            return;
        }

        int i = 0;
        for (FileObject file : tss.getLookup().lookupAll(FileObject.class)) {
            if(i >= 1) {
                return;
            }

            clbck.setTasks(file, scan(file));
            i++;
        }
    }

    public List<? extends Task> scan(FileObject fo) {
         new Phpcs().execute(fo);
         return ViolationRegistry.getInstance().getTaskList(fo);
    }
}