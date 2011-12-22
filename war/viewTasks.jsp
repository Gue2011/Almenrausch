<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<jsp:include page="imports.jsp"></jsp:include>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>View tasks for kw ${param.week}</title>
<meta charset="utf-8"> 


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

.week {
	float:left; 
	background: #9CCDE1; 
	padding: 5px 15px 5px 15px; 
	width: 15px; 
	color: #000; 
	margin-left: 5px; 
	text-decoration: none; 
}

#weeks {
	margin-bottom: 30px; 
}

#goto {
	margin-bottom: 7px;
	font-weight: bold; 
}

#user-level {
	margin-bottom: 7px; 
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
		
		$("input:submit, a, button", ".loginbutton").button();
		$("a", ".loginbutton").click(function() {
			window.location = '/';
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
	
	
	$(document).ready(function()
	{
		var mySections = $("tr").not("tr:first-child").hide();
	 
		mySections.each(function (i)
		{
			$(this).delay(i * 100).fadeIn(900);
		});
	});

	$('#opener').click(function() {
		$dialog.dialog('open');
		// prevent the default action, e.g., following a link
		return false;
	});
	
	
	<c:forEach items="${sessionScope.session.currentMoenigTasks}" var="task" varStatus="status">
		$("#confirm_M_${status.count}").easyconfirm({locale: { title: 'Please confirm deletion of task '+${task.taskId}+' from db ...', button: ['Keep','Delete']}});
		$("#confirm_M_${status.count}").click(function() {
			window.location = '/deleteTask?id=${task.taskId}&week=${param.week}';
		});
	</c:forEach>		

	<c:forEach items="${sessionScope.session.currentDirectTasks}" var="task" varStatus="status">
		$("#confirm_D_${status.count}").easyconfirm({locale: { title: 'Please confirm deletion of task '+${task.taskId}+' from db ...', button: ['Keep','Delete']}});
		$("#confirm_D_${status.count}").click(function() {
			window.location = '/deleteTask?id=${task.taskId}&week=${param.week}';
		});
	</c:forEach>		
	
	$('.week').corner(); 

});
</script>


<c:set var="admin">${sessionScope.session.admin}</c:set>

</head>

<body id="view-tasks">

	<div id="content">
		
		<c:choose>
			<c:when test="${empty param.month}">
				<h1>Uebersicht: KW ${param.week}  (${session.currentYear})</h1>
			</c:when>
			<c:otherwise>
				<h1>Uebersicht: Monat ${session.currentMonth} (${session.currentYear})</h1>
			</c:otherwise>
		</c:choose>
		<div id="user-level">
			Benutzerlevel: ${sessionScope.session.admin == false ? 'Besucher' : 'Administrator'}
		</div>
		<div id="goto">
		Gehe zu Woche:
		</div> 
		<div id="weeks">
			<c:forEach items="${sessionScope.session.weeksBefore}"
							var="week">
				<a href="/viewTasks?week=${week}">
					<div class="week">${week}</div>
				</a>
			</c:forEach>
		</div>
		<div class="floete"></div>
		<br />
		
		<div id="goto">
		Gehe zu Monat:
		</div> 
		<div id="weeks">
			<c:forEach items="${sessionScope.session.monthBefore}"
							var="month">
				<a href="/viewTasks?month=${month}">
					<div class="week">${month}</div>
				</a>
			</c:forEach>
		</div>
		<div class="floete"></div>
		<br />
	
		<c:choose>
			<c:when test="${empty sessionScope.session.currentMoenigTasks}">
				<h3>Bisher kein Grundrauschen fuer Koenig Moenig in KW
					${param.week} (${session.currentYear}) geplant.</h3>
			</c:when>
	
			<c:otherwise>
				<h2>Koenig Moenig Grundrauschen</h2>
				<table border="0" id="moenig">
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
							
							<td>
								<c:choose>
									<c:when test="${admin}">
										<a href="/updateTask?id=${task.taskId}&week=${param.week}">Aendern</a>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
								
							</td>
							<td>
								<c:choose>
									<c:when test="${admin}">
										<a href="#" id="confirm_M_${status.count}">Loeschen?</a>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
					<tr class="sum">
						<td colspan="5">SUMME</td>
						<td colspan="2">${sessionScope.session.sumOfMoenigTasks}</td>
					<tr>
				</table>
			</c:otherwise>
		</c:choose>
	
		<br />
	
	
		<c:choose>
			<c:when test="${empty sessionScope.session.currentDirectTasks}">
				<h3>Bisher kein Grundrauschen fuer die Direktkunden in KW
					${param.week} (${session.currentYear}) geplant.</h3>
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
							<td>
								<c:choose>
									<c:when test="${admin}">
										<a href="/updateTask?id=${task.taskId}&week=${param.week}">Aendern</a>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
								
							</td>
							<td>
								<c:choose>
									<c:when test="${admin}">
										<a href="#" id="confirm_D_${status.count}">Loeschen?</a>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
					<tr class="sum">
						<td colspan="5">SUMME</td>
						<td colspan="2">${sessionScope.session.sumOfDirectTasks}</td>
					<tr>
				</table>
			</c:otherwise>
		</c:choose>
		<br />
		<c:choose>
			<c:when test="${admin}">
				<div class="floete"></div>
				<div class="addbutton">
					<a href="#">Task hinzufuegen</a>
				</div>
				<div class="logoutbutton">
					<a href="#">Ausloggen</a>
				</div>
				<div class="clear"></div>
			</c:when>
			<c:otherwise>
				<div class="loginbutton">
					<a href="#">Einloggen?</a>
				</div>
			</c:otherwise>
		</c:choose>
		
	</div>
	
</body>
</html>
