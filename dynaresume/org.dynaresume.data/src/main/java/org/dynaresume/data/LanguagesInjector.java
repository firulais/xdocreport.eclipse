package org.dynaresume.data;

import org.dynaresume.domain.hr.DefaultLanguageCode;
import org.dynaresume.domain.hr.Language;
import org.dynaresume.services.LanguageService;

public class LanguagesInjector {

	private LanguageService languageService;

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	public void inject() {
		DefaultLanguageCode[] codes = DefaultLanguageCode.values();
		for (int i = 0; i < codes.length; i++) {
			addLanguage(codes[i]);
		}
	}

	private void addLanguage(DefaultLanguageCode languageCode) {
		addLanguage(languageCode.getCode(), languageCode.name());
	}

	private void addLanguage(String code, String label) {
		Language language = new Language();
		// language.setId(getId());
		language.setCode(code);
		language.setLabel(label);
		languageService.save(language);
	}

	// public Language getLanguageByCode(String code) {
	// for (Language language : languages.values()) {
	// if (code.equals(language.getCode())) {
	// return language;
	// }
	// }
	// return null;
	// }

}
