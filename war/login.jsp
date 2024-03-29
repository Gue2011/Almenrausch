<!DOCTYPE HTML>


<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<jsp:include page="imports.jsp"></jsp:include>    
<title>Please login</title>
   <style type="text/css">
		#login .ui-widget {
			font-size: 12px; 
		}
		
		.loginbutton {
			position: relative; 
			left: 110px; 
			top: -37px; 
		}
   </style>
   <script>
	
	$(function() {
		$("input:submit, a, button", ".loginbutton").button();
		$("a", ".loginbutton").click(function() {
			document.forms[0].submit();
			return false;
		});
		
		$("input:submit, a, button", ".visitbutton").button();
		$("a", ".visitbutton").click(function() {
			window.location = '/visit';
			return false;
		});
		
	});
	</script>

</head>

<body id="login">
	<div id="content">
		<h1>Login um einzutreten:</h1>
		<form action="login" method="post">
			<p>
				Passwort:<br>
				<input name="pw" type="password" size="12" maxlength="12">
			</p>
		</form>
	
		<div class="loginbutton">
			<a href="#">Einloggen</a>
		</div>

		<div class="visitbutton">
			<a href="#">Als Besucher eintreten</a>
		</div>

	</div>
	
</body>
</html>
