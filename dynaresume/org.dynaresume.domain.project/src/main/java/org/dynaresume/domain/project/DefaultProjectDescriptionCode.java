package org.dynaresume.domain.project;

public enum DefaultProjectDescriptionCode {

	Context("Context"), Problems("Problems"), Solutions("Solutions"), CompanyBenefits(
			"CompanyBenefits"), ClientBenefits("ClientBenefits");

	private final String code;

	private DefaultProjectDescriptionCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static DefaultProjectDescriptionCode getCodeEnum(String code) {
		if (code == null) {
			return null;
		}
		DefaultProjectDescriptionCode codeEnum = null;
		DefaultProjectDescriptionCode[] codes = values();
		for (int i = 0; i < codes.length; i++) {
			codeEnum = codes[i];
			if (codeEnum.getCode().equals(code)) {
				return codeEnum;
			}
		}
		return null;
	}
}
