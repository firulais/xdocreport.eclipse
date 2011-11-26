package org.dynaresume.dao.mock;

import java.util.HashMap;
import java.util.Map;

import org.dynaresume.domain.hr.DefaultSkillCategoryCode;
import org.dynaresume.domain.hr.Skill;
import org.dynaresume.domain.hr.SkillCategory;

public class SkillsData {

	static long currentId = 0;

	public static final SkillCategory functionalSkills;
	public static final SkillCategory processSkills;
	public static final SkillCategory technicalSkills;
	public static final SkillCategory osTechnicalSkills;
	public static final SkillCategory databaseTechnicalSkills;
	public static final SkillCategory langagesTechnicalSkills;
	public static final SkillCategory technologiesTechnicalSkills;
	public static final SkillCategory softwaresTechnicalSkills;
	public static final SkillCategory methodsAndToolsSkills;

	static final Map<Long, SkillCategory> categories;
	static final Map<Long, Skill> skills;
	private static Map<String, Skill> skillsByLabel;

	static {
		skills = new HashMap<Long, Skill>();
		skillsByLabel = new HashMap<String, Skill>();
		categories = new HashMap<Long, SkillCategory>();

		functionalSkills = addCategory(
				DefaultSkillCategoryCode.FunctionalSkills.getCode(),
				"Functional skills", null);
		processSkills = addCategory(
				DefaultSkillCategoryCode.ProcessSkills.getCode(),
				"Process skills", null);

		technicalSkills = addCategory(
				DefaultSkillCategoryCode.TechnicalSkills.getCode(),
				"Technical skills", null);
		osTechnicalSkills = addCategory(
				DefaultSkillCategoryCode.OSTechnicalSkills.getCode(), "OS",
				technicalSkills);
		databaseTechnicalSkills = addCategory(
				DefaultSkillCategoryCode.DatabaseTechnicalSkills.getCode(),
				"Database", technicalSkills);
		langagesTechnicalSkills = addCategory(
				DefaultSkillCategoryCode.LangagesTechnicalSkills.getCode(),
				"Langages", technicalSkills);
		technologiesTechnicalSkills = addCategory(
				DefaultSkillCategoryCode.TechnologiesTechnicalSkills.getCode(),
				"Technologies", technicalSkills);
		softwaresTechnicalSkills = addCategory(
				DefaultSkillCategoryCode.SoftwaresTechnicalSkills.getCode(),
				"Softwares", technicalSkills);

		methodsAndToolsSkills = addCategory(
				DefaultSkillCategoryCode.MethodsAndToolsSkills.getCode(),
				"Methods and associated tools", null);

		Skill javaSkill = addSkill("Java", null);
		addSkill("Spring", javaSkill, "", "http://www.springsource.org/");
		addSkill(
				"OSGi",
				javaSkill,
				"The OSGi technology is a set of specifications that define a dynamic component system for Java. These specifications enable a development model where applications are (dynamically) composed of many different (reusable) components. The OSGi specifications enable components to hide their implementations from other components while communicating through services, which are objects that are specifically shared between components. This surprisingly simple model has far reaching effects for almost any aspect of the software development process."
						+ "\n\nThough components have been on the horizon for a long time, so far they failed to make good on their promises. OSGi is the first technology that actually succeeded with a component system that is solving many real problems in software development. Adopters of OSGi technology see significantly reduced complexity in almost all aspects of development. Code is easier to write and test, reuse is increased, build systems become significantly simpler, deployment is more manageable, bugs are detected early, and the runtime provides an enormous insight into what is running. Most important, it works as is testified by the wide adoption and use in popular applications like Eclipse and Spring."
						+ "\n\nWe developed the OSGi technology to create a collaborative software environment. We were not looking for the possibility to run multiple applications in a single VM. Application servers do that already (though they were not yet around when we started in 1998). No, our problem was harder. We wanted an application to emerge from putting together different reusable components that had no a-priori knowledge of each other. Even harder, we wanted that application to emerge from dynamically assembling a set of components. For example, you have a home server that is capable of managing your lights and appliances. A component could allow you to turn on and off the light over a web page. Another component could allow you to control the appliances via a mobile text message. The goal was to allow these other functions to be added without requiring that the developers had intricate knowledge of each other and let these components be added independently. ",
				"http://www.osgi.org/Main/HomePage");

		Skill eclipseSkill = addSkill("Eclipse", javaSkill);
		addSkill(
				"Eclipse RCP",
				eclipseSkill,
				"While the Eclipse platform is designed to serve as an open tools platform, it is architected so that its components could be used to build just about any client application. The minimal set of plug-ins needed to build a rich client application is collectively known as the Rich Client Platform. ",
				"http://wiki.eclipse.org/index.php/Rich_Client_Platform");
		addSkill(
				"Eclipse RAP",
				eclipseSkill,
				"The Rich Ajax Platform lets you build rich, Ajax-enabled Web applications by using the Eclipse development model, plug-ins with the well known Eclipse workbench extension points and a widget toolkit with SWT API. Existing RCP applications can be run as Web applications with only minor changes. ",
				"http://www.eclipse.org/rap/");
		addSkill(
				"EMF",
				eclipseSkill,
				"The EMF project is a modeling framework and code generation facility for building tools and other applications based on a structured data model. From a model specification described in XMI, EMF provides tools and runtime support to produce a set of Java classes for the model, along with a set of adapter classes that enable viewing and command-based editing of the model, and a basic editor.",
				"http://eclipse.org/modeling/emf/");
		addSkill(
				"GEF",
				eclipseSkill,
				"The Graphical Editing Framework (GEF) provides technology to create rich graphical editors and views for the Eclipse Workbench UI. It bundles three components:"
						+ "\n\nDraw2d (org.eclipse.draw2d) - A layout and rendering toolkit for displaying graphics on an SWT Canvas."
						+ "\n\nGEF (MVC) (org.eclipse.gef) - An interactive model-view-controler (MVC) framework, which fosters the implementation of SWT-based tree and Draw2d-based graphical editors for the Eclipse Workbench UI."
						+ "\n\nZest (org.eclipse.zest) - A visualization toolkit based on Draw2d, which enables implementation of graphical views for the Eclipse Workbench UI.",
				"http://www.eclipse.org/gef/");
		addSkill(
				"GMF",
				eclipseSkill,
				"The Eclipse Graphical Modeling Project (GMP) provides a set of generative components and runtime infrastructures for developing graphical editors based on EMF and GEF. ",
				"http://www.eclipse.org/modeling/gmp/");

		Skill jeeSkill = addSkill("JEE", javaSkill);
		addSkill("JSP", jeeSkill);
		Skill webservicesSkill = addSkill("WebServices (Java)", jeeSkill);
		addSkill(
				"CXF",
				webservicesSkill,
				"Apache CXF is an open source services framework. CXF helps you build and develop services using frontend programming APIs, like JAX-WS and JAX-RS. These services can speak a variety of protocols such as SOAP, XML/HTTP, RESTful HTTP, or CORBA and work over a variety of transports such as HTTP, JMS or JBI.",
				"http://cxf.apache.org/");
		addSkill("Axis 1", webservicesSkill, "", "http://axis.apache.org/axis/");
		addSkill("Axis 2", webservicesSkill, "",
				"http://axis.apache.org/axis2/java/core/");
		Skill fwkJEESkill = addSkill("Framework (JEE)", jeeSkill);
		addSkill("Play!", fwkJEESkill);
		addSkill("JBoss Seam", fwkJEESkill);
		addSkill("Struts 1", fwkJEESkill);
		addSkill("Struts 2", fwkJEESkill);
		addSkill("Wicket", fwkJEESkill);
		addSkill(
				"GWT",
				fwkJEESkill,
				"Google Web Toolkit (GWT) is a development toolkit for building and optimizing complex browser-based applications. GWT is used by many products at Google, including Google AdWords and Orkut. It's open source, completely free, and used by thousands of developers around the world. ",
				"http://code.google.com/intl/fr/webtoolkit/");
		Skill jeeServerSkill = addSkill("JEE WebServer", jeeSkill);
		addSkill("Tomcat", jeeServerSkill);
		addSkill(
				"Jetty",
				jeeServerSkill,
				"Jetty provides an Web server and javax.servlet container, plus support for Web Sockets, OSGi, JMX, JNDI, JASPI, AJP and many other integrations. These components are open source and available for commercial use and distribution. "
						+ "\n\nJetty is used in a wide variety of projects and products. Jetty can be embedded in devices, tools, frameworks, application servers, and clusters. See the Jetty Powered page for more uses of Jetty."
						+ "\n\nThe core Jetty project is hosted by the Eclipse Foundation. The codehaus provides Jetty accessories , integrations, and extensions, as well as hosting older versions of Jetty. See the About page for information about the project structure.",
				"http://www.eclipse.org/jetty/");
		addSkill("WebSphere", jeeServerSkill);
		addSkill("WebLogic", jeeServerSkill);
		addSkill("JBoss", jeeServerSkill);
		Skill javaScriptSkill = addSkill("JavaScript", null);
		Skill fwkJavaScriptSkill = addSkill("Framework (Javascript)",
				javaScriptSkill);
		addSkill("Dojo", fwkJavaScriptSkill);
		addSkill("Qooxdoo", fwkJavaScriptSkill);
		addSkill("JQuery", fwkJavaScriptSkill);

		Skill dotNetSkill = addSkill(".Net", null);
		addSkill("C#", dotNetSkill);
		addSkill("VB.Net", dotNetSkill);
		addSkill("XAML", dotNetSkill);

		Skill xmlSkill = addSkill(
				"XML",
				null,
				"Extensible Markup Language (XML) is a simple, very flexible text format derived from SGML (ISO 8879)",
				"http://www.w3.org/XML/");
		addSkill("XSD", xmlSkill, "", "http://www.w3.org/XML/Schema");
		addSkill(
				"XSL",
				xmlSkill,
				"XSLT is a language for transforming XML documents into other XML documents.",
				"http://www.w3.org/TR/xslt");
		addSkill(
				"XQuery",
				xmlSkill,
				"XML is a versatile markup language, capable of labeling the information content of diverse data sources including structured and semi-structured documents, relational databases, and object repositories. A query language that uses the structure of XML intelligently can express queries across all these kinds of data, whether physically stored in XML or viewed as XML via middleware. This specification describes a query language called XQuery, which is designed to be broadly applicable across many types of XML data sources",
				"http://www.w3.org/TR/xquery/");
		addSkill(
				"XForms",
				xmlSkill,
				"XForms is an XML application that represents the next generation of forms for the Web.",
				"http://www.w3.org/TR/xforms/");

		Skill osSkill = addSkill("OS", null);
		addSkill("Windows", osSkill);
		addSkill("Linux", osSkill);
		addSkill("Mac OS", osSkill);

		Skill databaseSkill = addSkill("Database", null);
		Skill oracleDatabaseSkill = addSkill("Oracle", databaseSkill);
		addSkill("Oracle 8i", oracleDatabaseSkill);
		addSkill("Oracle 9i", oracleDatabaseSkill);
		addSkill("Oracle 10g", oracleDatabaseSkill);
		addSkill("SQL Server", databaseSkill);
		addSkill("MySQL", databaseSkill);
		addSkill("KTBS", databaseSkill);
		addSkill("Postgres", databaseSkill);
		addSkill("Derby", databaseSkill);
		addSkill("H2", databaseSkill);

	}

	private static Skill addSkill(String label, Skill parent) {
		return addSkill(label, parent, null, null);
	}

	private static Skill addSkill(String label, Skill parent,
			String description, String url) {
		Skill skill = new Skill();
		skill.setId(getId());
		skill.setName(label);
		skill.setDescription(description);
		skill.setURL(url);
		skill.setParent(parent);
		addSkill(skill);
		return skill;
	}

	private static SkillCategory addCategory(String code, String label,
			SkillCategory parent) {
		SkillCategory skill = new SkillCategory();
		// skill.setChildren(new ArrayList<SkillCategory>());
		skill.setId(getId());
		skill.setLabel(label);
		skill.setCode(code);
		if (parent != null) {
			skill.setParent(parent);
			// parent.getChildren().add(skill);
		}
		addCategory(skill);
		return skill;
	}

	private static void addSkill(Skill skill) {
		skills.put(skill.getId(), skill);
		skillsByLabel.put(skill.getName(), skill);
	}

	private static void addCategory(SkillCategory category) {
		categories.put(category.getId(), category);
	}

	public synchronized static Long getId() {
		return currentId++;
	}

	public static Skill getSkillByLabel(String label) {
		return skillsByLabel.get(label);
	}
}