package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.board.model.dto.Board;

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

	/** 게시글 상세 조회
	 * @param map
	 * @return board
	 */
	Board selectOne(Map<String, Integer> map);

	/** 게시글 좋아요 체크/해제
	 * @param map
	 * @return
	 */
	int boardLike(Map<String, Integer> map);

	/** 조회 수 1 증가
	 * @param boardNo
	 * @return
	 */
	int updateReadCount(int boardNo);

	/** 검색 서비스
	 * @param paramMap
	 * @param cp
	 * @return map
	 */
	Map<String, Object> searchList(Map<String, Object> paramMap, int cp);

	/** DB 이미지 파일명 목록 조회 
	 * @return
	 */
	List<String>  selectDbImageList();     
	
	

}
