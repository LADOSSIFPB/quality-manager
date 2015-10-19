package br.edu.ifpb.qmanager.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import controller.AreaController;
import controller.CampusController;
import controller.CargosController;
import controller.ChatController;
import controller.CursoController;
import controller.DepartamentoController;
import controller.EditalController;
import controller.EntidadeController;
import controller.InstituicaoBancariaController;
import controller.InstituicaoFinanciadoraController;
import controller.ParticipacaoController;
import controller.PessoaController;
import controller.ProgramaInstitucionalController;
import controller.ProjetoController;
import controller.ServerController;
import controller.TitulacaoController;

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