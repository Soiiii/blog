let index = {
    init: function(){
    // .on(1,2) 1번이 되면 2번에 무엇을 할건지 적는거(1:클릭, 2:)
        $("#btn-save").on("click", ()=>{
            this.save();
        });
    },

    save:function(){
        //alert("user의 save함수 호출됨");
        let data={
        // data object에 넣기 $(#id) -> input에서 id 값으로 찾음
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
        $.ajax().done().fail();

    }
}

index.init();
