package org.dynaresume.domain.hr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Hobby {

	public static final String LABEL_PROPERTY = "label";
   @Id
   @GeneratedValue
   private Long id;
   @Column
   private String label;

   public Long getId() {
           return id;
   }

   public void setId(long id) {
   
           this.id = id;
   
   }

   public String getLabel() {
           return label;
   }

   public void setLabel(String label) {
	   //Object oldValue = this.label;
           this.label = label;
           //firePropertyChange("label", oldValue, label);
   }

}
