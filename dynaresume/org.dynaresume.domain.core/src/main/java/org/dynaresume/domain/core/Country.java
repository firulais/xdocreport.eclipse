package org.dynaresume.domain.core;

public class Country {

	/**
     * 
     */
    private static final long serialVersionUID = -1896189210454791831L;
    //@Id
    private String iso3;

    public String getIso3() {
            return iso3;
    }

    public void setIso3(String iso3) {

            this.iso3 = iso3;

    }

    public String getLabel() {
            return label;
    }

    public void setLabel(String label) {

            this.label = label;

    }

    //@Column
    private String label;

}
