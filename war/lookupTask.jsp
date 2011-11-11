<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   	<jsp:include page="imports.jsp"></jsp:include>    
    <title>Update task <%= request.getAttribute("id") %></title>
     <style type="text/css">
		#lookup-task .ui-widget {
			font-size: 12px; 
		}
		
		.content {
			width: 500px; 
			padding: 10px; 
		}
		
		#description {
			width: auto; 
		}
		
		.backbutton {
			position: relative; 
			float: left; 
			margin-right: 20px; 
			left: 10px; 
		}
		
	</style>
    <script>
    $(function() {
		$("input:submit, a, button", ".savebutton").button();
		$("a", ".savebutton").click(function() {
			document.forms[0].submit();
			return false;
		});

		$("input:submit, a, button", ".backbutton").button();
		$("a", ".backbutton").click(function() {
			
			history.back();
			return false;
		});
	});
    </script>
  </head>

  <body id="lookup-task">
  
  	<c:choose>
  		<c:when test="${not empty param.idx}">
  			<br />
  			<h1>Task ${param.idx} nicht in db!</h1>
  		</c:when>
  		<c:otherwise>
  			<h1>Details von Task: <%= request.getAttribute("id") %>!</h1>
		   <form action="updateTaskSubmit">
			  <input name="id" type="hidden" value="<%= request.getAttribute("id") %>">
			  <p>ID:<br><input disabled name="nid" type="text" size="6" maxlength="6" value="<%= request.getAttribute("id") %>" disabled></p>
			  <p>Beschreibung:<br><input disabled name="description" type="text" size="30" maxlength="40" value="<%= request.getAttribute("description") %>"></p>
			  <p>Aufwand:<br><input disabled name="effort" type="text" size="10" maxlength="10" value="<%= request.getAttribute("effort") %>"> Stunden</p>
			  <p>Datum:<br><input disabled name="date" type="date" size="20" maxlength="20" value="<%= request.getAttribute("date") %>"></p>
			  <p><SELECT disabled NAME="payer" value="<%= request.getAttribute("payer") %>">
				<OPTION VALUE="Fuji">Fuji</OPTION>
				<OPTION VALUE="Direkt">Direkt</OPTION>
				</SELECT></p>
			</form>
			
			<div class="floete"></div>
			<div class="savebutton">
				<a href="#">Speichern</a>
			</div>
  		</c:otherwise>
  	</c:choose>
  
	<div class="backbutton">
		<a href="#">Abbrechen</a>
	</div>
	<div class="clear"></div>
	</div>
    </body>
	
    </body>
</html>
