<%@page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="imports.jsp"></jsp:include>    

<title>Error!</title>
</head>
<body>
	<br>
	<h2>Sorry, We could not handle your request!</h2>
	<br>
	<strong>Cause: ${pageContext.exception.message}</strong>
</body>
</html>