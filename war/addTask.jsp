<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <jsp:include page="imports.jsp"></jsp:include>    
    
    <style type="text/css">
		#add-task .ui-widget {
			font-size: 12px; 
		}
		
		.content {
			width: 500px; 
			padding: 10px; 
		}
		
		#description {
			width: auto; 
		}
	
		.savebutton {
			position: relative; 
			float:left; 
		}
		
		.backbutton {
			position: relative; 
			left: 10px; 
			float:left; 
		}
		
	</style>
    <script>
	$(function() {
		$( "#datepicker" ).datepicker();

	});
	
	jQuery(function($){
        $.datepicker.regional['de'] = {clearText: 'löschen', clearStatus: 'aktuelles Datum löschen',
                closeText: 'schließen', closeStatus: 'ohne Änderungen schließen',
                prevText: '<zurück', prevStatus: 'letzten Monat zeigen',
                nextText: 'Vor>', nextStatus: 'nächsten Monat zeigen',
                currentText: 'heute', currentStatus: '',
                monthNames: ['Januar','Februar','März','April','Mai','Juni',
                'Juli','August','September','Oktober','November','Dezember'],
                monthNamesShort: ['Jan','Feb','Mär','Apr','Mai','Jun',
                'Jul','Aug','Sep','Okt','Nov','Dez'],
                monthStatus: 'anderen Monat anzeigen', yearStatus: 'anderes Jahr anzeigen',
                weekHeader: 'Wo', weekStatus: 'Woche des Monats',
                dayNames: ['Sonntag','Montag','Dienstag','Mittwoch','Donnerstag','Freitag','Samstag'],
                dayNamesShort: ['So','Mo','Di','Mi','Do','Fr','Sa'],
                dayNamesMin: ['So','Mo','Di','Mi','Do','Fr','Sa'],
                dayStatus: 'Setze DD als ersten Wochentag', dateStatus: 'Wähle D, M d',
                dateFormat: 'yy-mm-dd', firstDay: 1,
                initStatus: 'Wähle ein Datum', isRTL: false};
        $.datepicker.setDefaults($.datepicker.regional['de']);
	});
	
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

  <title>Add a task</title>
  </head>

  <body id="add-task" >
   	<div id="content">
    <h1>Task hinzufuegen!</h1>
	
	<form action="addTask" id="task-form">
	  <p>ID:<br><input name="id" type="text" size="6" maxlength="6"></p>
	  <p>Beschreibung:<br><textarea id="description" name="description" type="text" maxlength="200"></textarea></p>
	  <p>Aufwand:<br><input name="effort" type="text" size="10" maxlength="10"> Stunden</p>
	  <p>Datum:<br><input name="date" type="text" id="datepicker"></p>
	  <p><SELECT NAME="payer">
		<OPTION VALUE="Fuji">Fuji
		<OPTION VALUE="Direkt">Direkt
		</SELECT></p>
	</form>
	
	<div class="floete"></div>
	<div class="savebutton">
		<a href="#">Speichern</a>
	</div>
	<div class="backbutton">
		<a href="#">Abbrechen</a>
	</div>
	<div class="clear"></div>
	</div>
    </body>
</html>
