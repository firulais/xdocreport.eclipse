package fr.opensagres.xdocreport.eclipse.extensions.modules;

import fr.opensagres.xdocreport.eclipse.extensions.ICommandIdProvider;

public interface IReportModuleEntry extends IReportBaseModule,
		ICommandIdProvider {

	IReportModule getModule();
}
