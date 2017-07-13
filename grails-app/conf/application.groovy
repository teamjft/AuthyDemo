// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'authydemo.SecUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'authydemo.SecUserSecRole'
grails.plugin.springsecurity.authority.className = 'authydemo.SecRole'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/fonts/**',    access: ['permitAll']],

	[pattern: '/**/favicon.ico', access: ['permitAll']],

    [pattern: '/registration/verify',   access: 'isAuthenticated()'],
    [pattern: '/registration/**',       access: ['permitAll']],
    [pattern: '/**',                    access: 'isAuthenticated()']
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/dashboard'

authy.accountSID = "YOUR_SID"
authy.authToken = "YOUR_AUTH_TOKEN"
authy.fromPhoneNumber = "YOUR_PHONE_NUMBER"
authy.apiKey = "YOUR_KEY"
