
var app = angular.module("task-app", ['ngRoute']);

app.value("urlBaseTask", "/easy-task/task/");

$(document).ready(function(){
	showNotifyMensagem("Bem-vindo ao Easy Task", "info", "glyphicon glyphicon-home");
});

function showNotifyMensagem(mensagem, tipoMensagem, icone) {
	$.notify({
    	icon: icone,
    	message: mensagem
    },{
    	// settings
        type: tipoMensagem,
        timer: 2000,
        placement: {
    		from: "top",
    		align: "right"
    	},
    	animate: {
    		enter: 'animated fadeInDown',
    		exit: 'animated fadeOutUp'
    	}
    });
}
