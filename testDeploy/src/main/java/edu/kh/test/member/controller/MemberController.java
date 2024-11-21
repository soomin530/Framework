package edu.kh.test.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.test.member.model.dto.Member;
import edu.kh.test.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@ResponseBody
	@GetMapping("selectAllList")
	public List<Member> selectAllList() {
		
		return service.selectAllList();
	}
	
	

	
}
