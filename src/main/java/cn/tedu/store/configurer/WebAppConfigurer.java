package cn.tedu.store.configurer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.store.controller.LoginInterceptor;

@Configuration
public class WebAppConfigurer 
	implements WebMvcConfigurer {

	@Override
	public void addInterceptors(
			InterceptorRegistry registry) {
		// 黑名单
		List<String> pathPatterns
			= new ArrayList<>();
		pathPatterns.add("/user/**");
		// 白名单
		List<String> excludePathPatterns
			= new ArrayList<>();
		excludePathPatterns.add("/user/reg.do");
		excludePathPatterns.add("/user/login.do");
		// 注册
		registry
			.addInterceptor(new LoginInterceptor())
			.addPathPatterns(pathPatterns)
			.excludePathPatterns(excludePathPatterns);
	}

}
