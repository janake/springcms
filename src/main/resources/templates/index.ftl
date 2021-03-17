<#import "spring.ftl" as spring />
<#assign security=JspTaglibs["/META-INF/security.tld"]/>
<html>
<head>
<title>SpringCMS</title>
<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.css">
<script type="text/javascript" src="/webjars/ckeditor/4.6.2/standard/ckeditor.js"></script>
<script type="text/javascript" src="/js/createNewNode.js" defer></script>
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
	  	<#if fragment??>
			<#include "/fragments/"+ fragment>
		</#if>
	  </div>
	</div>

</body>
</html>