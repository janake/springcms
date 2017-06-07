<form action="/node/${node.id}/save" method="POST">
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