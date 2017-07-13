<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Message</title>
</head>

<body>
<h1>Message</h1>

<table class="table table-striped table-bordered">
	<tr><td>SID</td><td>${message.sid}</td></tr>
	<tr><td>Body</td><td>${message.body}</td></tr>
	<tr><td>From</td><td>***********</td></tr>
	<tr><td>To</td><td>***********</td></tr>
	<tr><td>Status</td><td>${message.status}</td></tr>

	<tr><td>Subresource Uris</td><td>${message.subresourceUris}</td></tr>
	<tr><td>Account Sid</td><td>${message.accountSid}</td></tr>
	<tr><td>Uri</td><td>${message.uri}</td></tr>

	<tr><td>Date Created</td><td>${message.dateCreated}</td></tr>
	<tr><td>Date Updated</td><td>${message.dateUpdated}</td></tr>
	<tr><td>Date Sent</td><td>${message.dateSent}</td></tr>

	<tr><td>Price Unit</td><td>${message.priceUnit}</td></tr>
	<tr><td>Price</td><td>${message.price}</td></tr>

	<tr><td>Error Code</td><td>${message.errorCode}</td></tr>
	<tr><td>Error Message</td><td>${message.errorMessage}</td></tr>
</table>
</body>
</html>
