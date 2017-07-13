<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main" />
	<title>Send ${params.mms ? 'MMS' : 'SMS'}</title>
</head>

<body>
<h1>Send ${params.mms ? 'MMS' : 'SMS'}</h1>

<p>Send message to your phone</p>

<g:render template="/template/flashMessage" />

<g:form controller="twilio" action="sendMessage" method="POST">
	<div class="col-md-12">
		<div class="form-group">
			<textarea class="form-control textarea" rows="5" name="message" placeholder="Message" required></textarea>
		</div>
	</div>
	<g:if test="${params.mms}">
		<div class="col-md-12">
			<div class="form-group">
				<input type="url" name="url" class="form-control textarea" placeholder="Media URL" required/>
			</div>
		</div>
	</g:if>

	<div class="col-md-12">
		<button type="submit" class="btn main-btn btn-success pull-right">Send Message</button>
	</div>
</g:form>
</body>
</html>
