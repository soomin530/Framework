package edu.kh.test.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.test.member.model.dto.Member;
import edu.kh.test.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Override
	public List<Member> selectAllList() {
		return mapper.selectAllList();
	}
}
