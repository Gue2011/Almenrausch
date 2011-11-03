<%@page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Error!</title>
</head>
<body>
	<br>
	<h2>Sorry, We could not handle your request!</h2>
	<br>
	<strong>Cause: ${pageContext.exception.message}</strong>
</body>
</html>