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
import fr.opensagres.eclipse.forms.widgets.pagination.spring.PageableController;
import fr.opensagres.eclipse.forms.widgets.pagination.spring.PageableTable;

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

		// 1) Generate a Component composed with:
		// a) Header banner : Hyperlinks First/Previous, Next/Last
		// b) A SWT Table
		// c) Footer banner : display total items, etc
		PageableTable paginationTable = new PageableTable(parent, SWT.NONE,
				toolkit) {
			@Override
			protected Page<?> loadPage(PageableController controller) {
				// Call the service which returns Spring Data Page structure
				// (the list to display, the total elements etc
				return PersonService.getInstance().getPersons(controller);
			}
		};
		paginationTable.setLayoutData(new GridData(GridData.FILL_BOTH));

		// 2) Initialize the table viewer
		TableViewer viewer = paginationTable.getViewer();
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new ViewLabelProvider());

		// 3) Set current page to 0 to refresh the table
		paginationTable.setCurrentPage(0);
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
