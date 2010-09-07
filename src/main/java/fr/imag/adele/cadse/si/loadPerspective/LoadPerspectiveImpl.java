/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
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
