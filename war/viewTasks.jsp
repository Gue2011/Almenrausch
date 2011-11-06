<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<jsp:include page="imports.jsp"></jsp:include>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>View tasks for kw ${param.week}</title>



<style type="text/css">
td {
	text-align: center;
	width: 100px;
}

#view-tasks .ui-widget {
	font-size: 12px; 
}

.addbutton {
	position: relative; 
	float:left; 
}

.logoutbutton {
	position: relative; 
	left: 10px; 
	float:left; 
}



</style>

<script>
	$(function() {
		$("input:submit, a, button", ".addbutton").button();
		$("a", ".addbutton").click(function() {
			window.location = '/addTask.jsp';
			return false;
		});

		$("input:submit, a, button", ".logoutbutton").button();
		$("a", ".logoutbutton").click(function() {
			window.location = '/logout';
			return false;
		});
	});
	
	$(document).ready(function() {
	var $dialog = $('<div></div>')
		.html('This dialog will show every time!')
		.dialog({
			autoOpen: false,
			title: 'Basic Dialog'
		});

	$('#opener').click(function() {
		$dialog.dialog('open');
		// prevent the default action, e.g., following a link
		return false;
	});
	
	$("#yesno").easyconfirm({locale: { title: 'Select Yes or No', button: ['No','Yes']}});
	$("#yesno").click(function() {
		alert("You clicked yes");
	});
});
</script>

</head>

<body id="view-tasks">
	<h1>Uebersicht: KW ${param.week}</h1>

	<c:choose>
		<c:when test="${empty sessionScope.session.currentMoenigTasks}">
			<h3>Bisher kein Grundrauschen fuer Koenig Moenig in KW
				${param.week} geplant.</h3>
		</c:when>

		<c:otherwise>
			<h2>Koenig Moenig Grundrauschen</h2>
			<table border="0">
				<tr>
					<th>ID</th>
					<th width="500">Descripton</th>
					<th>Aufwand</th>
					<th>Date</th>
					<th width="200">Kostenstelle</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${sessionScope.session.currentMoenigTasks}"
					var="task"
					varStatus="status">
					<c:choose>
						<c:when test="${status.count%2==0}">
							<tr class="odd">
						</c:when>
						<c:otherwise>
							<tr class="even">
						</c:otherwise>
					</c:choose>
					
						<td>${task.taskId}</td>
						<td>${task.description}</td>
						<td>${task.effort}</td>
						<td>${task.dateString}</td>
						<td>MOENIG</td>
						<td><a
							href="/updateTask?id=${task.taskId}&week=${param.week}">Aendern</a>
						</td>
						<td><button id="opener">Loeschen?</button>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5">Summe</td>
					<td colspan="2">${sessionScope.session.sumOfMoenigTasks}</td>
				<tr>
			</table>
		</c:otherwise>
	</c:choose>

	<br />


	<c:choose>
		<c:when test="${empty sessionScope.session.currentDirectTasks}">
			<h3>Bisher kein Grundrauschen fuer die Direktkunden in KW
				${param.week} geplant.</h3>
		</c:when>

		<c:otherwise>
			<h2>Direktkunden Grundrauschen</h2>
			<table border="0">
				<tr>
					<th>ID</th>
					<th width="300">Descripton</th>
					<th>Aufwand</th>
					<th>Date</th>
					<th width="200">Kostenstelle</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${sessionScope.session.currentDirectTasks}"
					var="task"
					varStatus="status">
					<c:choose>
						<c:when test="${status.count%2==0}">
							<tr class="odd">
						</c:when>
						<c:otherwise>
							<tr class="even">
						</c:otherwise>
					</c:choose>
						<td>${task.taskId}</td>
						<td>${task.description}</td>
						<td>${task.effort}</td>
						<td>${task.dateString}</td>
						<td>DIREKTKUNDEN</td>
						<td><a
							href="/updateTask?id=${task.taskId}&week=${param.week}">Aendern</a>
						</td>
						<td><a
							href="/deleteTask?id=${task.taskId}&week=${param.week}">Loeschen</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5">Summe</td>
					<td colspan="2">${sessionScope.session.sumOfDirectTasks}</td>
				<tr>
			</table>
		</c:otherwise>
	</c:choose>
	<br />
	
	<div class="floete"></div>
	
	<div class="addbutton">
		<a href="#">Task hinzufuegen</a>
	</div>
	<div class="logoutbutton">
		<a href="#">Ausloggen</a>
	</div>
	<div class="clear"></div>
	
	<a href="#" id="yesno">Normal test with yes and no</a>
	
</body>
</html>
