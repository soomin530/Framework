package edu.kh.project.member.model.service;

import java.util.List;

import edu.kh.project.member.model.dto.Member;

public interface MemberService {

	/** 로그인 서비스
	 * @param inputMember
	 * @return loginMember
	 */
	Member login(Member inputMember) throws Exception;

	/** 이메일 중복 검사 서비스
	 * @param memberEmail
	 * @return
	 * @author 우수민 <- 프로젝트 할 때 꼭 해놓기
	 */
	int checkEmail(String memberEmail);

	/** 닉네임 유효성 검사
	 * @param memberNickname
	 * @return 
	 */
	int checkNickname(String memberNickname);

	/** 회원 가입 서비스 
	 * @param inputMember
	 * @param memberAddress
	 * @return
	 */ 
	int signup(Member inputMember, String[] memberAddress);

	/** 회원 목록 조회
	 * @return
	 */
	List<Member> selectList();    

}
