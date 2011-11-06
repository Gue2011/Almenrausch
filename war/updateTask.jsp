<!DOCTYPE HTML>


<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
   	<jsp:include page="imports.jsp"></jsp:include>    
    <title>Update task <%= request.getAttribute("id") %></title>
  </head>

  <body>
    <h1>Task hinzufügen!</h1>
    
   <form action="updateTaskSubmit">
	  <input name="id" type="hidden" value="<%= request.getAttribute("id") %>">
	  <p>ID:<br><input name="nid" type="text" size="6" maxlength="6" value="<%= request.getAttribute("id") %>" disabled></p>
	  <p>Beschreibung:<br><input name="description" type="text" size="30" maxlength="40" value="<%= request.getAttribute("description") %>"></p>
	  <p>Aufwand:<br><input name="effort" type="text" size="10" maxlength="10" value="<%= request.getAttribute("effort") %>"> Stunden</p>
	  <p>Datum:<br><input name="date" type="date" size="20" maxlength="20" value="<%= request.getAttribute("date") %>"></p>
	  <p><SELECT NAME="payer" value="<%= request.getAttribute("payer") %>">
		<OPTION VALUE="Fuji">Fuji</OPTION>
		<OPTION VALUE="Direkt">Direkt</OPTION>
		</SELECT></p>
	  <input type="submit" value=" Ändern ">
	</form>
	
	<br />
	<a href="/viewTasks?week=<%= request.getAttribute("week") %>">Abbrechen</a>
	
    </body>
</html>
