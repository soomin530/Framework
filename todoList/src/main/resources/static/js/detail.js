// 목록으로 버튼 동작 (메인페이지로 요청)
const goToList = document.querySelector("#goToList");

goToList.addEventListener("click", () => {
  location.href = "/"; // 메인페이지로 요청(GET)
});

// 완료 여부 변경 버튼
const completeBtn = document.querySelector(".complete-btn");

completeBtn.addEventListener("click", e => {

  // 요소.dataset : data-* 속성에 저장된 값 반환
  // data-todo-no 에 세팅한 속성값 얻어오기
  // data-todo-no(html -를 사용) -> dataset.todoNo (js 카멜케이스)
  const todoNo = e.target.dataset.todoNo;

  let complete = e.target.innerText; // 기존 완료 여부 값 얻어오기

  // Y 라면 N 으로 바꾸고 N 이라면 Y로 바꿔준다
  complete = (complete == 'Y') ? 'N' : 'Y'; 

  // 완료 여부 수정 요청하기
  location.href
   = `/todo/changeComplete?todoNo=${todoNo}&complete=${complete}`;
   // --> /todo/changeComplete?todoNo=1&complete=Y

  // console.log(todoNo);
});
// 수정 버튼
const updateBtn = document.querySelector("#updateBtn");

updateBtn.addEventListener("click", e => {
  const todoNo = e.target.dataset.todoNo;
  location.href = "/todo/update?todoNo=" + todoNo; // 수정할 수 있는 화면 요청 (GET)

});
// 삭제버튼
const deleteBtn = document.querySelector("#deleteBtn");
deleteBtn.addEventListener("click", e => {
  // 1. 정말 삭제할 것인지 confirm()을 이용해서 확인
	// confirm()은 확인 클릭 시 true, 취소 클릭 시 false 반환
	
	// 취소 클릭 시
	if(!confirm("정말 삭제하시겠습니까?")) return;
	
	// 확인 클릭 시
	// /todo/delete?todoNo=4 GET 방식 요청 보내기
  const todoNo = e.target.dataset.todoNo;
	location.href = "/todo/delete?todoNo=" + todoNo; 
});