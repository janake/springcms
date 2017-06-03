<#import "spring.ftl" as spring />
<#assign security=JspTaglibs["/META-INF/security.tld"] />
<html>
	<head>
		<title>
		
		</title>
		<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.css">
		<script src="/webjars/ckeditor/4.6.2/standard/ckeditor.js" type="text/javascript"></script>
		<script src="/js/createNewNode.js" type="text/javascript" defer></script>
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
		<form action="/node/createNewNode" method="POST">
			<div class="well">
				<label for="title">Title: </label>
				<@spring.formInput "node.title", "class=form-control"/>
				<@spring.showErrors ""/>
			</div>
			<div class="well">
				<@spring.formTextarea "node.body"/>
			</div>
			<div class="well">
				<button class="btn-success" type="submit">Save</button>
			</div>
		</form>
	  </div>
	</div>

	</body>
</html>