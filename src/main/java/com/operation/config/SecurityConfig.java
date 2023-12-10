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
		// 스프링 시큐리티를 커스터 마이징 해주는 내용
		http.csrf().disable();
		// 페이지 별 권한부여
		http.authorizeHttpRequests()
		.requestMatchers(new AntPathRequestMatcher("/board/goWritePost/**")).authenticated() // 자유 or 질문 or qna 게시글 작성
		.requestMatchers(new AntPathRequestMatcher("/board/viewPostConf/**")).authenticated() // 자유 or 질문 게시글로 이동
		.requestMatchers(new AntPathRequestMatcher("/qna/viewQnaConf/**")).authenticated() // qna 게시글로 이동
		.requestMatchers(new AntPathRequestMatcher("/member/mypage/**")).authenticated() // 마이페이지 내에서 사용되는 모든 url
		.requestMatchers(new AntPathRequestMatcher("/qna/mypage/**")).authenticated() // 마이페이지 내 QNA 게시글 관리
		.requestMatchers(new AntPathRequestMatcher("/board/mypage/**")).authenticated() // 마이페이지 내 게시글 관리
		.requestMatchers(new AntPathRequestMatcher("/qna/admin/**")).hasRole("ADMIN") // 관리자 Q&A 답글 작성/수정/수정내용 불러오기 관리자만 가능
		.requestMatchers(new AntPathRequestMatcher("/**")).permitAll();
		//http.formLogin();
		http.formLogin().loginPage("/member/goLogin").defaultSuccessUrl("/")
		.successHandler((request, response, authentication) -> {
			// 성공했을 때
			 // 인증 객체에서 사용자 정보 가져오기
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        String loginID = userDetails.getUsername(); // 아이디
			session.setAttribute("loginID", loginID);
			response.sendRedirect("/");
		})
		.failureHandler((request, response, exception) -> {
			// 실패했을 때
			response.sendRedirect("/member/goLogin");
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
