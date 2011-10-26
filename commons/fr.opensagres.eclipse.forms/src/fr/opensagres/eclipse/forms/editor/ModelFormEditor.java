package fr.opensagres.eclipse.forms.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;

import fr.opensagres.eclipse.forms.IBindableAware;

public abstract class ModelFormEditor<EditorInput extends IEditorInput, Model>
		extends AbstractFormEditor<EditorInput> {

	public static final String SHARED_DATABINDING_CONTEXT_ID = "___SharedDBC";

	protected final class DirtyFlag implements IChangeListener {

		private boolean dirty = false;

		public boolean isDirty() {
			return dirty;
		}

		public void setDirty(boolean dirty) {
			if (this.dirty != dirty) {
				this.dirty = dirty;
				editorDirtyStateChanged();
			}
		}

		public void handleChange(ChangeEvent event) {
			setDirty(true);
		}
	}

	private ChangeTracker changeTracker;
	private boolean initializeBinding;
	private final List<IBindableAware> pagesAlreadyBounded;
	private final Map<String, DataBindingContext> dataBindingContextCahe;

	private Model model;
	private DirtyFlag dirtyFlag;

	public ModelFormEditor() {
		this.initializeBinding = false;
		this.pagesAlreadyBounded = new ArrayList<IBindableAware>();
		this.dataBindingContextCahe = new HashMap<String, DataBindingContext>();
	}

	@Override
	protected void onAfterAddPages() {
		super.onAfterAddPages();

		// // Load model
		reload();
		dirtyFlag = new DirtyFlag();
		changeTracker = new ChangeTracker();
		changeTracker.addChangeListener(dirtyFlag);
	}

	private void reload() {
		model = onLoad(getEditorInput());
	}

	protected Model getModelObject() {
		return (Model) model;
	}

	protected synchronized void bind(IBindableAware page,
			DataBindingContext dataBindingContext) {
		try {
			changeTracker.setEnabled(false);
			page.onBind(dataBindingContext);
			if (!pagesAlreadyBounded.contains(page)) {
				pagesAlreadyBounded.add(page);
			}
			changeTracker.trackTargetObservables(dataBindingContext);
		} finally {
			changeTracker.setEnabled(true);
		}
	}

	/**
	 * Implement onLoad to retrieve the model object by the editor input object.
	 */
	protected abstract Model onLoad(EditorInput input);

	public DataBindingContext getDatabindingContext(String databindingContentId) {
		DataBindingContext dataBindingContext = dataBindingContextCahe
				.get(databindingContentId);
		if (dataBindingContext == null) {
			dataBindingContext = new DataBindingContext();
			dataBindingContextCahe
					.put(databindingContentId, dataBindingContext);
		}
		return dataBindingContext;
	}

	public boolean isInitializeBinding() {
		return initializeBinding;
	}

	@Override
	public boolean isDirty() {
		if (dirtyFlag.isDirty()) {
			return true;
		}
		return super.isDirty();
	}

	@Override
	protected void onSave(IProgressMonitor monitor) {
		Collection<DataBindingContext> dataBindingContexts = dataBindingContextCahe
				.values();
		for (DataBindingContext dataBindingContext : dataBindingContexts) {
			dataBindingContext.updateModels();
		}
		model = onSave(getModelObject(), monitor);
		onReload(getModelObject());

		for (DataBindingContext dataBindingContext : dataBindingContexts) {
			dataBindingContext.updateTargets();
			dataBindingContext.dispose();
		}

		for (IBindableAware page : pagesAlreadyBounded) {
			page.bind();
		}
		dirtyFlag.setDirty(false);
	}

	@Override
	public void dispose() {
		super.dispose();
		Collection<DataBindingContext> dataBindingContexts = dataBindingContextCahe
				.values();
		for (DataBindingContext dataBindingContext : dataBindingContexts) {
			dataBindingContext.dispose();
		}
		dataBindingContextCahe.clear();
		pagesAlreadyBounded.clear();
		dirtyFlag = null;
		model = null;
	}

	protected void onReload(Model modelObject) {

	}

	protected abstract Model onSave(Model modelObject, IProgressMonitor monitor);
}
