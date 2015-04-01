<%@ page language="java" import="org.springframework.security.core.Authentication, org.springframework.security.core.context.SecurityContextHolder" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="QModal.">

    <title>QModal</title>

		<meta charset="utf-8">
		<link href="css/style2.css" rel='stylesheet' type='text/css' />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> 
		addEventListener("load", function() { 
			setTimeout(hideURLbar, 0); 
			}, false); 

		function hideURLbar(){ 
			window.scrollTo(0,1); 
			} 
		</script>
		<!--webfonts-->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:600italic,400,300,600,700' rel='stylesheet' type='text/css'>
</head>
<body>

				<div class="login-form">
					<div class="head">
						<img src="images/user.jpg" alt=""/>
						
					</div>
				<form action="<c:url value='j_spring_security_check' />" method="post" id="submit">
					<li>
						<input type="text" class="text" value="USERNAME" name="j_username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'USERNAME';}" ><a href="#" class=" icon user"></a>
					</li>
					<li>
						<input type="password" value="Password" name="j_password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}"><a href="#" class=" icon lock"></a>
					</li>
					<div class="p-container">
								<label class="checkbox"><input type="checkbox" id="remember" checked><i></i>Remember Me</label>
								<input type="submit" value="SIGN IN" >
							<div class="clear"> </div>
					</div>
				</form>
			</div>
   					<div class="copy-right">
						<p>QModal</p> 
					</div>
</body>
</html>
