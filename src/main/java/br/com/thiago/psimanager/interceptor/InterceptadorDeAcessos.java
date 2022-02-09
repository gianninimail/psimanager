package br.com.thiago.psimanager.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.thiago.psimanager.service.AcessoService;

@Component
public class InterceptadorDeAcessos implements HandlerInterceptor {

	@Autowired
	private AcessoService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		Acesso acesso = new Acesso();
		
		acesso.setPath(request.getRequestURI());
		acesso.setData(LocalDateTime.now());
		
		request.setAttribute("acesso", acesso);
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		Acesso acesso = (Acesso) request.getAttribute("acesso");
		acesso.setDuracao(Duration.between(acesso.getData(), LocalDateTime.now()));
		service.inserir(acesso);
	}
	
	
}
