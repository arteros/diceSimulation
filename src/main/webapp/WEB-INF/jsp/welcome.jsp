<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<script type="text/javascript"
	src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>


	<div class="divBody">

		<div class="divContainer">
			<h1>Welcome to tic tac toe</h1>
			<h2>Add player 1 name</h2>
		</div>
		<div class="divContainer">
			<form method="post" action="addPlayer">
				<p>
					Player 1 Name: <input type="text" name="playerName">
				</p>
				<p>
					<input type="submit" value="Submit" /> 
				</p>
			</form>
		</div>
	</div>
	<!-- /.container -->




</body>

</html>
