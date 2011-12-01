package org.dynaresume.data;

public abstract class AbstractInjector {

	private final DataInjector dataInjector;

	public AbstractInjector(DataInjector dataInjector) {
		this.dataInjector = dataInjector;
	}

	public DataInjector getDataInjector() {
		return dataInjector;
	}

	abstract void inject();

}
