package org.dynaresume.dao.mock;

import java.util.Map;

import org.dynaresume.dao.LanguageDao;
import org.dynaresume.domain.hr.Language;
import org.springframework.stereotype.Repository;

@Repository("languageDao")
public class MockLanguageDao extends AbstractDaoMock<Language> implements
		LanguageDao {

	private final Map<Long, Language> languages = LanguagesData.languages;

	public Iterable<Language> findAll() {
		return languages.values();
	}

	public Language findOne(Long id) {
		Language language = languages.get(id);
		if (language != null) {
			return clone(language);
		}
		return languages.get(id);
	}

	public Language save(Language language) {
		if (language.getId() == null) {
			language.setId(LanguagesData.getId());
		}
		languages.put(language.getId(), language);
		return clone(language);
	}

	private Language clone(Language language) {
		Language newLanguage = new Language();
		newLanguage.setId(language.getId());
		newLanguage.setCode(language.getCode());
		newLanguage.setLabel(language.getLabel());
		return newLanguage;
	}

}
