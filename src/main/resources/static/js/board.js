let index = {

    init: function(){
        $("#btn-save").on("click", ()=>{
            this.save();
        });
        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{
            this.update();
        });
    },

    save:function(){
        let data={
        // data object에 넣기 $(#id) -> input에서 id 값으로 찾음
            title: $("#title").val(),
            content: $("#content").val()
        };
        //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
        //ajax 호출시 기본이 비동기 호출
        //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
        $.ajax({
        //글쓰기니까 POST요청
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data), // http body데이터
            contentType:"application/json; charset=utf-8",
             //요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열 (생긴게 json이라면)
             // => javascript 오브젝트로 변경
            dataType:"json"

        }).done(function(resp){
            alert("글쓰기 완료");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
        },
    deleteById:function(){
        let id= ${"#id"}.text();
        $.ajax({
        //글삭제니까 DELETE요청
        type:"DELETE",
        url:"/api/board/" + id,
        //전송해줄 데이터가 업으므로 삭제함
//            data:JSON.stringify(data), // http body데이터
//            contentTyㅁpe:"application/json; charset=utf-8",
        dataType:"json"
        }).done(function(resp){
            alert("삭제 완료");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
        },

    update:function(){
        //value값을 들고올것임
        let id = $("#id").val();

        let data={
        // data object에 넣기 $(#id) -> input에서 id 값으로 찾음
            title: $("#title").val(),
            content: $("#content").val()
        };
        $.ajax({
        //수정이니까 PUT메소드 사용
            type:"PUT",
            url:"/api/board/" +id,
            data:JSON.stringify(data), // http body데이터
            contentType:"application/json; charset=utf-8",
             //요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열 (생긴게 json이라면)
             // => javascript 오브젝트로 변경
            dataType:"json"

        }).done(function(resp){
            alert("글 수정 완료");
            location.href="/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
}
index.init();
