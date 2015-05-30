package br.edu.ifpb.ifopendoors.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class HibernateTest {

	public static void main(String[] args) {

		// Consultar porta aberta.
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		String sql = "SELECT * "
				+ " FROM tb_alocacao as al, tb_desalocacao as des "
				+ " WHERE al.room_id_sala = :number" + " AND al.dt_abertura = "
				+ " (SELECT max(al2.dt_abertura) "
				+ " FROM tb_alocacao as al2 "
				+ " WHERE al2.room_id_sala = :number)"
				+ " AND al.id_alocacao = des.open_id_alocacao";

		Query query = session.createSQLQuery(sql).setParameter("number",
				1);
		
		List result = query.list();
		
		System.out.println(result);
		
		
	}

}