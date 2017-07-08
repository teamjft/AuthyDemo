<g:if test="${flash.error}">
	<div class="alert alert-danger">
		${flash.error}
	</div>
</g:if>

<g:if test="${flash.message}">
	<div class="alert alert-success">
		${flash.message}
	</div>
</g:if>
