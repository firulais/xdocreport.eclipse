package org.dynaresume.domain.hr;

public enum DefaultLanguageCode {

	French("FR"), English("EN"), German("GE");

	private final String code;

	private DefaultLanguageCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static DefaultLanguageCode getCodeEnum(String code) {
		if (code == null) {
			return null;
		}
		DefaultLanguageCode codeEnum = null;
		DefaultLanguageCode[] codes = values();
		for (int i = 0; i < codes.length; i++) {
			codeEnum = codes[i];
			if (codeEnum.getCode().equals(code)) {
				return codeEnum;
			}
		}
		return null;
	}
}
