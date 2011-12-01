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

public class ResumesInjector extends AbstractInjector {

	public ResumesInjector(DataInjector dataInjector) {
		super(dataInjector);
	}

	public void inject() {
		DataInjector dataInjector = getDataInjector();
		addResume(new AngeloZerrResume(dataInjector));
		addResume(new PascalLeclercqResume(dataInjector));
		addResume(new AmineBoustaResume(dataInjector));
		addResume(new JawherMoussaResume(dataInjector));
		addResume(new RalfSternbergResume(dataInjector));
		addResume(new TomSchindlResume(dataInjector));
		addResume(new LarsVogelResume(dataInjector));
		addResume(new KaiTodterResume(dataInjector));
		addResume(new GeorgeGastaldiResume(dataInjector));
		addResume(new JesseMcConnellResume(dataInjector));
		addResume(new ArnaudCogoluegnesResume(dataInjector));
		addResume(new MickaelIstriaResume(dataInjector));
		addResume(new MickaelBaronResume(dataInjector));
		addResume(new DinoCosmasResume(dataInjector));
		addResume(new NicolasRaymondResume(dataInjector));
		addResume(new YannickVimalResume(dataInjector));
	}

	private void addResume(AbstractResumeFactory factory) {
		addResume(factory.getResume());
	}

	private void addResume(Resume resume) {
		getDataInjector().getResumeService().save(resume);
	}
}
