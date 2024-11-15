package edu.kh.project.common.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * Filter : 요청, 응답 시 걸러내거나 추가할 수 있는 객체
 * 
 * [필터 클래스 생성 방법]
 * 1. jakarta.servlet.Filter 인터페이스 상속 받기
 * 2. doFilter() 메서드 오버라이딩
 * 
 * */

// 로그인이 되어있지 않은 경우 특정 페이지로 돌아가게 함
public class LoginFilter implements Filter{

	// 필터 동작을 정의하는 메서드
	@Override
	public void doFilter(ServletRequest request,
						ServletResponse response,
						FilterChain chain) throws IOException, ServletException {
		
		// ServletRequest  : HttpServletRequest 의 부모 타입
		// ServletResponse : HttpServletResponse 의 부모 타입
		
		// Session 필요함 -> 왜? -> loginMember가 session에 담겨있기 때문에!
		
		// HTTP 통신이 가능한 형태로(자식형태) 다운 캐스팅 해서 session 얻어오기
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		// 현재 요청의 URI를 가져옴 (쿼리스트링을 제외한 부분)
		String path = req.getRequestURI();
		
		// 요청 URI가 "/myPage/profile/" 로 시작하는지 확인
		if(path.startsWith("/myPage/profile/")) {
			// 필터를 통과하도록 함
			chain.doFilter(request, response);
			// 필터를 통과한 후 아래 코드 수행하지 않도록 return
			return;	
			
		}
		
		
		// Session 얻어오기
		HttpSession session = req.getSession();  // req -> session
												 // req, session -> application
		
		// 세션에서 로그인 한 회원 정보를 얻어옴
		// 얻어왔으나, 없을 때 -> 로그인이 되어있지 않은 상태
		if(session.getAttribute("loginMember")== null) {
			
			// /loginError 쪽으로 재요청
			// resp를 이용해서 원하는 곳으로 redirect
			resp.sendRedirect("/loginError");
			
		} else {
			// 로그인이 되어있는 경우
			
			// FilterChain
			// - 다음 필터 또는  Dispathcer Servlet과 연결된 객체
			// 	 필터가 여러 개 있다면 다음 필터로 요청/응답 객체 전달
			// 	 만약에 다음 필터가 없으면  Dispathcer Servlet으로 request, response 전달 (Controller 쪽으로 넘어감)
			chain.doFilter(request, response);
			
		}
		
	}

}
