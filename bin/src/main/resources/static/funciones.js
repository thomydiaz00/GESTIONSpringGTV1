 function clearSearch() {
        window.location.href = '/admin/listar';
    }
function clearSearchProductos() {
        window.location.href = '/admin/listarproductos';
    }
    
function clearSearchUser() {
        window.location.href = '/user/listar';
    }
    
function clearSearchProductosUser() {
        window.location.href = '/user/listarproductos';
    }

function eliminarpersona(id) {
	console.log(id);
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
				 url:"/admin/delete/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Registro eliminado satisfactoriamente!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/admin/listar";
		    	}
		    });
		  } 
		});
		

}

function eliminarproducto(idproducto) {
	console.log(idproducto);
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
				 url:"/admin/deleteproducto/"+idproducto,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Registro eliminado satisfactoriamente!", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/admin/listarproductos";
		    	}
		    });
		  } 
		});
		

}