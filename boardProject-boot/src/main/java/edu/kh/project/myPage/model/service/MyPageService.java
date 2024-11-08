package edu.kh.project.myPage.model.service;

import edu.kh.project.member.model.dto.Member;

public interface MyPageService {

	int updateInfo(Member inputMember, String[] memberAddress);

}
