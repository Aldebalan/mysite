<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var messageBox = function(title, message, callback) {
	$("#dialog-message p").text(message);
	$("#dialog-message")
		.attr("title", title)
		.dialog({
			width: 340,
			modal: true,
			buttons: {
				"확인": function(){
					$(this).dialog('close');
				}
			},
			close: callback
		});
}

$(function(){
	$("#join-form").submit(function(event){
		event.preventDefault();
		
		//1. 이름 유효성(empty) 체크
		if($("#name").val() === '') {
			messageBox("회원가입", "이름이 비어 있습니다.", function() {
				$("#name").focus();
			});
			return;
		}

		//2. 이메일 유효성(empty) 체크
		if($("#email").val() === '') {
			messageBox("회원가입", "이메일이 비어 있습니다.", function() {
				$("#email").focus();
			});
			return;
		}
		
		//3. 이메일 중복 체크 유무
		if(!$("#img-checkemail").is(":visible")) {
			messageBox("회원가입", "이메일 중복을 확인해 주세요");
			return;
		}
		
		//4. 비밀번호 유효성(empty) 체크	
		if($("#password").val() === '') {
			messageBox("회원가입", "비밀번호가 비어 있습니다.", function() {
				$("#password").focus();
			});
			return;
		}
		
		//5. 약관 동의 유무
		if(!$("#agree-prov").is(":checked")){
			messageBox("회원가입", "약관 동의를 해야 합니다.");
			return;
		}
		
		// 6.ok
		this.submit();
	});
	
	$("#email").change(function(){
		$("#btn-checkemail").show();
		$("#img-checkemail").hide();		
	});
	
	$("#btn-checkemail").click(function(){
		var email = $("#email").val();
		if(email === '') {
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath }/api/user/existemail?email=" + email,
			type: "get",
			dataType: "json",
			error: function(xhr, status, e) {
				console.error(status, e);
			},
			success: function(response) {
				if(response.result !== 'success') {
					console.error(response.message);
					return;
				}
				
				if(response.data) { // exists!
					alert("존재하는 이메일입니다. 다른 이메일을 사용하세요.");
					$("#email")
						.val('')
						.focus();
					return;
				}
				
				// not exist
				$("#btn-checkemail").hide();
				$("#img-checkemail").show();
			}
		});
	}); 	
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<form:form
					modelAttribute="userVo"
					id="join-form"
					name="joinForm"
					method="post"
					action="${pageContext.request.contextPath }/user/join">
					
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<p style="text-align:left; padding:0; color: red">
						<spring:hasBindErrors name="userVo">
							<c:if test='${errors.hasFieldErrors("name") }'>
								<spring:message code='${errors.getFieldError("name").codes[0] }'/>
							</c:if>
						</spring:hasBindErrors>
					</p>

					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />
					<input type="button" id='btn-checkemail' value="중복체크">
					<img id="img-checkemail" style="width:24px; vertical-align: bottom; display:none;" src="${pageContext.request.contextPath }/assets/images/check.png" />
					<p style="text-align:left; padding:0; color: red">
						<form:errors path="email" />
					</p>
					
					<label class="block-label"><spring:message code='user.join.label.password'/></label>
					<form:password path="password" />
					<p style="text-align:left; padding:0; color: red">
						<form:errors path="password" />
					</p>
					
					<fieldset>
						<legend>성별</legend>
						<form:radiobutton path="gender" value="female" label="여" checked="${userVo.gender == 'female' }"/>
						<form:radiobutton path="gender" value="male" label="남" checked="${userVo.gender == 'male' } }"/>
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					<input type="submit" value="가입하기">
				</form:form>
			</div>
		</div>
		<div id="dialog-message" title="" style="display:none">
			<p style="line-height:60px"></p>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>