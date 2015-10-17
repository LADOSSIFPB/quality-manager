package br.edu.ifpb.qmanager.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class QManagerApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public QManagerApplication() {
		
		// Serviços
		this.singletons.add(new QManagerCadastrar());
		this.singletons.add(new QManagerConsultar());
		this.singletons.add(new QManagerEditar());
		this.singletons.add(new QManagerRemover());
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