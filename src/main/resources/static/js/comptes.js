function deleteCompte(rib) {


	swal({
		title: "Are you sure?",
		text: "Once deleted, you will not be able to recover this compte!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {

				$.ajax({
					url: '/comptes/delete-ajax',
					type: 'POST',  // http method
					data: { 'rib': rib },  // data to submit
					success: function() {
						$("#" + rib).remove();
						swal("Poof! Your compte has been deleted!", {
							icon: "success",
						});
					}
				});


			} else {
				swal("Your compte is safe!");
			}
		});




}