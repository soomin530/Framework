package edu.kh.project.common.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.kh.project.common.filter.LoginFilter;

// 만들어 놓은 Filter 클래스가 언제 적용될지 설정하는 클래스

@Configuration // 서버가 켜질 때 해당 클래스 내 모든 메서드가 실행됨!
public class FilterConfig {
	
	@Bean // 반환된 객체를 Bean으로 등록 : LoginFilter로 타입을 제한한 객체를 Bean으로 등록함
	public FilterRegistrationBean<LoginFilter> loginFilter() {
		// FilterRegistrationBean : 필터를 Bean으로 등록하는 객체
		
		FilterRegistrationBean<LoginFilter> filter = new FilterRegistrationBean<>();
		
		// 사용할 필터 객체 추가
		filter.setFilter(new LoginFilter());
		
		// 필터가 동작할 URL 세팅
		
		// /myPage/* : myPage로 시작하는 모든 요청
		String[] filteringURL = {"/myPage/*", "/editBoard/*", "/chatting/*"}; 
		// 필터링 하고 싶은 URL 몇 개든 쓸 수 있게 배열로 만들어줌
		
		// String[] 을 List 로 변환
		// Arrays.asList(filteringURL) -> List 형태로 변환!
		
		filter.setUrlPatterns(Arrays.asList(filteringURL));
		
		// 필터 이름 지정
		filter.setName("loginFilter");
		
		// 필터 순서 지정
		filter.setOrder(1); // 필터 중 첫 번째로 동작
		
		return filter; // 반환된 FilterRegistrationBean 객체가 필터를 생성해서 Bean으로 등록
	}

}
