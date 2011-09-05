package fr.opensagres.xdocreport.eclipse.utils;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.rwt.RWT;

public final class URLHelper {

	public static final String EQUAL = "=";
	public static final String AMPERSAND = "&";
	public static final String QUESTION_MARK = "?";

	private URLHelper() {
		// no instance creation
	}

	public static String getServletName() {
		String result = RWT.getRequest().getServletPath();
		if (result.startsWith("/")) {
			result = result.substring(1);
		}
		return result;
	}

	/**
	 * Returns the servlet's URL of the current RWT installation.
	 */
	public static String getURLString() {
		HttpServletRequest request = RWT.getRequest();
		StringBuffer result = new StringBuffer();
		result.append(getContextURLString());
		result.append(request.getServletPath());
		return result.toString();
	}

	/**
	 * Returns the URL to the webapp's context root of the current RWT
	 * installation.
	 */
	public static String getContextURLString() {
		HttpServletRequest request = RWT.getRequest();
		StringBuffer result = new StringBuffer();
		result.append(getServerURL());
		result.append(request.getContextPath());
		return result.toString();
	}

	// ////////////////
	// helping methods

	/**
	 * Appends the given <code>key</code> and <code>value</code> to the given
	 * buffer by prepending a question mark and separating key and value with an
	 * equals sign.
	 */
	public static void appendFirstParam(StringBuffer buffer, String key,
			String value) {
		buffer.append(QUESTION_MARK);
		buffer.append(key);
		buffer.append(EQUAL);
		buffer.append(value);
	}

	/**
	 * Appends the given <code>key</code> and <code>value</code> to the given
	 * buffer by prepending an ampersand and separating key and value with an
	 * equals sign.
	 */
	public static void appendParam(StringBuffer buffer, String key, String value) {
		buffer.append(AMPERSAND);
		buffer.append(key);
		buffer.append(EQUAL);
		buffer.append(value);
	}

	private static String getServerURL() {
		// TODO: [fappel] remove the creation of absolute addresses with
		// relative ones, this should make this method obsolete
		HttpServletRequest request = RWT.getRequest();

		// /////////////////////////////////////////////////////////////////////
		// use the following workaround to keep servlet 2.2 spec. compatibility
		String port = URLHelper.createPortPattern(request);
		StringBuffer result = new StringBuffer();
		String serverName = request.getServerName();
		result.append(request.getScheme());
		result.append("://");
		result.append(serverName);
		result.append(port);
		// /////////////////////////////////////////////////////////////////////
		return result.toString();
	}

	private static String createPortPattern(HttpServletRequest request) {
		String result = String.valueOf(request.getServerPort());
		if (result != null && !result.equals("")) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(":");
			buffer.append(result);
			result = buffer.toString();
		} else {
			result = "";
		}
		return result;
	}
}
