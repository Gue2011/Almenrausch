<!DOCTYPE HTML>



<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css">
	<jsp:include page="jqueryui.jsp"></jsp:include>    
    <script>
	$(function() {
		$( "#datepicker" ).datepicker();
	});
	</script>




    
    <title>Add a task</title>
  </head>

  <body>
    <h1>Task hinzufuegen!</h1>
	
	<form action="addTask">
	  <p>ID:<br><input name="id" type="text" size="6" maxlength="6"></p>
	  <p>Beschreibung:<br><input name="description" type="text" size="200" maxlength="200"></p>
	  <p>Aufwand:<br><input name="effort" type="text" size="10" maxlength="10"> Stunden</p>
	  <p>Datum:<br><input name="date" type="text" id="datepicker"></p>
	  <p><SELECT NAME="payer">
		<OPTION VALUE="Fuji">Fuji
		<OPTION VALUE="Direkt">Direkt
		</SELECT></p>
	  <input type="submit" value=" Absenden ">
	</form>
	
	<br />
	<a href="javascript:history.back()">Abbrechen</a>
	
    </body>
</html>
