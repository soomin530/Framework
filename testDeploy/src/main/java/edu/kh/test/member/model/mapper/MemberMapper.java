package edu.kh.test.member.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.test.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	List<Member> selectAllList();

}
