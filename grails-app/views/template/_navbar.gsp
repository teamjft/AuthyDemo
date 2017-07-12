<div class="navbar navbar-default navbar-static-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<g:if test="${params.controller != 'twilio'}">
				<a class="navbar-brand" href="/">
					<i class="fa grails-icon"><asset:image src="grails-cupsonly-logo-white.svg" /></i> Authy Demo
				</a>
			</g:if>
			<g:else>
				<a class="navbar-brand" href="${g.createLink(controller: 'twilio')}">
					<i class="fa grails-icon"><asset:image src="grails-cupsonly-logo-white.svg" /></i> Twilio Demo
				</a>
			</g:else>
		</div>

		<div class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
			<ul class="nav navbar-nav navbar-right">
				<sec:ifNotLoggedIn>
					<g:if test="${params.controller != 'registration'}">
						<li class="dropdown">
							<g:link controller="registration" action="index">Sign up</g:link>
						</li>
					</g:if>
					<li class="dropdown">
						<g:link controller="login">Login</g:link>
					</li>
				</sec:ifNotLoggedIn>
				<sec:ifLoggedIn>
					<li class="dropdown">
						<g:link controller='logout'>Logout</g:link>
					</li>
				</sec:ifLoggedIn>
			</ul>
		</div>
	</div>
</div>
