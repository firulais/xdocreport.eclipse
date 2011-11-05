package fr.opensagres.xdocreport.eclipse.demo.resume.services.impl;

import java.io.IOException;

import fr.opensagres.xdocreport.document.images.ByteArrayImageProvider;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.core.NaturalPerson;
import fr.opensagres.xdocreport.eclipse.demo.resume.domain.hr.Resume;

public class PascalResume extends Resume {

        public PascalResume() {
                // Pascal
                NaturalPerson person = new NaturalPerson();
                person.setId(ResumeServiceImpl.currentId++);
                person.setFirstName("Pascal");
                person.setLastName("Leclercq");
                person.setEmail("pascal.leclercq@gmail.com");

                super.setId(ResumeServiceImpl.currentId++);
                super.setOwner(person);
                try {
                        super.setPhoto(new ByteArrayImageProvider(Resume.class
                                        .getResourceAsStream("PascalLeclercq.jpg")));
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}
