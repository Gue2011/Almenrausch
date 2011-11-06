<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<jsp:include page="jqueryui.jsp"></jsp:include>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>View tasks for kw ${param.week}</title>

<link rel="stylesheet" type="text/css" href="styles.css">

<style type="text/css">
td {
	text-align: center;
	width: 100px;
}

#view-tasks .ui-widget {
	font-size: 12px; 
}

.floete {
	float:left; 
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

.clear {
	clear:both; 
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
</script>

</head>

<body id="view-tasks">
	<h1>Übersicht: KW ${param.week}</h1>

	<c:choose>
		<c:when test="${empty sessionScope.session.currentMoenigTasks}">
			<h3>Bisher kein Grundrauschen für König Mönig in KW
				${param.week} geplant.</h3>
		</c:when>

		<c:otherwise>
			<h2>König Mönig Grundrauschen</h2>
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
					var="task">
					<tr class="isCorrect_">
						<td>${task.taskId}</td>
						<td>${task.description}</td>
						<td>${task.effort}</td>
						<td>${task.dateString}</td>
						<td>MÖNIG</td>
						<td><a
							href="/updateTask?id=${task.taskId}&week=${param.week}">Ändern</a>
						</td>
						<td><a
							href="/deleteTask?id=${task.taskId}&week=${param.week}">Löschen</a>
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
			<h3>Bisher kein Grundrauschen für die Direktkunden in KW
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
					var="task">
					<tr class="isCorrect_">
						<td>${task.taskId}</td>
						<td>${task.description}</td>
						<td>${task.effort}</td>
						<td>${task.dateString}</td>
						<td>DIREKTKUNDEN</td>
						<td><a
							href="/updateTask?id=${task.taskId}&week=${param.week}">Ändern</a>
						</td>
						<td><a
							href="/deleteTask?id=${task.taskId}&week=${param.week}">Löschen</a>
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
</body>
</html>
