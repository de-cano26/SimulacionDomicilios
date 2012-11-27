$(document).ready(function() 
{

}

});

var doh = {
	loadConfig:function (elm)
	{
		console.log($(elm).attr('id'));
    var codPunto = $(elm).parent().parent().attr('data-local');
		$.get('data/detalleVenta.php?id='+$(elm).attr('id')+'&local='+codPunto,
        function(result)
        {
            $('#backdrop').html(result);
            $('#modalItems').modal('show');
        }); 
	},
  runSim:function()
  {
    
  },
  showResults:function()
  {

  }
};
