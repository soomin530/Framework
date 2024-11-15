package edu.kh.project.main.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;

@Controller
public class MainController {
	
	@RequestMapping("/") // "/"로 요청이 오면 이 메서드로 매핑해주겠다
	public String mainPage() {
		
		// 접두사/접미사 제외
		// classpath:/templates/
		// .html
		return "common/main";
		
	}
	
	// LoginFilter -> loginError 리다이렉트 하면 만나게 되는 메서드
	// -> message 만들어서 메인페이지로 리다이렉트
	@GetMapping("loginError")
	public String loginError(RedirectAttributes ra) {
		ra.addFlashAttribute("message", "로그인 후 이용해주세요.");
		return "redirect:/";
	}

}
