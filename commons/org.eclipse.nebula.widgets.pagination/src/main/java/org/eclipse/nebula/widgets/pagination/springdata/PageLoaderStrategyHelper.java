/*******************************************************************************
 * Copyright (C) 2011 Angelo Zerr <angelo.zerr@gmail.com>, Pascal Leclercq <pascal.leclercq@gmail.com>
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Angelo ZERR - initial API and implementation
 *     Pascal Leclercq - initial API and implementation
 *******************************************************************************/
package org.eclipse.nebula.widgets.pagination.springdata;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.widgets.pagination.LazyItemsSelectionListener;
import org.eclipse.nebula.widgets.pagination.PageChangedAdapter;
import org.eclipse.nebula.widgets.pagination.PageChangedListener;
import org.eclipse.swt.widgets.TableItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This class help you to configure a {@link PageableController} to manage
 * paginated list data in a {@link Viewer}. This helper manages the 2 following
 * strategies for pagination :
 * 
 * <ul>
 * <li>
 * load the paginated list and <strong>replace data</strong> from the viewer
 * with the new list. See
 * {@link PageLoaderStrategyHelper#loadPageAndReplaceItems(PageableController, Viewer, PageLoader)}
 * </li>
 * <li>
 * load the paginated list and <strong>add to the data</strong> of the viewer
 * the new list. See
 * {@link PageLoaderStrategyHelper#loadPageAndAddItems(PageableController, TableViewer, PageLoader)}
 * </li>
 * </ul>
 * 
 * @see http://www.springsource.org/spring-data
 * 
 */
public class PageLoaderStrategyHelper {

	// ---------------- Replace strategy

	/**
	 * This method loads the paginated list by using the given page loader
	 * {@link PageLoader} and information about pagination from the given
	 * controller {@link PageableController}. After loading paginated list
	 * returned in a Spring Data structure {@link Page}, this method :
	 * 
	 * <ul>
	 * <li>update the total elements of the given controller
	 * {@link PageableController}</li>
	 * <li>refresh the given {@link Viewer} by replacing data with the new
	 * paginated list.</li>
	 * </ul>
	 * 
	 * @param controller
	 *            the controller to use to load paginated list and update the
	 *            total elements.
	 * @param viewer
	 *            the viewer to refresh with new paginated list.
	 * @param pageLoader
	 *            the page loader used to load paginated list.
	 */
	public static void loadPageAndReplaceItems(
			final PageableController controller, final Viewer viewer,
			final PageLoader pageLoader) {
		Page<?> page = loadPageAndUpdateTotalElements(controller, pageLoader);
		// Refresh the viewer with the paginated list.
		viewer.setInput(page.getContent());
	}

	/**
	 * Create {@link PageChangedListener} with pagination "replace" strategy.
	 * See
	 * {@link PageLoaderStrategyHelper#loadPageAndReplaceItems(PageableController, Viewer, PageLoader)}
	 * for more information.
	 * 
	 * @param controller
	 *            the controller to use to load paginated list and update the
	 *            total elements.
	 * @param viewer
	 *            the viewer to refresh with new paginated list.
	 * @param pageLoader
	 *            the page loader used to load paginated list.
	 * @return
	 */
	public static PageChangedListener<PageableController> createloadPageAndReplaceItemsListener(
			final PageableController controller, final StructuredViewer viewer,
			final PageLoader pageLoader) {
		return new PageChangedAdapter<PageableController>() {
			@Override
			public void pageIndexChanged(int oldPageIndex, int newPageIndex,
					PageableController controller) {
				PageLoaderStrategyHelper.loadPageAndReplaceItems(controller,
						viewer, pageLoader);
			}

			@Override
			public void pageSizeChanged(int oldPageSize, int newPageSize,
					PageableController paginationController) {
				controller.reset();
			}

			@Override
			public void sortChanged(String oldPopertyName, String propertyName,
					int oldSortDirection, int sortDirection,
					PageableController controller) {
				controller.reset();
			}
		};
	}

	// ---------------- Add strategy

	/**
	 * This method loads the paginated list by using the given page loader
	 * {@link PageLoader} and information about pagination from the given
	 * controller {@link PageableController}. After loading paginated list
	 * returned in a Spring Data structure {@link Page}, this method :
	 * 
	 * <ul>
	 * <li>update the total elements of the given controller
	 * {@link PageableController}</li>
	 * <li>refresh the given {@link Viewer} by replacing data with the new
	 * paginated list.</li>
	 * </ul>
	 * 
	 * @param controller
	 *            the controller to use to load paginated list and update the
	 *            total elements.
	 * @param viewer
	 *            the viewer to refresh with new paginated list.
	 * @param pageLoader
	 *            the page loader used to load paginated list.
	 */
	public static void loadPageAndAddItems(final PageableController controller,
			final TableViewer viewer, final PageLoader pageLoader) {
		Page<?> page = loadPageAndUpdateTotalElements(controller, pageLoader);
		if (!page.getContent().isEmpty()) {
			viewer.add(page.getContent().toArray());
			int count = viewer.getTable().getItemCount();
			if (count > 0) {
				TableItem item = viewer.getTable().getItem(count - 1);
				item.setData(LazyItemsSelectionListener.LAST_ITEM_LOADED, true);
			}
		}
	}

	/**
	 * Create {@link PageChangedListener} with pagination "add" strategy. See
	 * {@link PageLoaderStrategyHelper#loadPageAndAddItems(PageableController, TableViewer, PageLoader)}
	 * for more information.
	 * 
	 * @param controller
	 *            the controller to use to load paginated list and update the
	 *            total elements.
	 * @param viewer
	 *            the viewer to refresh with new paginated list.
	 * @param pageLoader
	 *            the page loader used to load paginated list.
	 * @return
	 */
	public static PageChangedListener<PageableController> createloadPageAndAddItemsListener(
			final PageableController controller, final TableViewer viewer,
			final PageLoader pageLoader) {
		return new PageChangedAdapter<PageableController>() {
			@Override
			public void pageIndexChanged(int oldPageIndex, int newPageIndex,
					PageableController controller) {
				PageLoaderStrategyHelper.loadPageAndAddItems(controller,
						viewer, pageLoader);
			}

			@Override
			public void pageSizeChanged(int oldPageSize, int newPageSize,
					PageableController paginationController) {
				controller.reset();
			}

			@Override
			public void sortChanged(String oldPopertyName, String propertyName,
					int oldSortDirection, int sortDirection,
					PageableController controller) {
				controller.reset();
			}
		};
	}

	// ---------------- Utilities methods

	/**
	 * Load the paginated list and update the total element of the given
	 * controller.
	 * 
	 * @param controller
	 *            the controller to use to load paginated list and update the
	 *            total elements.
	 * @param pageLoader
	 *            the page loader used to load paginated list.
	 * @return
	 */
	public static Page<?> loadPageAndUpdateTotalElements(
			final PageableController controller, final PageLoader pageLoader) {
		// Load the paginated list.
		Page<?> page = loadPage(pageLoader, controller);
		// Update the total elements of the controller.
		controller.setTotalElements(page.getTotalElements());
		return page;
	}

	/**
	 * Load the paginated list.
	 * 
	 * @param pageLoader
	 * @param pageable
	 * @return
	 */
	public static Page<?> loadPage(PageLoader pageLoader, Pageable pageable) {
		if (pageLoader == null) {
			throw new NullPointerException("PageLoader cannot be null!");
		}
		return pageLoader.loadPage(pageable);
	}
}
