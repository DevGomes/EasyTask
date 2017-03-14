package br.com.easy_task.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.easy_task.app.model.Task;
import br.com.easy_task.app.service.TaskService;


@RestController
@RequestMapping(value = TaskController.URL_ROOT)
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	protected static final String URL_ROOT = "/task";
	protected static final String URL_LISTA = "lista";
	protected static final String URL_SALVAR = "salvar";
	protected static final String URL_DELETE = "deletar/{idTask}";
	
	
	@RequestMapping(value = URL_LISTA, method = RequestMethod.GET,
			headers = "Accept=application/json")
	@ResponseBody
	public List<Task> listar() {
		
		return taskService.buscarTasks();
	}
	
	@RequestMapping(value = URL_SALVAR, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity salvar(@RequestBody Task task) throws Exception {
		
		taskService.salvar(task);
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@RequestMapping(value=URL_DELETE, method=RequestMethod.DELETE)
	@ResponseBody
	public String deletar(@PathVariable("idTask") Long idTask) throws Exception{
		
		taskService.deletar(idTask);
		
		return "";
	}
}
