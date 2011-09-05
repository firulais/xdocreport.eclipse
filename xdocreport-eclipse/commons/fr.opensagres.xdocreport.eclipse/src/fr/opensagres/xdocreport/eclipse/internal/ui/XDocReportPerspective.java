package fr.opensagres.xdocreport.eclipse.internal.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class XDocReportPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout.addView(XDocReportExplorerView.ID, IPageLayout.LEFT, 0.25f, "org.eclipse.ui.editorss");
		
//		layout.setEditorAreaVisible(false);
//		layout.setFixed(true);
		
		//String editorArea = layout.getEditorArea();
		
		//layout.setFixed(true);

		
		
//		IFolderLayout left = layout.createFolder( "topLeft",
//                IPageLayout.LEFT,
//                0.25f,
//                editorArea );
		
//		IFolderLayout right = layout.createFolder( "right",
//                IPageLayout.RIGHT,
//                0.70f,
//                editorArea );
		//right.addView(ReportDefinitionView.ID);

	}

}
