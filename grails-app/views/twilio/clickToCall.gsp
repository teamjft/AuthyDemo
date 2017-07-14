<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Click To Call</title>
</head>

<body>
<h1>Twilio, Click To Call Demo</h1>

<g:render template="/template/flashMessage"/>

<g:form controller="twilio" action="clickToCallMakeCall" method="POST">
	<label>Your number</label>

	<div class="form-group">
		<g:textField name="firstPhoneNumber" class="form-control"/>
	</div>

	<label>Anothe rnumber</label>

	<div class="form-group">
		<g:textField name="secondPhoneNumber" class="form-control"/>
	</div>

	<g:submitButton name="submit" value="Make Call" />
</g:form>
</body>
</html>
