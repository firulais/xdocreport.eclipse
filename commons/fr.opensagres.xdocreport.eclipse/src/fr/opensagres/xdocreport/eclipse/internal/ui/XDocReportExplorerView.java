package fr.opensagres.xdocreport.eclipse.internal.ui;

import java.util.Collection;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import fr.opensagres.xdocreport.eclipse.PlatformXDocReport;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportBaseModule;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModule;
import fr.opensagres.xdocreport.eclipse.extensions.modules.IReportModuleEntry;
import fr.opensagres.xdocreport.eclipse.internal.Activator;
import fr.opensagres.xdocreport.eclipse.internal.ImageResources;
import fr.opensagres.xdocreport.eclipse.internal.extensions.modules.ReportBaseModule;
import fr.opensagres.xdocreport.eclipse.ui.handlers.ContextHandlerUtils;

public class XDocReportExplorerView extends ViewPart implements
		IDoubleClickListener {
	public XDocReportExplorerView() {
	}

	public static final String ID = "fr.opensagres.xdocreport.eclipse.reportExplorer";

	private TreeViewer viewer;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Collection<?>) {
				return ((Collection<?>) inputElement).toArray();
			}
			return new Object[0];
		}

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof IReportModule) {
				return ((IReportModule) parentElement).getEntries().toArray();
			}
			return null;
		}

		public Object getParent(Object element) {
			return null;
		}

		public boolean hasChildren(Object element) {
			return element instanceof IReportModule;
		}
	}

	class ViewLabelProvider extends LabelProvider {
		@Override
		public String getText(Object element) {
			if (element instanceof ReportBaseModule) {
				return ((IReportBaseModule) element).getName();
			}
			return super.getText(element);
		}

		public Image getImage(Object element) {
			if (element instanceof IReportModule) {
				return XDocReportExplorerView.this.getImage(
						(IReportModule) element, ImageResources.IMG_MODULE);
			}
			if (element instanceof IReportModuleEntry) {
				return XDocReportExplorerView.this.getImage(
						(IReportModuleEntry) element, "entry");
			}
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	private Image getImage(IReportBaseModule baseModule, String imageKey) {
		Image image = baseModule.getImage();
		if (image != null) {
			return image;
		}
		if (imageKey == null) {
			return null;
		}
		image = ImageResources.getImage(imageKey);
		if (image != null) {
			return image;
		}
		return PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		// Provide the input to the ContentProvider
		viewer.setInput(PlatformXDocReport.getReportModuleRegistry()
				.getReportModules());
		viewer.addDoubleClickListener(this);
		viewer.expandAll();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void doubleClick(final DoubleClickEvent event) {
		ISelection selection = event.getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object element = structuredSelection.getFirstElement();
			if (element instanceof IReportModuleEntry) {
				IReportModuleEntry entry = (IReportModuleEntry) element;
				String commandId = entry.getCommandId();

				try {
					ContextHandlerUtils.executeCommand(commandId, getSite(),
							entry, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}