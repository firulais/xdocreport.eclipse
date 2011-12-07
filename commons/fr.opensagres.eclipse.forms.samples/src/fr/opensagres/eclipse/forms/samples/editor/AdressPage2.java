package fr.opensagres.eclipse.forms.samples.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.pagination.PaginationBannerWidget;
import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.nebula.widgets.pagination.PaginationInfoWidget;
import org.eclipse.nebula.widgets.pagination.spring.PageableController;
import org.eclipse.nebula.widgets.pagination.spring.PageableStructuredViewerLoader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.springframework.data.domain.Page;

import fr.opensagres.eclipse.forms.editor.ModelFormEditor;
import fr.opensagres.eclipse.forms.editor.ModelFormPage;
import fr.opensagres.eclipse.forms.samples.model.Person;
import fr.opensagres.eclipse.forms.samples.services.PersonService;

public class AdressPage2 extends ModelFormPage<Person> {

	private Text personNameText;
	private TableViewer viewer;

	public AdressPage2(ModelFormEditor editor) {
		super(editor, "ID_Adress2", "Adress2");
	}

	@Override
	protected void onCreateUI(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		Composite body = managedForm.getForm().getBody();
		TableWrapLayout tableWrapLayout = new TableWrapLayout();
		tableWrapLayout.numColumns = 1;
		body.setLayout(tableWrapLayout);

		Composite parent = toolkit.createComposite(body);
		parent.setLayout(new GridLayout());
		parent.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

		personNameText = toolkit.createText(parent, " ", SWT.BORDER);
		personNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final int pageIndex = -1;
		final int size = 3;

		PaginationController controller = new PageableController(pageIndex,
				size);

		PaginationBannerWidget paginationHeader = new PaginationBannerWidget(
				controller, parent, SWT.NONE, toolkit);
		paginationHeader.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Table table = toolkit.createTable(parent, SWT.BORDER | SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL);
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		viewer = new TableViewer(table);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new ViewLabelProvider());

		PaginationBannerWidget paginationFooter = new PaginationBannerWidget(
				controller, parent, SWT.NONE, toolkit);
		paginationFooter.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		PaginationInfoWidget totalItemsWidget = new PaginationInfoWidget(
				controller, parent, SWT.BORDER, toolkit);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalAlignment = GridData.END;
		totalItemsWidget.setLayoutData(data);

		// controller.addPageSelectionListener(new
		// PageControllerChangedAdapter() {
		// @Override
		// public void pageSelected(int oldPageNumber, int newPageNumber,
		// PaginationController controller) {
		// refreshPersons(controller);
		// }
		// });
		new PageableStructuredViewerLoader(viewer, controller) {
			@Override
			protected Page<?> loadPage(PageableController controller) {
				
				System.err.println("TotalElements=" + controller.getTotalElements());
				System.err.println("CurrentPage=" + controller.getCurrentPage());
				System.err.println("TotalPages=" + controller.getTotalPages());

				
				return PersonService.getInstance().getPersons(controller);
			}
		};
		controller.setCurrentPage(0);
	}

	public void onBind(DataBindingContext dataBindingContext) {
		dataBindingContext.bindValue(
				SWTObservables.observeText(personNameText, SWT.Modify),
				PojoObservables.observeValue(getModelObject(), "name"));
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			if (obj instanceof Person) {
				return ((Person) obj).getName();
			}
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
}
