package org.dynaresume.eclipse.ui.editors;

import org.dynaresume.domain.hr.Language;
import org.dynaresume.domain.hr.SkillLanguage;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.dynaresume.eclipse.ui.viewers.LanguageContentProvider;
import org.dynaresume.eclipse.ui.viewers.LanguageLabelProvider;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.rap.singlesourcing.SingleSourcingUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import fr.opensagres.eclipse.forms.editor.ModelDetailsPage;

public class LanguageDetailsPage extends ModelDetailsPage<Language> {

	private ComboViewer languageComboViewer;

	@Override
	protected void onCreateUI(Composite parent) {

		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = getManagedForm().getToolkit();

		Section languageDetailSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR);
		languageDetailSection.marginWidth = 10;
		languageDetailSection
				.setText(Messages.ResumeFormEditor_LanguagesPage_LanguageDetailsPage_title); //$NON-NLS-1$
		languageDetailSection
				.setDescription(Messages.ResumeFormEditor_LanguagesPage_LanguageDetailsPage_desc); //$NON-NLS-1$

		TableWrapData td = new TableWrapData(TableWrapData.FILL,
				TableWrapData.TOP);
		td.grabHorizontal = true;
		languageDetailSection.setLayoutData(td);

		Composite client = toolkit.createComposite(languageDetailSection);
		languageDetailSection.setClient(client);

		// Create generic content
		createBodyContent(toolkit, client);

		SingleSourcingUtils.FormToolkit_paintBordersFor(toolkit, client);
	}

	private void createBodyContent(FormToolkit toolkit, Composite parent) {
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		parent.setLayout(glayout);

		// Language label
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_LanguagesPage_LanguageDetailsPage_languageLabel_label);
		languageComboViewer = new ComboViewer(parent, SWT.READ_ONLY);
		languageComboViewer.setContentProvider(LanguageContentProvider
				.getInstance());
		languageComboViewer.setLabelProvider(LanguageLabelProvider
				.getInstance());
		languageComboViewer
				.setInput(getEditor().getLanguageService().findAll());
		languageComboViewer.getControl().setLayoutData(
				new GridData(GridData.FILL_HORIZONTAL));

	}

	protected ResumeFormEditor getEditor() {
		return (ResumeFormEditor) getModelFormPage().getEditor();
	}

	public void onBind(DataBindingContext bindingContext) {
		// Label binding
		IObservableValue languageComboObserveSelectionObserveWidget = SWTObservables
				.observeSelection(languageComboViewer.getCombo());
		IObservableValue modelLanguageLabelObserveValue = PojoObservables
				.observeValue(getModelObject(), SkillLanguage.LANGUAGE_PROPERTY
						+ "." + Language.LABEL_PROPERTY);
		bindingContext.bindValue(languageComboObserveSelectionObserveWidget,
				modelLanguageLabelObserveValue, null, null);

	}

}
