 
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
					if(res == 1){
						swal("Debe desvincular a los profesores a cargo de la clase antes de borrarla", {icon:"warning"}).then((ok)=>{
							if(ok){
								window.location.reload();
							}
						})
					}else{
						swal("datos eliminados correctamente", {icon:"success"}).then((ok)=>{
							if(ok){
								window.location.reload();
							}
						})
					}
						
					
				},			
			  });
		  } 
		});
		
	}
	function eliminarClaseDeProfesor(idClase, idProf){
		console.log(idClase, idProf)

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
						url:"/admin/borrar_clase_profesor/"+idProf+'/'+idClase,
						success: function(res) {
						console.log(res);
					},			
					});
				swal("Datos eliminados satisfactoriamente", {
					icon: "success",
				}).then((ok)=>{
					if(ok){
						window.location.reload();
					}
				});
				} 
			});
	

	}
	function eliminarusuario(id) {
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
				   url:"/admin/eliminar_usuario/"+id,
				   success: function(res) {
					  console.log(res);
				  },			
				});
			  swal("Datos eliminados satisfactoriamente", {
				icon: "success",
			  }).then((ok)=>{
				  if(ok){
					  location.href="/admin/configuracion";
				  }
			  });
			} 
		  });
  }

	function eliminarhorario(id) {
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
						url:"/admin/eliminar_horario/"+id,
						success: function(res) {
						console.log(res);
					},			
					});
				swal("Datos eliminados satisfactoriamente", {
					icon: "success",
				}).then((ok)=>{
					if(ok){
						window.location.reload();
					}
				});
				} 
			});
	function eliminarusuario(id){
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
						url:"/admin/eliminar_usuario"+id,
						success: function(res) {
						console.log(res);
					},			
					});
				swal("Datos eliminados satisfactoriamente", {
					icon: "success",
				}).then((ok)=>{
					if(ok){
						window.location.reload();
					}
				});
				} 
			});
	}
}

		
	