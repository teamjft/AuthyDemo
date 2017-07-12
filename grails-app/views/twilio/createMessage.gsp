<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Send SMS</title>
</head>

<body>
<h1>Send Text Message (SMS)</h1>

<p>Send message to your phone</p>

<g:render template="/template/flashMessage"/>

<g:form controller="twilio" action="sendMessage" method="POST">
	<div class="col-md-12">
		<div class="form-group">
			<textarea class="form-control textarea" rows="5" name="message" id="message" placeholder="Message"></textarea>
		</div>
	</div>

	<div class="col-md-12">
		<button type="submit" class="btn main-btn btn-success pull-right">Send a message</button>
	</div>
</g:form>
</body>
</html>
