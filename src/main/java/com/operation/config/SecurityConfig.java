package com.operation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.operation.services.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MemberService mServ;
	
	@Bean
	protected SecurityFilterChain config(HttpSecurity http) throws Exception {
		// 스프링 시큐리티를 커스터 마이징 해주는 내용을 넣어주면 됨
		http.csrf().disable();
		http.authorizeHttpRequests()
		.requestMatchers(new AntPathRequestMatcher("/**")).permitAll();
//		http.authorizeHttpRequests()
//			.requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()// 이 경로에 대해서는 인증 되어야 한다.
//			.requestMatchers(new AntPathRequestMatcher("/manager/only")).hasRole("MANAGER")
//			.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("MANAGER", "ADMIN")
//			.requestMatchers(new AntPathRequestMatcher("/admin/manager")).hasAnyRole("MANAGER", "ADMIN")
//			.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
//			.requestMatchers(new AntPathRequestMatcher("/**")).permitAll();// 모든 경로에 대해 허가한다.
		http.formLogin().loginPage("/member/goLogin").defaultSuccessUrl("/"); // 로그인 페이지로는 /login 페이지를 사용하고 성공하면 /페이지로 가겠다.
		http.logout().invalidateHttpSession(true);
		http.exceptionHandling().accessDeniedPage("/denied");
		http.formLogin().loginPage("/login");
		
//		http.userDetailsService(mServ);
		return http.build();
	}
	
}
