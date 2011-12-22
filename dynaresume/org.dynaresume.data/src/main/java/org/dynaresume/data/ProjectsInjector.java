package org.dynaresume.data;

import java.util.HashSet;
import java.util.Set;

import org.dynaresume.domain.project.DefaultProjectDescriptionCode;
import org.dynaresume.domain.project.Project;
import org.dynaresume.domain.project.ProjectDescription;
import org.dynaresume.domain.project.ProjectDescriptionType;

public class ProjectsInjector extends AbstractInjector {

	public ProjectsInjector(DataInjector dataInjector) {
		super(dataInjector);
	}

	public void inject() {

		ProjectDescriptionType contextType = addType(
				DefaultProjectDescriptionCode.Context.getCode(), "Context");
		ProjectDescriptionType problemType = addType(
				DefaultProjectDescriptionCode.Problems.getCode(), "Problems");
		ProjectDescriptionType solutionsType = addType(
				DefaultProjectDescriptionCode.Solutions.getCode(), "Solutions");
		ProjectDescriptionType companyBenefitType = addType(
				DefaultProjectDescriptionCode.CompanyBenefits.getCode(),
				"Company benefits");
		ProjectDescriptionType clientBenefitType = addType(
				DefaultProjectDescriptionCode.ClientBenefits.getCode(),
				"Client benefits");

		Project xdocreportProject = createProject("XDocReport",
				"http://code.google.com/p/xdocreport", null);
		addDescription(
				xdocreportProject,
				contextType,
				"XDocReport means XML Document reporting. It's Java API to merge XML document created with MS Office (docx) or OpenOffice (odt), LibreOffice (odt) with a Java model to generate report and convert it if you need to another format (PDF, XHTML...).");
		save(xdocreportProject);

		Project sampleProject = createProject(
				"Création du Référentiel Personne Unique (RP)", "", "Client10");
		addDescription(
				sampleProject,
				contextType,
				"Dans le cadre du Programme XXX, le client YYY souhaite mettre en place un référentiel Client Unique pour les individus retraite / prévoyance / assurance de personnes",
				"Sur une durée de plus de deux seront progressivement injectées pour de 30 millions d’individus, après avoir été fiabilisés, normalisés au niveau adresse et dé doublonnés");
		addDescription(
				sampleProject,
				solutionsType,
				"Spécifications, Réalisation (réalisation / test unitaire / compteurs...)",
				"Qualification (statique /  dynamique /  assistance à recette)",
				"Création du chargeur  de base de données, intégrant les contrôles Métiers",
				"Implémentation de l’usine de migration ( Fiabilisation, dé doublonnage)",
				"Répétition & Bascule",
				"Alimentation du référentiel à partir de 6 modèles de données Source");
		addDescription(
				sampleProject,
				companyBenefitType,
				"Bascule finale sur Plateforme XXXXX dans les locaux du Client",
				"L’adaptabilité de la solution au contexte client ",
				"La capacité à assister le client dans les phase d’arbitrage manuel assistée par des ateliers logiciels",
				"Industrialisation de la solution");
		save(sampleProject);

		addProject("SIDoc", "", null);
	}

	private void addDescription(Project project, ProjectDescriptionType type,
			String... descriptionList) {

		Set<ProjectDescription> descriptions = project.getDescriptions();
		if (descriptions == null) {
			descriptions = new HashSet<ProjectDescription>();
			project.setDescriptions(descriptions);
		}

		String description = null;
		for (int i = 0; i < descriptionList.length; i++) {
			description = descriptionList[i];
			ProjectDescription projectDescription = new ProjectDescription();
			projectDescription.setDescription(description);
			projectDescription.setType(type);
			descriptions.add(projectDescription);
		}
	}

	private ProjectDescriptionType addType(String code, String label) {
		ProjectDescriptionType type = new ProjectDescriptionType();
		type.setCode(code);
		type.setLabel(label);
		return getDataInjector().getProjectDescriptionTypeService().save(type);
	}

	private void addProject(String name, String url, String clientName) {
		Project project = createProject(name, url, clientName);
		save(project);
	}

	private void save(Project project) {
		getDataInjector().getProjectService().save(project);
	}

	private Project createProject(String name, String url, String clientName) {
		Project project = new Project();
		project.setName(name);
		project.setURL(url);

		if (clientName != null) {
			project.setClient(getDataInjector().getClientsInjector().getClient(
					clientName));
		}

		return project;
	}

}
