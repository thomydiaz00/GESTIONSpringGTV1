 
function eliminarprofesor(id) {
	console.log(id);
	swal({
		  title: "Esta seguro de eliminar estos datos?",
		  text: "Una vez eliminado no se prodra restablecer!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/admin/delete_profesor/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Datos eliminados satisfactoriamente", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/admin/lista_profesores";
		    	}
		    });
		  } 
		});
}
function eliminarclase(id) {
	console.log(id);
	swal({
		  title: "Esta seguro de eliminar estos datos?",
		  text: "Una vez eliminado no se prodra restablecer!",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/admin/delete_clase/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Datos eliminados satisfactoriamente", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/admin/lista_clases";
		    	}
		    });
		  } 
		});
}
		
	