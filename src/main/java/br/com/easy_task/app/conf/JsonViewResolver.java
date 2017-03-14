package br.com.easy_task.app.conf;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		/*
		 O trecho jsonView.setPrettyPrint(true) foi adicionado para que o Jackson mantenha 
		 uma formatação amigável ao retornar o JSON dos nossos produtos. 
		 * */
		jsonView.setPrettyPrint(true);
		
		return jsonView;
	}
	

}
