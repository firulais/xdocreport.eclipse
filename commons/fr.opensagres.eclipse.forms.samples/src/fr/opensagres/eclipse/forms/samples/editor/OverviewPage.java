package fr.opensagres.eclipse.forms.samples.editor;

import java.io.IOException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.opensagres.eclipse.forms.editor.ModelFormEditor;
import fr.opensagres.eclipse.forms.editor.ModelFormPage;
import fr.opensagres.eclipse.forms.samples.model.Person;
import fr.opensagres.eclipse.forms.widgets.DateTimeControl;
import fr.opensagres.eclipse.forms.widgets.PhotoControl;
import fr.opensagres.eclipse.forms.widgets.SimpleWikiText;

public class OverviewPage extends ModelFormPage<Person> {

	private Text personNameText;
	private Label label;
	private DateTimeControl birthDaydateTime;
	private SimpleWikiText commentsText;

	public OverviewPage(ModelFormEditor editor) {
		super(editor, "ID", "Person");
	}

	@Override
	protected void onCreateUI(IManagedForm managedForm) {
		FormToolkit toolkit = managedForm.getToolkit();
		Composite body = managedForm.getForm().getBody();
		TableWrapLayout tableWrapLayout = new TableWrapLayout();
		tableWrapLayout.numColumns = 1;
		body.setLayout(tableWrapLayout);

		Composite parent = toolkit.createComposite(body);
		parent.setLayout(new GridLayout(2, false));
		parent.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));

		toolkit.createLabel(parent, "Name:");
		personNameText = toolkit.createText(parent, " ", SWT.BORDER);
		personNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		toolkit.createLabel(parent, "Unique ID:");
		label = toolkit.createLabel(parent, "");
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Date control
		toolkit.createLabel(parent, "Birthday");
		birthDaydateTime = new DateTimeControl(parent, SWT.NONE, toolkit);
		birthDaydateTime.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Photo control
		Label photoLabel = toolkit.createLabel(parent, "Photo");
		GridData data = new GridData();
		data.verticalAlignment = SWT.TOP;
		photoLabel.setLayoutData(data);
		PhotoControl photoControl = new PhotoControl(parent, SWT.NONE,
				SWT.BORDER, toolkit);
		photoControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		try {
			photoControl.setImageStream(OverviewPage.class
					.getResourceAsStream("EmptyPhoto.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Wiki comments
		toolkit.createLabel(parent, "Wiki Comments");
		commentsText = new SimpleWikiText(parent, SWT.NONE, SWT.BORDER, toolkit);
		commentsText.setLayoutData(new GridData(GridData.FILL_BOTH));

	}

	public void onBind(DataBindingContext dataBindingContext) {
		dataBindingContext.bindValue(
				SWTObservables.observeText(personNameText, SWT.Modify),
				PojoObservables.observeValue(getModelObject(), "name"));

		dataBindingContext.bindValue(SWTObservables.observeText(label),
				PojoObservables.observeValue(getModelObject(), "uniqueId"));

	}

}
