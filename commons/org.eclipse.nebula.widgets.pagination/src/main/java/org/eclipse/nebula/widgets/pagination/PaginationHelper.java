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
package org.eclipse.nebula.widgets.pagination;

import java.text.MessageFormat;
import java.util.Locale;

import org.eclipse.swt.widgets.Table;

public class PaginationHelper {

	public static final int DOT = -1;

	public static int[] getPageIndexes(int currentPageIndex, int totalPages,
			int nbMax) {
		nbMax = nbMax > totalPages ? totalPages : nbMax;
		int[] indexes = new int[nbMax];
		if (totalPages <= nbMax) {
			// ALl indexes must be filled
			for (int i = 0; i < indexes.length; i++) {
				indexes[i] = i;
			}
		} else {
			if (currentPageIndex > (totalPages - nbMax) + 3) {
				int index = totalPages - 1;
				for (int i = indexes.length - 1; i >= 0; i--) {
					if (i == 0) {
						indexes[i] = i;
					} else if (i == 1) {
						indexes[i] = DOT;
					} else {
						indexes[i] = index--;
					}
				}
			} else {

				if (nbMax - currentPageIndex > 2) {
					for (int i = 0; i < indexes.length; i++) {
						if (i == nbMax - 1) {
							indexes[i] = totalPages - 1;
						} else if (i == nbMax - 2) {
							indexes[i] = DOT;
						} else {
							indexes[i] = i;
						}
					}
				} else {
					// Total page is > to nb max of pages to display
					// Compute list to have for instance 1 ... 10, 11, 12, 13,
					// 14,
					// ...
					// 20
					int middle = nbMax / 2;
					int index = currentPageIndex;
					int nbItems = 0;
					for (int i = middle; i > 0 && index > 0; i--) {
						if (i == 1) {
							// before last item, check if it's 2 item
							indexes[i] = index == 2 ? index : DOT;
						} else if (i == 0) {
							indexes[i] = 0;
						} else if (index > 0) {
							indexes[i] = index--;
						}
						nbItems++;
					}

					index = currentPageIndex;
					for (int i = nbItems; i < nbMax; i++) {
						if (i == nbMax - 2) {
							indexes[i] = index == totalPages ? index : DOT;
						} else if (i == nbMax - 1) {
							indexes[i] = totalPages - 1;
						} else {
							indexes[i] = index++;
						}
					}
				}
			}

		}
		return indexes;
	}

	public static AbstractPaginationTable<?> getPaginationTable(Table table) {
		return (AbstractPaginationTable<?>) table.getData("___PaginationTable");
	}

	public static void setPaginationTable(Table table,
			AbstractPaginationTable<?> paginationTable) {
		table.setData("___PaginationTable", paginationTable);
	}

	public static String getResultsText(PaginationController controller,
			Locale locale) {
		String resultsMessage = Resources.getText(
				"PaginationDecorator.results", locale);// "Results {0}-{1} of {2}";
		return getResultsText(controller, resultsMessage);
	}

	public static String getResultsText(PaginationController controller,
			String resultsMessage) {
		int start = controller.getPageOffset() + 1;
		int end = start + controller.getPageSize() - 1;
		long total = controller.getTotalElements();
		if (end > total) {
			end = (int) total;
		}
		return getResultsText(start, end, total, controller, resultsMessage);
	}

	public static String getResultsText(int start, int end, long total,
			PaginationController controller, String resultsMessage) {
		return MessageFormat.format(resultsMessage, start, end, total);
	}

}
