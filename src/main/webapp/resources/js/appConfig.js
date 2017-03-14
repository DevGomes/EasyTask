
//Criar uma factory ou servive para acesso ao servidor
app.service("taskService", function($http, urlBaseTask) {
	
	this.buscarTasks = function(successCallback, errorCallback) {
		$http.get(urlBaseTask + "lista")
			.success(successCallback)
			.error(errorCallback);
	}
	
	this.salvarTask = function(objTask, successCallback, errorCallback) {
		$http.post(urlBaseTask + "salvar", objTask)
			.success(successCallback)
			.error(errorCallback);
	}
	
	this.excluir = function(idTask, successCallback, errorCallback) {
		$http.delete(urlBaseTask + "deletar/" + idTask)
		.success(successCallback)
		.error(errorCallback);
	}
});

// Definição das rotas
app.config(function($routeProvider){
	
	$routeProvider.when("/taskList", {
		controller: "taskController",
		templateUrl: "views/task/tasks.html"
	}) // Task
	.otherwise({
		redirectTo: "/"
	});
	
});
