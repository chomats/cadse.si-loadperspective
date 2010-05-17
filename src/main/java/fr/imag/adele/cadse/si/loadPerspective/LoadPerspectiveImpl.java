package fr.imag.adele.cadse.si.loadPerspective;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import fr.imag.adele.cadse.core.CadseException;
import fr.imag.adele.cadse.core.WSModelState;
import fr.imag.adele.cadse.workspace.as.loadservice.LoadService;

public class LoadPerspectiveImpl implements LoadService {

	@Override
	public String getDescription() {
		return "loadPerspective";
	}

	@Override
	public void run(WSModelState state) throws CadseException {
		if (state != WSModelState.RUN) return;
		
		try {
			// IWorkbenchWindow win =
			// PlatformUI.getWorkbench().getActiveWorkbenchWindow();

			final IWorkbench workbench = PlatformUI.getWorkbench();
			Runnable r = new Runnable() {
				public void run() {
					IWorkbenchWindow[] win = workbench.getWorkbenchWindows();
					if (win != null && win.length == 1) {
						IWorkbenchPage page;
						try {
							page = workbench.showPerspective("fede.tool.workspace.workspacePerspective", win[0]);
							win[0].setActivePage(page);
						} catch (WorkbenchException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			};
			final Display currentDisplay = workbench.getDisplay();
			currentDisplay.asyncExec(r);

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
