package com.operation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.operation.services.UserSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserSecurityService mSServ;
	
	@Bean
	protected SecurityFilterChain config(HttpSecurity http) throws Exception {
		// 스프링 시큐리티를 커스터 마이징 해주는 내용을 넣어주면 됨
		http.csrf().disable();
		http.authorizeHttpRequests()
		.requestMatchers(new AntPathRequestMatcher("/board/goWritePost/**")).authenticated()
		.requestMatchers(new AntPathRequestMatcher("/board/viewPostConf/**")).authenticated()
		.requestMatchers(new AntPathRequestMatcher("/qna/viewQnaConf/**")).authenticated()
		.requestMatchers(new AntPathRequestMatcher("/**")).permitAll();
		http.formLogin().loginPage("/member/goLogin");
		http.logout().invalidateHttpSession(true);
		http.exceptionHandling().accessDeniedPage("/denied");
		
		http.userDetailsService(mSServ);
		return http.build();
	}
	
}
