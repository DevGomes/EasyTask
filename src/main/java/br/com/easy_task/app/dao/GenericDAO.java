package br.com.easy_task.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * Por padrão uma classe com anotação @Service é um Singleton como todos os
 * serviço utilizaram objeto da classe GenericDAO é necessario definir um
 * "BeanDefinition.SCOPE_PROTOTYPE" para cada service injetar GenericDAO com seu
 * próprio tipo.
 */
@Transactional
@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericDAO<T> implements IGenericDAO<T> {

	private Class<T> clazz;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public List<T> buscar(String descricao, String namedQuery, String parametro) {
		return (List<T>) entityManager.createNamedQuery(namedQuery, clazz)
				.setParameter(parametro, "%" + descricao + "%").getResultList();

	}

	public T buscarEspecifico(Long id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public List<T> listar(String namedQuery) throws Exception {
		return entityManager.createNamedQuery(namedQuery, clazz)
				.getResultList();
	}

	@Override
	public T salvar(T objeto) throws Exception {
		return entityManager.merge(objeto);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Class<T> getClazz() {
		return clazz;
	}

}
