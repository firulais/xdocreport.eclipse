package org.dynaresume.domain.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Country implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -1896189210454791831L;
    @Id
    private String iso3;

    @Column
    private String label;

    public String getIso3() {
            return iso3;
    }

    public String getLabel() {
            return label;
    }

    public void setIso3(String iso3) {

            this.iso3 = iso3;

    }

    public void setLabel(String label) {
            this.label = label;
    }

}
