package org.dynaresume.services;

import org.dynaresume.domain.hr.Language;

public interface LanguageService {

	Iterable<Language> findAll();

}
