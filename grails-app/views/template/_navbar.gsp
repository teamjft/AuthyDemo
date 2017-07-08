<div class="navbar navbar-default navbar-static-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/">
				<i class="fa grails-icon"><asset:image src="grails-cupsonly-logo-white.svg" /></i> Authy Demo
			</a>
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
