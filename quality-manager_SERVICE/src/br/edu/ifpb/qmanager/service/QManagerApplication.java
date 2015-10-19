package br.edu.ifpb.qmanager.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.edu.ifpb.qmanager.controller.AreaController;
import br.edu.ifpb.qmanager.controller.CampusController;
import br.edu.ifpb.qmanager.controller.CargosController;
import br.edu.ifpb.qmanager.controller.ChatController;
import br.edu.ifpb.qmanager.controller.CursoController;
import br.edu.ifpb.qmanager.controller.DepartamentoController;
import br.edu.ifpb.qmanager.controller.EditalController;
import br.edu.ifpb.qmanager.controller.EntidadeController;
import br.edu.ifpb.qmanager.controller.InstituicaoBancariaController;
import br.edu.ifpb.qmanager.controller.InstituicaoFinanciadoraController;
import br.edu.ifpb.qmanager.controller.ParticipacaoController;
import br.edu.ifpb.qmanager.controller.PessoaController;
import br.edu.ifpb.qmanager.controller.ProgramaInstitucionalController;
import br.edu.ifpb.qmanager.controller.ProjetoController;
import br.edu.ifpb.qmanager.controller.ServerController;
import br.edu.ifpb.qmanager.controller.TitulacaoController;

public class QManagerApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public QManagerApplication() {
		
		// Serviços
		this.singletons.add(new AreaController());
		this.singletons.add(new CampusController());
		this.singletons.add(new CargosController());
		this.singletons.add(new ChatController());
		this.singletons.add(new CursoController());
		this.singletons.add(new DepartamentoController());
		this.singletons.add(new EditalController());
		this.singletons.add(new EntidadeController());
		this.singletons.add(new InstituicaoFinanciadoraController());
		this.singletons.add(new InstituicaoBancariaController());
		this.singletons.add(new ParticipacaoController());
		this.singletons.add(new PessoaController());
		this.singletons.add(new ProgramaInstitucionalController());
		this.singletons.add(new ProjetoController());
		this.singletons.add(new ServerController());
		this.singletons.add(new TitulacaoController());
		
		
		this.singletons.add(new UploadFileQManager());
		
		// Verificação dos serviços
		this.singletons.add(new QManagerRestIndex());	
		this.singletons.add(new OverviewResourceServices());
	}

	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}