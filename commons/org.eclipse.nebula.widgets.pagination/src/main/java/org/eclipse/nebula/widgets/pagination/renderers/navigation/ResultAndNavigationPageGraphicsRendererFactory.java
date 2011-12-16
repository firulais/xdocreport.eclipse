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
package org.eclipse.nebula.widgets.pagination.renderers.navigation;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.nebula.widgets.pagination.renderers.CompositeRendererFactory;
import org.eclipse.nebula.widgets.pagination.renderers.navigation.graphics.BlackNavigationPageGraphicsConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.navigation.graphics.BlueNavigationPageGraphicsConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.navigation.graphics.GreenNavigationPageGraphicsConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.navigation.graphics.NavigationPageGraphicsConfigurator;
import org.eclipse.swt.widgets.Composite;

/**
 * Renderer factory to create instance of
 * {@link ResultAndNavigationPageGraphicsRenderer}.
 * 
 */
public class ResultAndNavigationPageGraphicsRendererFactory implements
		CompositeRendererFactory {

	private static final CompositeRendererFactory BLUE_FACTORY = new ResultAndNavigationPageGraphicsRendererFactory(
			BlueNavigationPageGraphicsConfigurator.getInstance());

	private static final CompositeRendererFactory GREEN_FACTORY = new ResultAndNavigationPageGraphicsRendererFactory(
			GreenNavigationPageGraphicsConfigurator.getInstance());

	private static final CompositeRendererFactory BLACK_FACTORY = new ResultAndNavigationPageGraphicsRendererFactory(
			BlackNavigationPageGraphicsConfigurator.getInstance());

	public static CompositeRendererFactory getBlueFactory() {
		return BLUE_FACTORY;
	}

	public static CompositeRendererFactory getGreenFactory() {
		return GREEN_FACTORY;
	}

	public static CompositeRendererFactory getBlackFactory() {
		return BLACK_FACTORY;
	}

	private final NavigationPageGraphicsConfigurator configurator;

	public ResultAndNavigationPageGraphicsRendererFactory(
			NavigationPageGraphicsConfigurator configurator) {
		this.configurator = configurator;
	}

	public Composite createComposite(Composite parent, int style,
			PaginationController controller) {
		return new ResultAndNavigationPageGraphicsRenderer(parent, style,
				controller, configurator);
	}
}
