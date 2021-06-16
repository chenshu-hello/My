<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
                  $(function(){
                  	//随机生成验证码
					  $("#code_img").click(function (){

					  	this.src="${basePath}kaptcha.jpg?d="+new Date();

				  })
                  //注册用户名
                     $("#sub_btn").click(function(){
                     //获取用户名输入框中的内容
                      var usernameText=$("#username").val();

                      var usernamePatt=/^\w{5,12}$/;

                      if(!usernamePatt.test(usernameText)){
                              $("span.errorMsg").text("用户名输入不正确");

                            return false;
                            }
                      var passwordText=$("#password").val();

                      var passwordPatt=/^\w{5,12}$/;

                      if(!passwordPatt.test(passwordText)){

                             $("span.errorMsg").text("密码输入不正确");

                            return false;

                            }
                      var repwdText=$("#repwd").val();

                      if(repwdText!=passwordText){

                      $("span.errorMsg").text("确认密码输入不正确");

                          return false;
                          }
                          var emailText=$("#email").val();

                          var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                      if(!emailPatt.text(emailText)){

                      $("span.errorMsg").text("邮箱输入不正确");

                          return false;
                          }
                          var codeText=$("#code").val();

                          codeText=$.trim(codeText);
                          if( codeText==null || codeText==""){
                                $("span.errorMsg").text("验证码输入不正确");

                                return false;
                          }

                             $("span.errorMsg").text("");

                  });
                  });
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>David书城会员</h1>
								<span class="errorMsg">
									${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   value="${requestScope.username}"
										   autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   value="${requestScope.email}"
										   autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px" height="30px" width="110px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>