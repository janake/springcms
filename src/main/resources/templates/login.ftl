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
	  </div>
	  <div class="col-sm-9">

		<h1>Login</h1>
		   <form name='f' action="/security_check" method='POST'>
		      <table>
		         <tr>
		            <td>User:</td>
		            <td><input type='text' name="username" value=''></td>
		         </tr>
		         <tr>
		            <td>Password:</td>
		            <td><input type='password' name='password' /></td>
		         </tr>
		         <tr>
		            <td><input name="submit" type="submit" value="submit" /></td>
		         </tr>
		      </table>
		  </form>

	  </div>
	</div>


</body>
</html>




