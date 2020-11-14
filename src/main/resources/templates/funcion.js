function eliminar(id){
    swal({
        title: "Esta seguro de eliminar este cliente?",
        text: "Una vez eliminado no podrá ser recuperado!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((willDelete) => {
        if (willDelete) {
            window.location.href='/admin/delete/'+id;
            swal("Poof! Your imaginary file has been deleted!",{
            icon: "success",
        });
        } else {
        swal("Your imaginary file is safe!");
        }
    });
