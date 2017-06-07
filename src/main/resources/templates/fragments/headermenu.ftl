<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">SpringCMS</a>
    </div>
    
    <ul class="nav navbar-nav">
      <li class="active"><a href="/">Home</a></li>
         <@security.authorize access="hasRole('ROLE_ADMIN')">
      		<li><a href="/admin/config">Site administration</a></li>
      		<li><a href="/newcontenttype">Create new contenttype</a></li>
		 </@security.authorize>      
    	 <@security.authorize access="isAuthenticated()">
     	 	<li><a href="/logout">Logout</a></li>
     	 <@security.authentication property="principal.username"/>
		 </@security.authorize>      
    	 <@security.authorize access="isAnonymous()">
            <li><a href="/login">Login</a></li>
		 </@security.authorize>      
    </ul>
  </div>
</nav>