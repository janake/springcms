<#import "spring.ftl" as spring />
<#assign security=JspTaglibs["/META-INF/security.tld"] />
<html>
	<head>
		<title>SpringCMS</title>
		<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.css">
	</head>
	<body>
	
	<div class="row">
	  <div class="col-sm-12 well">
	  	<#include "/fragments/headermenu.ftl">
	  </div>
	</div>

	<div class="row">
	  <div class="col-sm-3">
		<#include "/fragments/menu.ftl">
		<#include "/fragments/nodelistmenu.ftl">  
	  </div>
	  <div class="col-sm-9">
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

	  </div>
	</div>
	
	
	

		
	
	</body>
</html>