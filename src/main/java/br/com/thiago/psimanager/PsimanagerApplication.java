
package br.com.thiago.psimanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableCaching
@EnableSpringDataWebSupport
@SpringBootApplication
public class PsimanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsimanagerApplication.class, args);
	}

}
