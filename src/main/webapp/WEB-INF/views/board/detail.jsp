<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
 <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
 <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
 <button id="btn-delete" class="btn btn-warning">삭제</button>
 <br/><br/>

 <div>
    글 번호: <span id="id"><i>${board.id} </i></span>
    작성자: <span><i>${board.user.username} </i></span>
 <form>
   <div>
     <h3> ${board.title}</h3>
</div>

<div>
   <div> ${board.content} </div>
</div>
<hr/>

 </form>
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
</div>
