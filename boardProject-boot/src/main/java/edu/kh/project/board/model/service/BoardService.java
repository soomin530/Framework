package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	/** 게시판 종류 조회
	 * @return boardTypeList
	 */
	List<Map<String, Object>> selectBoardTypeList(); 
	// DB에 게시판 종류 얻어오고 싶은데 DTO를 따로 마련하지 않았기 때문에
	// BOARD_CODE와 BOARD_NAME을 각각 K:V꼴로 Map형태로 리스트에 담아서 이를 얻어옴

	/** 특정 게시판에 지정된 페이지 목록 조회 서비스
	 * @param boardCode
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectBoardList(int boardCode, int cp); 

}
