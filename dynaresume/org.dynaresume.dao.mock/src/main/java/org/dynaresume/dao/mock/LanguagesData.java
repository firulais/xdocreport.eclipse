package org.dynaresume.dao.mock;

import java.util.HashMap;
import java.util.Map;

import org.dynaresume.domain.hr.DefaultLanguageCode;
import org.dynaresume.domain.hr.Language;

public class LanguagesData {

	static long currentId = 0;
	static final Map<Long, Language> languages;

	static {
		languages = new HashMap<Long, Language>();
		DefaultLanguageCode[] codes = DefaultLanguageCode.values();
		for (int i = 0; i < codes.length; i++) {
			addLanguage(codes[i]);
		}
	}

	private static void addLanguage(DefaultLanguageCode languageCode) {
		addLanguage(languageCode.getCode(), languageCode.name());
	}

	private static void addLanguage(String code, String label) {
		Language language = new Language();
		language.setId(getId());
		language.setCode(code);
		language.setLabel(label);
		languages.put(language.getId(), language);
	}

	public synchronized static Long getId() {
		return currentId++;
	}

	public static Language getLanguageByCode(String code) {
		for (Language language : languages.values()) {
			if (code.equals(language.getCode())) {
				return language;
			}
		}
		return null;
	}

}
