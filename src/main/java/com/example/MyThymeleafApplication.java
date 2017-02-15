package com.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
public class MyThymeleafApplication {

	@RequestMapping("/")
	public String index(Model model) {
		Person single = new Person("xx", 11);
		
		List<Person> people = new ArrayList<>();
		Person p1 = new Person("xx", 11);
		Person p2 = new Person("yy", 12);
		Person p3 = new Person("zz", 13);
		people.add(p1);
		people.add(p2);
		people.add(p3);
		
		model.addAttribute("singlePerson", single);
		model.addAttribute("people", people);
		
		return "index";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MyThymeleafApplication.class, args);
	}
	
	/**
	 * 1.通用文件内配置servlet容器，当前类要声明为static
	 * @author WQXia
	 *
	 */
//	@Component
//	public static class CustomServletContainer implements EmbeddedServletContainerCustomizer {
//
//		@Override
//		public void customize(ConfigurableEmbeddedServletContainer container) {
//			container.setPort(8089);
//			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
//			container.setSessionTimeout(10, TimeUnit.MINUTES);
//		}
//		
//	}
	
	/**
	 * 2.servlet容器之tomcat容器特定配置
	 * @return
	 */
//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//		factory.setPort(8085);
//		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
//		factory.setSessionTimeout(10, TimeUnit.MINUTES);
//		return factory;
//	}
	
	/**
	 * Http转向Https的配置
	 * @return
	 */
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory() {

			/**
			 * 使用tomcat容器前处理上下文，添加用户访问约束
			 */
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				
				SecurityCollection securityCollection = new SecurityCollection();
				securityCollection.addPattern("/*");
				
				securityConstraint.addCollection(securityCollection);
				context.addConstraint(securityConstraint);
			}
		};
		
		factory.addAdditionalTomcatConnectors(httpConnector());
		return factory;
	}
	
	/**
	 * 连接器配置
	 * @return
	 */
	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}
}
