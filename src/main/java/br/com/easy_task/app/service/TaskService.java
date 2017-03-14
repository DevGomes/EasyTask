package br.com.easy_task.app.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.easy_task.app.dao.IGenericDAO;
import br.com.easy_task.app.model.Status;
import br.com.easy_task.app.model.Task;


@Transactional
@Service
public class TaskService {
	
	private IGenericDAO<Task> genericDAO;
	
	@Autowired
	public void setDao(IGenericDAO<Task> DAOInject) {
		this.genericDAO = DAOInject;
		genericDAO.setClazz(Task.class);
	}
	
	public List<Task> buscarTasks() {
		
		List<Task> tasks = null;
		
		try {
			tasks = genericDAO.listar("Task.buscarTaks");
		} catch (Exception e) {
			System.err.println("Erro ao buscar Tasks!");
		}
		
		return tasks;
	}
	
	public void salvar(Task task) {
		try {
			
			if(task.getStatus() == null) {
				task.setStatus(Status.A_FAZER);
			}
			genericDAO.salvar(task);
			
		} catch (Exception e) {
			System.err.println("Erro ao salvar a Task \n" + task);
		}
	}
	
	public void deletar(Long idTask) {
		
		if(idTask == null) {
			return ;
		}
		
		Task taskDelete = buscarTask(idTask);
		taskDelete.setDataDelecao(Calendar.getInstance());
		
		salvar(taskDelete);
	}
	
	public Task buscarTask(Long idTask) {
		
		return genericDAO.buscarEspecifico(idTask);
	}
}
