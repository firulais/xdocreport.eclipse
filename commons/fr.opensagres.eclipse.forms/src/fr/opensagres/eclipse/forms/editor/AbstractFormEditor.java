package fr.opensagres.eclipse.forms.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.forms.editor.FormEditor;

public abstract class AbstractFormEditor<EditorInput extends IEditorInput> extends FormEditor {

	@Override
	protected final void addPages() {
		onBeforeAddPages();
		doAddPages();
		onAfterAddPages();
	}
	
	protected void onAfterAddPages() {
		// Do nothing
	}

	protected void onBeforeAddPages() {
		// Do nothing		
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}
	
	public void doSave(IProgressMonitor monitor) {
		onBeforeSave(monitor);
		onSave(monitor);
		onAfterSave(monitor);				
	}

	protected void onBeforeSave(IProgressMonitor monitor) {
		commitPages(true);		
	}
	
	protected void onAfterSave(IProgressMonitor monitor) {
		editorDirtyStateChanged();
	}	

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public EditorInput getEditorInput() {
		return (EditorInput) super.getEditorInput();
	}

	protected abstract void doAddPages();
	
	protected abstract void onSave(IProgressMonitor monitor);
}
