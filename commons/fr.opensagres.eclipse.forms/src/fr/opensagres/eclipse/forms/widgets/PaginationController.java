package fr.opensagres.eclipse.forms.widgets;

import org.eclipse.core.runtime.ListenerList;

public class PaginationController {

	private static final int DEFAULT_PAGE_INDEX = 0;
	private static final int DEFAULT_PAGE_SIZE = 10;

	private int currentPage = DEFAULT_PAGE_INDEX;
	private int itemsPerPage = DEFAULT_PAGE_SIZE;
	private long totalElements = 0;
	private ListenerList pageSelectionListeners = new ListenerList();

	public interface PageSelectionListener {
		public void pageSelected(int pageNumber, PaginationController controller);
	}

	public PaginationController(int currentPage, int itemsPerPage) {
		this.currentPage = currentPage;
		this.itemsPerPage = itemsPerPage;
	}

	public void addPageSelectionListener(PageSelectionListener listener) {
		if (listener == null) {
			throw new NullPointerException(
					"Cannot add a null page selection listener"); //$NON-NLS-1$
		}
		pageSelectionListeners.add(listener);
	}

	private void notifyListeners(int selectedPage) {
		final Object[] listeners = pageSelectionListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			final PageSelectionListener listener = (PageSelectionListener) listeners[i];
			listener.pageSelected(selectedPage, this);
		}
	}

	/**
	 * Returns if there is a previous page.
	 * 
	 * @return if there is a previous page
	 */
	public boolean hasPreviousPage() {
		return getCurrentPage() > 0;
	}

	/**
	 * Returns whether the current page is the first one.
	 * 
	 * @return
	 */
	public boolean isFirstPage() {
		return !hasPreviousPage();
	}

	/**
	 * Returns if there is a next page.
	 * 
	 * @return if there is a next page
	 */
	public boolean hasNextPage() {
		return ((getCurrentPage() + 1) * getPageSize()) < totalElements;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (this.currentPage != currentPage) {
			this.currentPage = currentPage;
			notifyListeners(currentPage);
		}
	}

	public int getPageSize() {
		return itemsPerPage;
	}

	public int getTotalPages() {

		return getPageSize() == 0 ? 0 : (int) Math.ceil((double) totalElements
				/ (double) getPageSize());
	}

	/**
	 * Returns whether the current page is the last one.
	 * 
	 * @return
	 */
	public boolean isLastPage() {

		return !hasNextPage();
	}
	
	public void setTotalElements(long totalElements) {
		if (this.totalElements != totalElements) {
			this.totalElements = totalElements;
			notifyListeners(currentPage);
		}
	}
	
	public long getTotalElements() {
		return totalElements;
	}
}
