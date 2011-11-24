package org.dynaresume.eclipse.ui.editors;

import java.net.MalformedURLException;
import java.net.URL;

import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillResume;
import org.dynaresume.eclipse.ui.internal.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

import fr.opensagres.eclipse.forms.editor.ModelEditorInput;

public class SkillDetailsPage extends AbstractSkillDetailsPage implements
		IHyperlinkListener {

	private Hyperlink skillNameHyperlink;
	private Label skillDescriptionLabel;
	private Hyperlink skillURLLabel;
	private Composite parent;

	@Override
	protected void createBodyContent(FormToolkit toolkit, Composite parent) {
		this.parent = parent;
		GridLayout glayout = new GridLayout();
		glayout.numColumns = 2;
		parent.setLayout(glayout);

		// Skill Name
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_skillLabel_label,
				SWT.SINGLE);
		skillNameHyperlink = toolkit.createHyperlink(parent, "", SWT.SINGLE);
		skillNameHyperlink
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		skillNameHyperlink.addHyperlinkListener(this);

		// URL
		toolkit.createLabel(
				parent,
				Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_skillURL_label);
		skillURLLabel = toolkit.createHyperlink(parent, "", SWT.SINGLE);
		skillURLLabel.addHyperlinkListener(this);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.widthHint = 150;
		skillURLLabel.setLayoutData(gridData);

		// Description
		Label descriptionLabel = toolkit
				.createLabel(
						parent,
						Messages.ResumeFormEditor_SkillsPage_SkillDetailsPage_skillDescription_label);
		GridData gd_photoLabel = new GridData();
		gd_photoLabel.verticalAlignment = SWT.TOP;
		descriptionLabel.setLayoutData(gd_photoLabel);
		skillDescriptionLabel = toolkit.createLabel(parent, "", SWT.WRAP);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.widthHint = 150;
		skillDescriptionLabel.setLayoutData(gridData);
	}

	public void onBind(DataBindingContext bindingContext) {

		// Name binding
		// IObservableValue skillLabelObserveTextObserveWidget = SWTObservables
		// .observeText(skillNameLabel);
		// IObservableValue modelSkillLabelObserveValue = PojoObservables
		// .observeValue(getModelObject(), SkillResume.SKILL_PROPERTY
		// + "." + Skill.NAME_PROPERTY);
		// bindingContext.bindValue(skillLabelObserveTextObserveWidget,
		// modelSkillLabelObserveValue, null, null);
		skillNameHyperlink.setText(getModelObject().getSkill().getName());

		// Description binding
		IObservableValue skillDescriptionObserveTextObserveWidget = SWTObservables
				.observeText(skillDescriptionLabel);
		IObservableValue modelSkillDescriptionObserveValue = PojoObservables
				.observeValue(getModelObject(), SkillResume.SKILL_PROPERTY
						+ "." + Skill.DESCRIPTION_PROPERTY);
		bindingContext.bindValue(skillDescriptionObserveTextObserveWidget,
				modelSkillDescriptionObserveValue, null, null);

		// URL binding
		String url = getModelObject().getSkill().getURL();
		if (url != null) {
			skillURLLabel.setText(url);
			skillURLLabel.setEnabled(true);
		} else {
			skillURLLabel.setText("");
			skillURLLabel.setEnabled(false);
		}
		
		// IObservableValue skillURLObserveTextObserveWidget = SWTObservables
		// .observeText(skillURLLabel);
		// IObservableValue modelSkillURLObserveValue = PojoObservables
		// .observeValue(getModelObject(), SkillResume.SKILL_PROPERTY
		// + "." + Skill.URL_PROPERTY);
		// bindingContext.bindValue(skillURLObserveTextObserveWidget,
		// modelSkillURLObserveValue, null, null);
	}

	public void linkActivated(HyperlinkEvent e) {
		if (e.widget.equals(skillNameHyperlink)) {
			handleSkillNameHyperlink(e);
		} else {
			handleSkillURLHyperlink(e);
		}
	}

	private void handleSkillNameHyperlink(HyperlinkEvent e) {
		IWorkbenchPage page = super.getModelFormPage().getSite()
				.getWorkbenchWindow().getActivePage();
		try {
			page.openEditor(new ModelEditorInput(getModelObject().getSkill()) {
				public String getName() {
					// TODO Auto-generated method stub
					return "Skill";
				}

				public String getToolTipText() {
					// TODO Auto-generated method stub
					return "Skill";
				}
			}, "org.dynaresume.admin.eclipse.ui.skill.editors.SkillFormEditor",
					false);
		} catch (PartInitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void handleSkillURLHyperlink(HyperlinkEvent e) {
		try {
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser()
					.openURL(new URL(e.getLabel()));
		} catch (PartInitException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}

	public void linkEntered(HyperlinkEvent e) {
		// Do nothing
	}

	public void linkExited(HyperlinkEvent e) {
		// Do nothing
	}

}
