package br.com.easy_task.app.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.easy_task.app.controllers.HomeContoller;
import br.com.easy_task.app.dao.GenericDAO;
import br.com.easy_task.app.dao.IGenericDAO;
import br.com.easy_task.app.service.TaskService;

@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeContoller.class, IGenericDAO.class, GenericDAO.class,
		TaskService.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolverViews = new InternalResourceViewResolver();
		resolverViews.setPrefix("/views/");
		resolverViews.setSuffix(".html");

		return resolverViews;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	/**
	 * Diz para o Spring que as requisições referentes a arquivos de script,
	 * estilo, fontes e imagens não devem ser processadas pelo servlet do
	 * Spring, mas sim pelo servlet default da aplicaçao.
	 * */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	// Para comunicação com web service de pagamento em REST
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/*
	 * Retorna objeto do tipo ViewResolver, objeto que ele gerencie e que tipo
	 * view exibir para o usuário, se JSP normal ou JSON.
	 */
	@Bean
	public ViewResolver contentNegotionViewResolver(
			ContentNegotiationManager manager) {

		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(internalResourceViewResolver()); // ViewResolver para
															// página HTML
		viewResolvers.add(new JsonViewResolver()); // ViewResolver para JSON

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(viewResolvers); // Configura a lista de view
		resolver.setContentNegotiationManager(manager); // Define o manager do
														// resolver

		return resolver;
	}
}
