package edu.kh.project.email.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.email.model.service.EmailService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("email")
@RequiredArgsConstructor
// ㄴ final 필드에 자동으로 의존성 주입 (@Autowired 생성자 방식 코드 자동완성)
//	  클래스 단에 이거 쓰면 메서드에 @Autowired 안 써도 됨
public class EmailController {
	
	private final EmailService service;
	
	@ResponseBody
	@PostMapping("signup")
	public int signup(@RequestBody String email) {
		
		String authKey = service.sendEmail("signup", email); // 발급 받은 인증번호 or null
		
		if(authKey != null) { // 인증번호가 반환되어 돌아옴
							  // == 이메일 보내기 성공
			return 1;
		}
		
		// 이메일 보내기 실패
		return 0;
	}
	
	/** 입력받은 이메일, 인증번호가 DB에 있는지 조회
	 * @param map
	 * @return 1 : 이메일이 있고, 인증번호가 일치할 때 or 0 : 아닐 때
	 */
	@ResponseBody
	@PostMapping("checkAuthKey")
	public int checkAuthKey(@RequestBody Map<String, String> map) {
		return service.checkAuthKey(map); 
		
	}
}
