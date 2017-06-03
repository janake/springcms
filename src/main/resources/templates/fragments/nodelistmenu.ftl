<ul class="nav nav-pills nav-stacked">
	<li class="active"><a href="/node/new">Create new node</a></li>
	<#if nodes??>
		<#list nodes as node>
			<li>
				<a href="/node/${node.id}"/>${node.title}</a>
			</li>
		</#list>
	</#if>
</ul>