package managedBean;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

public class BreadCrumb {

	public static MenuModel initMenuModel() {

		MenuModel menuModel = new DefaultMenuModel();

		DefaultMenuItem defaultMenuItem = menuItemDefault("Default", "#",
				"default", false);

		DefaultMenuItem inicio = menuItemDefault("Início", "indexGestor.xhtml",
				"inicio", false);

		menuModel.addElement(defaultMenuItem);
		menuModel.addElement(inicio);

		return menuModel;

	}

	public static DefaultMenuItem menuItemDefault(String value, String url,
			String id, boolean active) {

		DefaultMenuItem item = new DefaultMenuItem();
		item.setValue(value);
		item.setUrl(url);
		item.setId(id);

		if (active)
			item.setStyleClass("breadcrumbcell active");
		else
			item.setStyleClass("breadcrumbcell");

		return item;

	}

	public static MenuModel edital(boolean active) {

		MenuModel menuModel = initMenuModel();

		DefaultMenuItem edital = menuItemDefault("Edital", PathRedirect.edital,
				"edital", active);

		menuModel.addElement(edital);

		return menuModel;

	}

	public static MenuModel detalhesEdital(boolean active) {

		MenuModel menuModel = edital(false);

		DefaultMenuItem detalhes = menuItemDefault("Detalhes Edital", PathRedirect.exibirEdital,
				"edital", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel cadastrarEdital(boolean active) {

		MenuModel menuModel = edital(false);

		DefaultMenuItem detalhes = menuItemDefault("Cadastrar Edital", PathRedirect.cadastrarEdital,
				"cadastraredital", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel editarEdital(boolean active) {

		MenuModel menuModel = detalhesEdital(false);

		DefaultMenuItem detalhes = menuItemDefault("Editar Edital", PathRedirect.cadastrarEdital,
				"editaredital", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel instituicaoFinanciadora(boolean active) {

		MenuModel menuModel = initMenuModel();

		DefaultMenuItem edital = menuItemDefault("Instituição Financiadora", PathRedirect.instituicaoFinanciadora,
				"instituicaoFinanciadora", active);

		menuModel.addElement(edital);

		return menuModel;

	}

	public static MenuModel detalhesInstituicaoFinanciadora(boolean active) {

		MenuModel menuModel = instituicaoFinanciadora(false);

		DefaultMenuItem detalhes = menuItemDefault("Detalhes Instituição Financiadora", PathRedirect.exibirInstituicaoFinanciadora,
				"detalhesInstituicaoFinanciadora", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel cadastrarInstituicaoFinanciadora(boolean active) {

		MenuModel menuModel = instituicaoFinanciadora(false);

		DefaultMenuItem detalhes = menuItemDefault("Cadastrar Instituição Financiadora", PathRedirect.cadastrarInstituicaoFinanciadora,
				"cadastrarInstituicaoFinanciadora", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel editarInstituicaoFinanciadora(boolean active) {

		MenuModel menuModel = detalhesInstituicaoFinanciadora(false);

		DefaultMenuItem detalhes = menuItemDefault("Editar Instituição Financiadora", PathRedirect.cadastrarInstituicaoFinanciadora,
				"cadastrarInstituicaoFinanciadora", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel programaInstitucional(boolean active) {

		MenuModel menuModel = initMenuModel();

		DefaultMenuItem edital = menuItemDefault("Programa Institucional", PathRedirect.programaInstitucional,
				"programaInstitucional", active);

		menuModel.addElement(edital);

		return menuModel;

	}

	public static MenuModel detalhesProgramaInstitucional(boolean active) {

		MenuModel menuModel = programaInstitucional(false);

		DefaultMenuItem detalhes = menuItemDefault("Detalhes Programa Institucional", PathRedirect.exibirProgramaInstitucional,
				"detalhesProgramaInstitucional", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel cadastrarProgramaInstitucional(boolean active) {

		MenuModel menuModel = programaInstitucional(false);

		DefaultMenuItem detalhes = menuItemDefault("Cadastrar Programa Institucional", PathRedirect.cadastrarProgramaInstitucional,
				"cadastrarProgramaInstitucional", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel editarProgramaInstitucional(boolean active) {

		MenuModel menuModel = detalhesProgramaInstitucional(false);

		DefaultMenuItem detalhes = menuItemDefault("Editar Programa Institucional", PathRedirect.cadastrarProgramaInstitucional,
				"editarProgramaInstitucional", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel curso(boolean active) {

		MenuModel menuModel = initMenuModel();

		DefaultMenuItem edital = menuItemDefault("Curso", PathRedirect.curso,
				"curso", active);

		menuModel.addElement(edital);

		return menuModel;

	}

	public static MenuModel detalhesCurso(boolean active) {

		MenuModel menuModel = curso(false);

		DefaultMenuItem detalhes = menuItemDefault("Detalhes Curso", PathRedirect.exibirCurso,
				"detalhesCurso", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel cadastrarCurso(boolean active) {

		MenuModel menuModel = curso(false);

		DefaultMenuItem detalhes = menuItemDefault("Cadastrar Curso", PathRedirect.cadastrarCurso,
				"cadastrarCurso", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel editarCurso(boolean active) {

		MenuModel menuModel = detalhesCurso(false);

		DefaultMenuItem detalhes = menuItemDefault("Editar Curso", PathRedirect.cadastrarCurso,
				"editarCurso", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel instituicaoBancaria(boolean active) {

		MenuModel menuModel = initMenuModel();

		DefaultMenuItem edital = menuItemDefault("Instituição Bancária", PathRedirect.instituicaoBancaria,
				"instituicaoBancaria", active);

		menuModel.addElement(edital);

		return menuModel;

	}

	public static MenuModel detalhesInstituicaoBancaria(boolean active) {

		MenuModel menuModel = instituicaoBancaria(false);

		DefaultMenuItem detalhes = menuItemDefault("Detalhes Instituição Bancária", PathRedirect.exibirInstituicaoBancaria,
				"detalhesInstituicaoBancaria", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel cadastrarInstituicaoBancaria(boolean active) {

		MenuModel menuModel = instituicaoBancaria(false);

		DefaultMenuItem detalhes = menuItemDefault("Cadastrar Instituição Bancária", PathRedirect.cadastrarInstituicaoBancaria,
				"cadastrarInstituicaoBancaria", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}
	
	public static MenuModel editarInstituicaoBancaria(boolean active) {

		MenuModel menuModel = detalhesInstituicaoBancaria(false);

		DefaultMenuItem detalhes = menuItemDefault("Editar Instituição Bancária", PathRedirect.cadastrarInstituicaoBancaria,
				"editarInstituicaoBancaria", active);

		menuModel.addElement(detalhes);

		return menuModel;

	}


}
