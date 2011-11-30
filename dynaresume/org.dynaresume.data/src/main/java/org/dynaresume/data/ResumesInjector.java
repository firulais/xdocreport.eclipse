package org.dynaresume.data;

import org.dynaresume.data.resume.AbstractResumeFactory;
import org.dynaresume.data.resume.AmineBoustaResume;
import org.dynaresume.data.resume.AngeloZerrResume;
import org.dynaresume.data.resume.ArnaudCogoluegnesResume;
import org.dynaresume.data.resume.DinoCosmasResume;
import org.dynaresume.data.resume.GeorgeGastaldiResume;
import org.dynaresume.data.resume.JawherMoussaResume;
import org.dynaresume.data.resume.JesseMcConnellResume;
import org.dynaresume.data.resume.KaiTodterResume;
import org.dynaresume.data.resume.LarsVogelResume;
import org.dynaresume.data.resume.MickaelBaronResume;
import org.dynaresume.data.resume.MickaelIstriaResume;
import org.dynaresume.data.resume.NicolasRaymondResume;
import org.dynaresume.data.resume.PascalLeclercqResume;
import org.dynaresume.data.resume.RalfSternbergResume;
import org.dynaresume.data.resume.TomSchindlResume;
import org.dynaresume.data.resume.YannickVimalResume;
import org.dynaresume.domain.hr.Resume;
import org.dynaresume.services.ResumeService;

public class ResumesInjector {

	private ResumeService resumeService;
	private SkillsInjector skillsInjector;
	public void setResumeService(ResumeService resumeService) {
		this.resumeService = resumeService;
	}
	public void setSkillsInjector(SkillsInjector skillsInjector) {
		this.skillsInjector = skillsInjector;
	}

	public void inject() {
		addResume(new AngeloZerrResume(skillsInjector));
		addResume(new PascalLeclercqResume(skillsInjector));
		addResume(new AmineBoustaResume(skillsInjector));
		addResume(new JawherMoussaResume(skillsInjector));
		addResume(new RalfSternbergResume(skillsInjector));
		addResume(new TomSchindlResume(skillsInjector));
		addResume(new LarsVogelResume(skillsInjector));
		addResume(new KaiTodterResume(skillsInjector));
		addResume(new GeorgeGastaldiResume(skillsInjector));
		addResume(new JesseMcConnellResume(skillsInjector));
		addResume(new ArnaudCogoluegnesResume(skillsInjector));
		addResume(new MickaelIstriaResume(skillsInjector));
		addResume(new MickaelBaronResume(skillsInjector));
		addResume(new DinoCosmasResume(skillsInjector));
		addResume(new NicolasRaymondResume(skillsInjector));
		addResume(new YannickVimalResume(skillsInjector));
	}

	private void addResume(AbstractResumeFactory factory) {
		addResume(factory.getResume());		
	}

	private void addResume(Resume resume) {
		resumeService.save(resume);
	}
}
