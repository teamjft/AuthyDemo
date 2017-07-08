<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Dashboard</title>
</head>

<body>
<h1>Welcome <sec:username /></h1>

<g:render template="/template/flashMessage" />

<p>Send message to your phone</p>

<g:form controller="dashboard" action="sendMessage">
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
