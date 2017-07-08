<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Registration</title>
</head>

<body>
<h1>Registration Form</h1>

<g:render template="/template/flashMessage" />

<g:hasErrors bean="${form}">
	<g:eachError>
		<div class="alert alert-danger">
			<g:message error="${it}" />
		</div>
	</g:eachError>
</g:hasErrors>

<g:form controller="registration" action="save" method="post">
	<div class="form-group">
		<label for="name">Name:</label>
		<input type="text" class="form-control" name="name" id="name" value="${form?.name}">
	</div>

	<div class="form-group">
		<label for="email">Email:</label>
		<input type="email" class="form-control" name="email" id="email" value="${form?.email}">
	</div>

	<div class="form-group">
		<label for="password">Password:</label>
		<input type="password" class="form-control" name="password" id="password">
	</div>

	<div class="form-group">
		<label for="country">Country code:</label>
		<input type="text" class="form-control" name="countryCode" id="country" value="${form?.countryCode}">
	</div>

	<div class="form-group">
		<label for="phoneNumber">Phone number:</label>
		<input type="text" class="form-control" name="phoneNumber" id="phoneNumber" value="${form?.phoneNumber}">
	</div>
	<button class="btn btn-primary">Sign up</button>
</g:form>
</body>
</html>
