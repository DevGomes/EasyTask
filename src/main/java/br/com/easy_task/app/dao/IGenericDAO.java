package br.com.easy_task.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface IGenericDAO<T> {
	
	T salvar(T objeto)throws Exception;
	
	List<T> buscar(String descricao, String namedQuery, String parametro);
	
	T buscarEspecifico(Long id);
	
	List<T> listar(String namedQuery)throws Exception;
	
	void setClazz(Class<T> clazzToSet);
	
	EntityManager getEntityManager();
}
