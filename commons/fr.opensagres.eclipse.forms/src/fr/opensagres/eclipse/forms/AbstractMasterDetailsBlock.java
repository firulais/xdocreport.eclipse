package fr.opensagres.eclipse.forms;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import fr.opensagres.eclipse.forms.internal.ImageResources;

public abstract class AbstractMasterDetailsBlock extends MasterDetailsBlock {

	@Override
	protected final void createMasterPart(IManagedForm managedForm,
			Composite parent) {
		onBeforeUI(managedForm, parent);
		onCreateUI(managedForm, parent);
		onAfterUI(managedForm, parent);
	}

	protected void onBeforeUI(IManagedForm managedForm, Composite parent) {

	}

	protected void onAfterUI(IManagedForm managedForm, Composite parent) {

	}

	protected abstract void onCreateUI(IManagedForm managedForm,
			Composite parent);
	
	protected void createToolBarActions(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		Action haction = new Action("hor", Action.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				sashForm.setOrientation(SWT.HORIZONTAL);
				form.reflow(true);
			}
		};
		haction.setChecked(true);
		//		haction.setToolTipText(Messages.getString("ScrolledPropertiesBlock.horizontal")); //$NON-NLS-1$
		haction.setImageDescriptor(ImageResources
				.getImageDescriptor(ImageResources.IMG_TH_HORIZONTAL));
		Action vaction = new Action("ver", Action.AS_RADIO_BUTTON) { //$NON-NLS-1$
			public void run() {
				sashForm.setOrientation(SWT.VERTICAL);
				form.reflow(true);
			}
		};
		vaction.setChecked(false);
		//		vaction.setToolTipText(Messages.getString("ScrolledPropertiesBlock.vertical")); //$NON-NLS-1$
		vaction.setImageDescriptor(ImageResources
				.getImageDescriptor(ImageResources.IMG_TH_VERTICAL));
		form.getToolBarManager().add(haction);
		form.getToolBarManager().add(vaction);
	}
}
