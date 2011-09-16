package fr.opensagres.xdocreport.eclipse.reporting.xdocreport;

import fr.opensagres.xdocreport.eclipse.extensions.reporting.AbstractReportProcessor;
import fr.opensagres.xdocreport.template.IContext;

public abstract class XDocReportProcessor extends AbstractReportProcessor {

	public abstract void populateContext(IContext context, Object model);

}
