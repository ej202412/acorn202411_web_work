<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form2.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>회원가입 폼 입니다</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div>
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id"/>
				<small class="form-text">영문자 소문자로 시작하고 5~10 글자 이내로 입력하세요</small>
				<div class="valid-feedback">잘 입력했군요! 짱구가 아니네요~</div>
				<div class="invalid-feedback">사용할 수 없는 아이디 입니다</div>
			</div>
			
			<button class="btn btn-success" type="submit" disabled="disabled">가입</button>
		</form>
	</div>
	<script>
	
		//아이디 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isIdValid=false;
		//비밀번호 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isPwdValid=false;
		//이메일 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isEmailValid=false;
		
		document.querySelector("#id").addEventListener("input", (event)=>{			
			//현재까지 입력한 아이디를 읽어온다
			let inputId=event.target.value; //document.querySelector("#id").value;
			//만일 정규표현식을 통과하지 못했다면
			if(reg_id.test(inputId)){
				isIdValid=true;
			}
			
		});
		
		const checkForm = ()=>{
			//폼 전체의 유효성 여부에 따라 분기한다 (지금은 id 유효성 여부만)
			if(isIdValid){
				//type 속성이 submit 인 요소를 찾아서 disabled 속성을 제거한다
				document.querySelector("[type=submit]").removeAttribute("disabled");
			}else{
				//type 속성이 submit 인 요소를 찾아서 disabled="disabled" 속성을 추가한다
				document.querySelector("[type=submit]").setAttribute("disabled", "disabled");
			}
		};
	
		/*
			폼에 submit 이벤트가 발생하면 입력한 내용을 검증해서 
			조건을 만족하지 못하면 폼 제출을 막는 예제
			
			hint => event.preventDefault()
		*/
		//아이디를 검증할 정규 표현식
		const reg_id=/^[a-z].{4,9}$/;
		
		//id 를 입력할 때마다 실행할 함수 등록
		document.querySelector("#id").addEventListener("input", (event)=>{
			//일단 is-valid, is-invalid 클래스를 모두 지우고
			event.target.classList.remove("is-valid", "is-invalid");
			
			//현재까지 입력한 아이디를 읽어온다
			let inputId=event.target.value; //document.querySelector("#id").value;
			//만일 정규표현식을 통과하지 못했다면
			if(!reg_id.test(inputId)){
				/*
					어떤 요소에 클래스를 추가하는 방법
					.classList.add("클래스명")
				*/
				event.target.classList.add("is-invalid"); //인풋 요소에 클래스 추가
				//아이디의 상태값 변경
				isIdValid=false;
			}else{
				event.target.classList.add("is-valid");
				//아이디의 상태값 변경
				isIdValid=true;
			}
			//상태값을 이용해서 UI 를 변경하는 함수 호출
			checkForm();
		});
		
	</script>
</body>
</html>