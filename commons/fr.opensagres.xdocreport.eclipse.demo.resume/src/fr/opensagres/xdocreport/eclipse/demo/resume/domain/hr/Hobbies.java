package fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr;

public class Hobbies {

	/**
    * 
    */
   private static final long serialVersionUID = -4919566195826675295L;
   //@Id
   //@GeneratedValue
   private Long id;
   //@Column
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
