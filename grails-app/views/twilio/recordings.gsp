<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Recordings</title>
</head>

<body>
<h1>Voice Recordings</h1>

<g:render template="/template/flashMessage" />

<table class="table table-striped table-bordered">
	<thead>
	<tr>
		<th>SID</th>
		<th>dateCreated</th>
		<th>dateUpdated</th>
		<th>status</th>
		<th>duration</th>
		<th>priceUnit</th>
		<th>price</th>
		<th>Action</th>
	</tr>
	</thead>
	<tbody>
	<g:each in="${recordings}" var="recording">
		<tr>
			<td><a href="https://api.twilio.com${recording.uri?.replace('.json', '')}" target="_blank">${recording.sid}</a></td>
			<td>${recording.dateCreated}</td>
			<td>${recording.dateUpdated}</td>
			<td>${recording.status}</td>
			<td>${recording.duration}</td>
			<td>${recording.priceUnit}</td>
			<td>${recording.price}</td>
			<td>
				<g:form controller="twilio" action="deleteRecording" method="POST">
					<g:hiddenField name="sid" value="${recording.sid}" />

					<g:submitButton 	name="submit" value="Delete Recording" class="btn btn-default" />
				</g:form>
			</td>
		</tr>
	</g:each>
	</tbody>
</table>
</body>
</html>
