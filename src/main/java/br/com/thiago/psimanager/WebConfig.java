package br.com.thiago.psimanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.thiago.psimanager.interceptor.InterceptadorDeAcessos;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Autowired
	private InterceptadorDeAcessos intercept;
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(intercept).addPathPatterns("/**");
	}
}
