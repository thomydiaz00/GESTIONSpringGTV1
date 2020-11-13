
$(document).ready(function(){
    $("#buscar_profesor").on("keyup", function() {
      var value = $(this).val().toLowerCase();
      $("#tabla_profesores tr").filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
});
  
