package edu.kh.project.board.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardImg {
	
	private int imgNo;
	private String imgPath;
	private String imgOriginalName;
	private String imgRename;
	private int imgOrder;
	private String boardNo;
	
	// 게시글 이미지 삽입/수정할 때 사용
	private MultipartFile uploadFile;

}
