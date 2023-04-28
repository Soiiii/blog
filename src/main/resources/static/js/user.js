let index = {

    init: function(){
    // .on(1,2) 1번이 되면 2번에 무엇을 할건지 적는거(1:클릭, 2:)
        $("#btn-save").on("click", ()=>{
            this.save();
        });
        $("#btn-login").on("click", ()=>{
            this.login();
        });
    },

    save:function(){
        //alert("user의 save함수 호출됨");
        let data={
        // data object에 넣기 $(#id) -> input에서 id 값으로 찾음
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
        //ajax 호출시 기본이 비동기 호출
        //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
        $.ajax({
            type:"POST",
            url:"/api/user",
            data:JSON.stringify(data), // http body데이터
            contentType:"application/json; charset=utf-8",
             //요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열 (생긴게 json이라면)
             // => javascript 오브젝트로 변경
            dataType:"json"

        }).done(function(resp){
            alert("회원가입 완료");
            console.log(resp);
            alert(resp);
//            location.href="/blog";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
        },

    login:function(){
            //alert("user의 save함수 호출됨");
            let data={
            // data object에 넣기 $(#id) -> input에서 id 값으로 찾음
                username: $("#username").val(),
                password: $("#password").val(),
                email: $("#email").val()
            };
            //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
            //ajax 호출시 기본이 비동기 호출
            //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
            $.ajax({
                type:"POST",
                url:"/api/user/login",
                data:JSON.stringify(data), // http body데이터
                contentType:"application/json; charset=utf-8",
                 //요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 문자열 (생긴게 json이라면)
                 // => javascript 오브젝트로 변경
                dataType:"json"

            }).done(function(resp){
                alert("로그인 완료");
                location.href="/";
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
            }


    }


index.init();
