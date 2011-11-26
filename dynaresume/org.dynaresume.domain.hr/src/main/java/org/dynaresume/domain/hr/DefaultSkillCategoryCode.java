package org.dynaresume.domain.hr;

public enum DefaultSkillCategoryCode {
	FunctionalSkills("Functional"), ProcessSkills("Process"), TechnicalSkills(
			"Technicals"), OSTechnicalSkills("OS"), DatabaseTechnicalSkills(
			"Database"), LangagesTechnicalSkills("Languages"), TechnologiesTechnicalSkills(
			"Technologies"), SoftwaresTechnicalSkills("Softwares"), MethodsAndToolsSkills(
			"Methods");

	private final String code;

	private DefaultSkillCategoryCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static DefaultSkillCategoryCode getCodeEnum(String code) {
		if (code == null) {
			return null;
		}
		DefaultSkillCategoryCode codeEnum = null;
		DefaultSkillCategoryCode[] codes = values();
		for (int i = 0; i < codes.length; i++) {
			codeEnum = codes[i];
			if (codeEnum.getCode().equals(code)) {
				return codeEnum;
			}
		}
		return null;
	}

}
