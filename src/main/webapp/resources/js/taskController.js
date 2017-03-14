app.controller("taskController", function($scope, taskService) {
	
	$scope.listarTasks = function() {
		taskService.buscarTasks(function(data, status, headers, config){
			$scope.tasks = data;
		}, function(data, status, headers, config){
			showNotifyMensagem("Erro ao listar tasks", "dange", "glyphicon glyphicon-home");
		});
	};
	
	$scope.salvarTask = function(task) {
		taskService.salvarTask(task, function(data, status, headers, config) {
			showNotifyMensagem("Task salva com sucesso!", "success", "glyphicon glyphicon-floppy-save")
			$scope.listarTasks();
		}, function(data, status, headers, config) {
			showNotifyMensagem("Erro ao salvar task!", "danger", "glyphicon glyphicon-remove-circle")
		});	
		
		$scope.fecharModal = "modal";
	};
	
	$scope.deletarTask = function(idTask) {
		taskService.excluir(idTask, function(data, status, headers, config) {
			showNotifyMensagem("Task deletada com sucesso!", "success", "glyphicon glyphicon-floppy-remove")
			$scope.listarTasks();
		}, function(data, status, headers, config) {
			showNotifyMensagem("Erro ao deletar task!", "danger", "glyphicon glyphicon-remove-circle")
		});
		
		$scope.fecharModal = "modal";
	};
	
	$scope.novaTask = function() {
		$scope.tituloCadEditModal='Nova Task';
		$scope.task = {};
	};
	
	$scope.setTaskDelete = function(task) {
		$scope.taskDelecao = task;
	};
	
	$scope.setTaskEdit = function(task) {
		$scope.tituloCadEditModal = "Editar Task";
		$scope.task = angular.copy(task);
	}
	
	$scope.alterarStatus = function(task, status) {
		task.status = status;
		$scope.salvarTask(task);
	}
	
});