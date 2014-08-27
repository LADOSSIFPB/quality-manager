package br.edu.ifpb.qmanager;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

public class QManagerApplication extends Application {
	
	private Set<Object> singletons = new HashSet();
	private Set<Class<?>> empty = new HashSet();

	public QManagerApplication() {
		// ADD YOUR RESTFUL RESOURCES HERE
		this.singletons.add(new QManagerService());
	}

	public Set<Class<?>> getClasses() {
		return this.empty;
	}

	public Set<Object> getSingletons() {
		return this.singletons;
	}
}