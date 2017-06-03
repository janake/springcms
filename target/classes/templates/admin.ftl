<#import "spring.ftl" as spring />
<#assign security=JspTaglibs["/META-INF/security.tld"] /><html>
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
		<h2>Administration<h2>
	  </div>
	</div>
</body>
</html>