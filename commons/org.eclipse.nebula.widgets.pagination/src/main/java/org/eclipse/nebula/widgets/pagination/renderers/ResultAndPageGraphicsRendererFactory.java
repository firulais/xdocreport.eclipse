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
package org.eclipse.nebula.widgets.pagination.renderers;

import org.eclipse.nebula.widgets.pagination.PaginationController;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.BlackGraphicsPageNavigationConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.BlueGraphicsPageNavigationConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.GraphicsPageNavigationConfigurator;
import org.eclipse.nebula.widgets.pagination.renderers.graphics.GreenGraphicsPageNavigationConfigurator;
import org.eclipse.swt.widgets.Composite;

public class ResultAndPageGraphicsRendererFactory implements PageRendererFactory {

	private static final PageRendererFactory BLUE_FACTORY = new ResultAndPageGraphicsRendererFactory(
			BlueGraphicsPageNavigationConfigurator.getInstance());

	private static final PageRendererFactory GREEN_FACTORY = new ResultAndPageGraphicsRendererFactory(
			GreenGraphicsPageNavigationConfigurator.getInstance());

	private static final PageRendererFactory BLACK_FACTORY = new ResultAndPageGraphicsRendererFactory(
			BlackGraphicsPageNavigationConfigurator.getInstance());

	public static PageRendererFactory getBlueFactory() {
		return BLUE_FACTORY;
	}

	public static PageRendererFactory getGreenFactory() {
		return GREEN_FACTORY;
	}

	public static PageRendererFactory getBlackFactory() {
		return BLACK_FACTORY;
	}

	private final GraphicsPageNavigationConfigurator configurator;

	public ResultAndPageGraphicsRendererFactory(GraphicsPageNavigationConfigurator configurator) {
		this.configurator = configurator;
	}

	public Composite createRenderer(PaginationController controller,
			Composite parent, int style) {
		return new ResultAndPageGraphicsRenderer(controller, parent, style,
				configurator);
	}
}
