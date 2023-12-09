package com.operation.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.operation.services.MemberService;
import com.operation.services.UserSecurityService;

import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserSecurityService mSServ;
	
	@Autowired
	private HttpSession session;
	
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
		http.formLogin().loginPage("/member/goLogin").defaultSuccessUrl("/")
		.successHandler((request, response, authentication) -> {
			// 성공했을 때
			System.out.println("로그인 성공");
			
			 // 인증 객체에서 사용자 정보 가져오기
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        String loginID = userDetails.getUsername(); // 아이디
			session.setAttribute("loginID", loginID);
			response.sendRedirect("/");
		})
		.failureHandler((request, response, exception) -> {
			// 실패했을 때
			System.out.println("로그인 실패");
			try {
	            // 로그인 페이지로 리다이렉션
	            response.sendRedirect("/member/goLogin?error");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		});
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
