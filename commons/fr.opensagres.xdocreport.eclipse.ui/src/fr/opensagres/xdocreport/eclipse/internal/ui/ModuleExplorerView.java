package fr.opensagres.xdocreport.eclipse.internal.ui;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.widgets.pshelf.AbstractRenderer;
import org.eclipse.nebula.widgets.pshelf.PShelf;
import org.eclipse.nebula.widgets.pshelf.PShelfItem;
import org.eclipse.nebula.widgets.pshelf.RedmondShelfRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
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

public class ModuleExplorerView extends ViewPart implements
		IDoubleClickListener {
	public ModuleExplorerView() {
	}

	public static final String ID = "fr.opensagres.xdocreport.eclipse.moduleExplorer";

	// private TreeViewer viewer;

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
				return ModuleExplorerView.this.getImage(
						(IReportModule) element, ImageResources.IMG_MODULE);
			}
			if (element instanceof IReportModuleEntry) {
				return ModuleExplorerView.this.getImage(
						(IReportModuleEntry) element, "entry");
			}
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	private Image getImage(IReportBaseModule baseModule, String imageKey) {
		Image image = baseModule.getIcon();
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

		AbstractRenderer renderer = new RedmondShelfRenderer();
		PShelf shelf = new PShelf(parent, SWT.BORDER);
		shelf.setRenderer(renderer);

		List<IReportModule> modules = PlatformXDocReport
				.getReportModuleRegistry().getReportModules();

		for (IReportModule module : modules) {
			createModule(shelf, module);
		}
	}

	private void createModule(PShelf shelf, IReportModule module) {
		PShelfItem item = new PShelfItem(shelf, SWT.NONE);
		item.setText(module.getName());
		item.setImage(module.getIcon());
		item.getBody().setLayout(new FillLayout());

		TreeViewer viewer = new TreeViewer(item.getBody(), SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		// Provide the input to the ContentProvider
		viewer.setInput(module.getEntries());
		viewer.addDoubleClickListener(this);
		viewer.expandAll();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		// viewer.getControl().setFocus();
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
				} catch (Throwable e) {
					// TODO : log that
					e.printStackTrace();
					IStatus status = new Status(IStatus.ERROR,
							Activator.PLUGIN_ID, e.getMessage(), e);
					// ErrorDialog.openError(viewer.getControl().getShell(),
					// Messages.XDocReportExplorerView_error,
					// e.getMessage(), status);
				}
			}
		}

	}
}