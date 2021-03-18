<form action="/node/${node.id}/save" method="POST">
	<div class="well">
		<label for="title">Title: </label>
		<@spring.formInput "node.title", "class=form-control"/>
		<@spring.showErrors ""/>
	</div>
	<div class="well">
		<@spring.formTextarea "node.body"/>
	</div>
	<script>
        ClassicEditor
            .create( document.querySelector( '#body' ) )
            .then( editor => {
                console.log( editor );
            } )
            .catch( error => {
                console.error( error );
            } );
    </script>
	<div class="well">
		<button class="btn-success" type="submit">Save</button>
	</div>
</form>