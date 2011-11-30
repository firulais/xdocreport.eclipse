package org.dynaresume.dao.mock;

import org.dynaresume.dao.LanguageDao;
import org.dynaresume.domain.hr.Language;
import org.springframework.stereotype.Repository;

@Repository("languageDao")
public class MockLanguageDao extends AbstractDaoMock<Language> implements
		LanguageDao {

	@Override
	public Language save(Language model) {
		// TODO Auto-generated method stub
		return super.save(model);
	}
	
	protected Language clone(Language language) {
		Language newLanguage = new Language();
		newLanguage.setId(language.getId());
		newLanguage.setCode(language.getCode());
		newLanguage.setLabel(language.getLabel());
		return newLanguage;
	}

}
