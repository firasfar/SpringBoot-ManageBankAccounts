function deleteStudent(id) {


	swal({
		title: "Are you sure?",
		text: "Once deleted, you will not be able to recover this student!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {

				$.ajax({
					url: '/students/delete-ajax',
					type: 'POST',  // http method
					data: { 'id': id },  // data to submit
					success: function() {
						$("#" + id).remove();
						swal("Poof! Your student has been deleted!", {
							icon: "success",
						});
					}
				});


			} else {
				swal("Your student is safe!");
			}
		});




}