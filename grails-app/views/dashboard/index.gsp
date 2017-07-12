<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Dashboard</title>
</head>

<body>
<h1>Welcome <sec:username /></h1>

<p>Authy demo completed :-)</p>

<g:render template="/template/flashMessage" />
<p><g:link controller="twilio" class="btn btn-default nounderline">Click for Twilio demo</g:link></p>
</body>
</html>
