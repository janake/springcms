	<div>
		<#if node.id??>		
			<h1>${node.title}</h1>
			<ul class="nav nav-tabs">
  				<li>Show</li>
  					<@security.authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')">
  						<li><a href="/node/${node.id}/edit">Edit</a></li>
	 				</@security.authorize>      
			</ul>
			<div>
				${node.body}
			</div>			
		</#if>
	</div>
