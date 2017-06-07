	<div>
		<#if node.id??>		
			<h1>${node.title}</h1>
			<ul class="nav nav-tabs">
  				<li><a href="/node/${node.id}">Show</a></li>
  					<@security.authorize access="hasAnyRole('admin', 'owner')">
  						<li><a href="/node/${node.id}/edit">Edit</a></li>
	 				</@security.authorize>      
			</ul>
			<div>
				${node.body}
			</div>			
		</#if>
	</div>
