<!doctype html>
<html lang="en" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />

	<title><g:layoutTitle default="Grails" /></title>

	<asset:stylesheet src="application.css" />

	<g:layoutHead />
</head>

<body>

<g:render template="/template/navbar" />

<div class="svg" role="presentation">
	<div class="grails-logo-container">
		<asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
	</div>
</div>

<div id="content" role="main">
	<section class="row colset-2-its">
		<g:layoutBody />
	</section>
</div>

<div class="footer" role="contentinfo"></div>

<div id="spinner" class="spinner" style="display:none;">
	<g:message code="spinner.alt" default="Loading&hellip;" />
</div>

<asset:javascript src="application.js" />
</body>
</html>
