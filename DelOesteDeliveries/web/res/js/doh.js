var doh = {
	getItems:function (elm)
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
  filter:function()
  {
    window.location="ventas.php?local="+$('#sLocal').val();
  },
  login:function()
  {
    if ($("#inputUser").val()=="Oscar") 
    {
      window.location="menu.php";
    }
    else
    {
      alert("Usuario incorrecto");
    }
  }
};
  $(document).ready(function() 
  {
    $('.datepicker').datepicker();
    $('#filterLocal').click(function() 
    {
      var local=$('#sLocal').val();
      var d1=$('#dp1').val();
      var d2=$('#dp2').val();
      window.location=window.location.pathname+"?local="+local+"&d1="+d1+"&d2="+d2;
    });
    $('#csvFile').click(function(name) 
    {
      var path=window.location.pathname;
      var query=window.location.search;
      if (query.length<1) 
      {
        query="?local=1&d1=2012-01-01&d2=2012-12-31";
      }
      window.location="data/"+path.substring(10, path.length - 4)+"CSV.php"+query;
    });

    var parts = window.location.search.substring(1,window.location.search.length).split("&");
    if (parts.length>=3) 
      {
    $('#sLocal').val(parts[0].split("=")[1]);
    $('#dp1').val(parts[1].split("=")[1]);
    $('#dp2').val(parts[2].split("=")[1]);
      }
    
  });

var eye = 
{
  left:function (time)
  {
    $('#logo').attr('src','img/logo-frames/2.gif');
      setTimeout(function()
        {
          $('#logo').attr('src','img/argus-small.png')
        },time); 
  },
  right:function (time)
  {
    $('#logo').attr('src','img/logo-frames/3.gif');
      setTimeout(function()
        {
          $('#logo').attr('src','img/argus-small.png')
        },time); 
  }
};
