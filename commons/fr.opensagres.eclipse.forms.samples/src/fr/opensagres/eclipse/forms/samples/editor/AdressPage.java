package fr.opensagres.eclipse.forms.samples.editor;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
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

import fr.opensagres.eclipse.forms.editor.ModelFormEditor;
import fr.opensagres.eclipse.forms.editor.ModelFormPage;
import fr.opensagres.eclipse.forms.samples.model.Person;
import fr.opensagres.eclipse.forms.samples.services.PersonService;
import fr.opensagres.eclipse.forms.samples.services.pagination.Page;
import fr.opensagres.eclipse.forms.samples.services.pagination.PageRequest;
import fr.opensagres.eclipse.forms.widgets.PaginationController;
import fr.opensagres.eclipse.forms.widgets.PaginationController.PageSelectionListener;
import fr.opensagres.eclipse.forms.widgets.PaginationWidget;

public class AdressPage extends ModelFormPage<Person> {

	private Text personNameText;
	private TableViewer viewer;

	public AdressPage(ModelFormEditor editor) {
		super(editor, "ID_Adress", "Adress");
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

		final int pageIndex = 0;
		final int size = 3;

		PaginationController controller = new PaginationController(pageIndex,
				size);
		
		PaginationWidget paginationHeader = new PaginationWidget(controller, parent, SWT.NONE, toolkit);
		paginationHeader.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Table table = toolkit.createTable(parent, SWT.BORDER | SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL);
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		viewer = new TableViewer(table);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new ViewLabelProvider());

		PaginationWidget paginationFooter = new PaginationWidget(controller,
				parent, SWT.NONE, toolkit);
		paginationFooter.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));		
		controller.addPageSelectionListener(new PageSelectionListener() {
			
			public void pageSelected(int pageNumber, PaginationController controller) {
				refreshPersons(pageNumber, controller);				
			}
		});
			
		// Provide the input to the ContentProvider
		refreshPersons(pageIndex, controller);

	}

	private void refreshPersons(final int pageIndex, final PaginationController controller) {
		Page<Person> page = PersonService.getInstance().getPersons(
				new PageRequest(pageIndex, controller.getPageSize()));
		controller.setTotalElements(page.getTotalElements());
		System.err.println("TotalElements=" + controller.getTotalElements());
		System.err.println("CurrentPage=" + controller.getCurrentPage());
		System.err.println("TotalPages=" + controller.getTotalPages());
		viewer.setInput(page.getContent());
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
