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

import java.awt.print.Pageable;
import java.util.List;

/**
 * Classes which implement this interface provide methods which load paginated
 * list by using Spring Data {@link Pageable} information about pagination
 * (sort, page index etc).
 * 
 * <p>
 * If you wish to manage pagination with Java {@link List} in memory you can use
 * {@link PageLoaderListImpl}.
 * 
 * </p>
 * <p>
 * For better design {@link IPageLoader} should be implemented by the Service
 * Layer or DAO (Repository) layer. If you wish to manage pagination with JPA,
 * Spring Data JPA can be very helpful.
 * </p>
 * 
 * @see http://www.springsource.org/spring-data
 */
public interface IPageLoader<C extends PageableController, P> {

	/**
	 * Load the paginated list by using Spring Data {@link Pageable} information
	 * about pagination (sort, page index etc) and returns the paginated list in
	 * the Spring Data structure.
	 * 
	 * @param pageable
	 *            information about pagination.
	 * @return the Spring Data structure which contains the paginated list.
	 */
	P loadPage(C controller);
}
