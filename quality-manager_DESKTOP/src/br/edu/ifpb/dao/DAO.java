package br.edu.ifpb.dao;

import br.edu.ifpb.entidades.Entidade;
import br.edu.ifpb.exceções.ClasseInválidaException;

public interface DAO {
	
	// Padrão CRUD para Database
	
	// função padrão para inserção no banco de dados
	public void creat(Entidade entidade) throws ClasseInválidaException;
	
	// função padrão para seleção no banco de dados
	public void read(Entidade entidade) throws ClasseInválidaException;
	
	// função padrão para alteração no banco de dados
	public void update(Entidade entidade) throws ClasseInválidaException;
	
	// função padrão para exclusão no banco de dados
	public void delete(Entidade entidade) throws ClasseInválidaException;
	
}
