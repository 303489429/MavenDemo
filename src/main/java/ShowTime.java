import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;


public class ShowTime implements IStartup {

	public void earlyStartup() {
		Display.getDefault().syncExec(new Runnable(){
			public void run() {
				long startTime = Long.parseLong(System.getProperty("eclipse.startTime"));
				long costTime = System.currentTimeMillis() - startTime ;
				Shell shell = Display.getDefault().getActiveShell() ;
				String msg = "Eclipse 启动耗时："+ costTime +"ms" ;
				MessageDialog.openInformation(shell, "Information", msg);
			}
		});
	}

}
