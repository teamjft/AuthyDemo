<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Twilio</title>
</head>

<body>
<h1>Twilio Demo</h1>

<g:render template="/template/flashMessage"/>

<p><g:link controller="twilio" action="createMessage" class="btn btn-default nounderline">Send Text Message (SMS)</g:link></p>
</body>
</html>
