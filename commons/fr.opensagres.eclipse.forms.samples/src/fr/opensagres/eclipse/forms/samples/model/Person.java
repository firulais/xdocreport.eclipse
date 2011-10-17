package fr.opensagres.eclipse.forms.samples.model;

public class Person {

	public static final String NAME_PROP = "name";
	private static long i = 0;

	private boolean dirty = false;;

	private long id = -1;
	private String name;

	public void setName(String name) {
		this.name = name;
		System.err.println("name=" + name + ", uniqueId=" + getUniqueId()
				+ ", dirty=" + dirty);
	}

	public String getName() {
		return name;
	}

	public long getId() {
		if (id == -1) {
			id = i++;
		}
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public String getUniqueId() {
		return toString();
	}

	public void setUniqueId(String s) {

	}
}
