package edu.kh.todo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그 객체 자동 생성
@Controller // 요청/응답 제어 역할 명시 + Bean 등록
public class MainController {
	
	@Autowired // 등록된 Bean 중 같은 타입이거나 상속관계인 Bean을 DI(의존성 주입)해줌.
	private TodoService service;
	
	@RequestMapping("/")
	public String mainPage(Model model) {
		// return이 문자열 형태라 String!
		
		log.debug("service : " + service);
		// edu.kh.todo.model.service.TodoServiceImpl@1b57cc87
		// ㄴ 의존성 주입이 잘 되고 있다 ~!
		
		// todoNo 가 1인 todo의 제목 조회하여 request scope에 추가
		String testTitle = service.testTitle();
		model.addAttribute("testTitle", testTitle);
		
		// ----------------------------------------------------------------
		
		// TB_TODO 테이블에 저장된 전체 할 일 목록 조회하기
		// + 완료된 할 일 갯수
		
		// Service 메서드 호출 후 결과 반환받기
		Map<String, Object> map = service.selectAll();
		
		// map에 담긴 내용 추출
		List<Todo> todoList = (List<Todo>)map.get("todoList");
		// object 형이기 때문에 더 작은 todoList에 대입하려면 다운캐스팅 해야함
		int completeCount = (int)map.get("completeCount");
		
		// Model을 이용해서 조회 결과 request scope 에 추가
		model.addAttribute("todoList", todoList);
		model.addAttribute("completeCount", completeCount);
		
		
		// 접두사 : classpath:/templates
		// 접미사 : .html
		return "common/main"; // 이쪽으로 foward
	}
}
