package fr.opensagres.xdocreport.eclipse.reporting.xdocreport.core;

import fr.opensagres.xdocreport.eclipse.reporting.core.AbstractReportProcessor;
import fr.opensagres.xdocreport.template.IContext;

public abstract class XDocReportProcessor extends AbstractReportProcessor {

	public abstract void populateContext(IContext context, Object model);

}
