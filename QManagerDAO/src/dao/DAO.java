package dao;

import entidades.EntidadeIF;
import excecoes.ClasseInvalidaException;

public interface DAO {
	
	/* Padrões do CRUD */
	/* Cada classe do pacote DAO implementará essa interface.
	 * Cabe a cada classe cliente verificar se o objeto de entrada corresponde ao seu tipo próprio,
	 * visto que todas as classes estão abstraídas pela interface Entidade. Por essa razão, as funções
	 * desse CRUD podem lançar a exceção ClasseInvalidaException
	 * */
	
	public void creat(EntidadeIF entidade) throws ClasseInvalidaException;
	
	public void read(EntidadeIF entidade) throws ClasseInvalidaException;
	
	public void update(EntidadeIF entidade) throws ClasseInvalidaException;
	
	public void delete(EntidadeIF entidade) throws ClasseInvalidaException;
	
}
