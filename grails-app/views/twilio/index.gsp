<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Twilio</title>
</head>

<body>
<h1>Twilio Demo</h1>

<g:render template="/template/flashMessage" />

<p><g:link controller="twilio" action="createMessage" class="btn btn-default nounderline">Send SMS</g:link></p>

<p><g:link controller="twilio" action="createMessage" params="[mms: true]" class="btn btn-default nounderline">Send MMS</g:link></p>

<p><g:link controller="twilio" action="messages" class="btn btn-default nounderline">Sent Messages</g:link></p>
</body>
</html>
