package com.operation.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
		//http.formLogin();
		http.formLogin().loginPage("/member/goLogin").defaultSuccessUrl("/");
		http.logout().invalidateHttpSession(true);
		http.exceptionHandling().accessDeniedPage("/denied");
		
		http.userDetailsService(mSServ);
		return http.build();
	}
	
	
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private DataSource ds;
	
	@Autowired // 매개변수의 자료형에 자동으로 탑제
	private void auth(AuthenticationManagerBuilder auth) throws Exception {
		String getUsers = "select id, pw, enabled  from member where id = ?";
		String getPrivs = "select id, role_id from member where id = ?";
		
		auth.jdbcAuthentication() // jdbc를 통해 인증 하겠다.
			.dataSource(ds)
			.usersByUsernameQuery(getUsers)
			.authoritiesByUsernameQuery(getPrivs);
	}
}
