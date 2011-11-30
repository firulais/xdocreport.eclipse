package org.dynaresume.data;

import javax.sql.DataSource;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		ServiceTracker tracker = new ServiceTracker(context,
				DataSource.class.getName(), null);
	}

	public void stop(BundleContext context) throws Exception {
		
	}

}
