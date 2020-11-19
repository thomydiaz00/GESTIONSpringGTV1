 
function eliminarprofesor(idProf) {
	console.log(idProf);
	swal({
		  title: "Esta seguro de Eliminar?",
		  text: "Una vez eliminado no se prodra restablecer!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/admin/delete_profesor/"+idProf,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Registro eliminado satisfactoriamente!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/admin/lista_profesores";
		    	}
		    });
		  } 
		});
}
		
	