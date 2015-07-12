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

}
