package org.dynaresume.data;

import java.util.LinkedHashMap;
import java.util.Map;

import org.dynaresume.domain.hr.DefaultLanguageCode;
import org.dynaresume.domain.hr.Language;

public class LanguagesInjector extends AbstractInjector {

	private final Map<String, Language> languagesByLabel = new LinkedHashMap<String, Language>();

	public LanguagesInjector(DataInjector dataInjector) {
		super(dataInjector);
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
		Language newLanguage = getDataInjector().getLanguageService().save(
				language);
		if (newLanguage != null) {
			languagesByLabel.put(newLanguage.getCode(), language);
		}
	}

	public Language getLanguageByCode(String code) {
		return languagesByLabel.get(code);
	}

}
