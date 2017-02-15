/*package com.example;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

*//**
 * 通用代码配置servlet容器
 * @author WQXia
 *
 *//*
@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer{

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8089);
		container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
		container.setSessionTimeout(10, TimeUnit.MINUTES);
	}

}
*/