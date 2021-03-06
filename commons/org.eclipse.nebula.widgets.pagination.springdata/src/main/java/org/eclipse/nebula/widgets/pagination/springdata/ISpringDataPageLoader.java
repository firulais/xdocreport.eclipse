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

import org.eclipse.nebula.widgets.pagination.IPageLoader;
import org.springframework.data.domain.Page;

/**
 * Spring Data {@link Page} page loader.
 * 
 * @param <T>
 */
public interface ISpringDataPageLoader<T> extends IPageLoader<Page<T>> {

}
