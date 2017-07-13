<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Messages</title>
</head>

<body>
<h1>Messages</h1>

<g:render template="/template/flashMessage" />

<table class="table table-striped table-bordered">
	<thead>
	<tr>
		<th>SID</th>
		<th>Body</th>
		<th>Status</th>
		<th>Actions</th>
	</tr>
	</thead>
	<tbody>
	<g:each in="${messages}" var="message">
		<tr>
			<td><g:link controller="twilio" action="messageDetails" params="[sid: message.sid]">${message.sid}</g:link></td>
			<td>${message.body}</td>
			<td>${message.status}</td>
			<td>
				<g:form controller="twilio" method="POST">
					<g:hiddenField name="sid" value="${message?.sid}" />

					<g:actionSubmit value="Erase Body" action="updateMessageBody" class="btn btn-default" />
					<g:actionSubmit value="Delete" action="deleteMessage" class="btn btn-default" />
				</g:form>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
</body>
</html>
