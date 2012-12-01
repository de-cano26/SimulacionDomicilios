$(document).ready(function() 
{
  $(".place>img").hover(
  function () 
  {
    $(this).siblings(".orders").css("visibility","visible");
  }, 
  function () 
  {
    $(this).siblings(".orders").css("visibility","hidden");
  }
  );
});

var doh = {
  timetoprepare:600000,
  timetodeliver:900000,
  scale:1000,
  deliveries:new Array(),
  locals:
  [
    {
      bikes:1,
      orders:0,
      records:[0,0,0,0]
    },
    {
      bikes:1,
      orders:0,
      records:[0,0,0,0]
    },
    {
      bikes:1,
      orders:0,
      records:[0,0,0,0]
    },
    {
      bikes:1,
      orders:0,
      records:[0,0,0,0]
    }
  ],
	loadConfig:function ()
	{
		doh.deliveries=mock.getDeliveries();
    console.log('conf loaded');
	},
  turn:0,
  running:false,
  start:function()
  {
    doh.running=true;
    doh.runSim(doh.turn);
  },
  runSim:function()
  {
    if (doh.running)
    {
      if (doh.turn<doh.deliveries.length) 
      {
        var delivery = doh.deliveries[doh.turn];
        setTimeout(
          function()
          {
            //Attend delivery
            doh.dispatch(delivery.day,delivery.sector);
            console.log('delivery '+doh.turn+' dispatched');
            doh.turn=doh.turn+1;
            doh.runSim()
          },
          delivery.time*1000/doh.scale);
      }
      else
      {
        console.log('Simulation completed');
        doh.turn=0;
      } 
    }     
  },
  stopSim:function()
  {
    doh.running=false;
  },
  dispatch:function(day,sector)
  {
    var local = doh.locals[sector];
    //Prepare delivery
    setTimeout(
        function()
        {
          if (local.bikes>0) 
          {
            //Attend delivery
            console.log('Attend data:'+day+" sector:"+sector);
            $("#local"+sector+">.bike>img").animate(
            {
              left: '+=545'
            }, doh.timetodeliver/doh.scale*2, function() 
            {
              local.records[day]=local.records[day]+1;
              console.log("Deliver arrived");
              $("#local"+sector+">.bike>img").animate(
              {
                left: '-=545'
              }, doh.timetodeliver/doh.scale*2, function() 
              {
                console.log("Bike returned");
              });
            });
          }
          else
          {
            //To wait
          }
        },
        doh.timetoprepare/doh.scale);
  }
};

var result = 
{
  newDeliverie:function (elm)
  {
    
  },
  showResults:function()
  {

  }
};

var mock=
{
  getDeliveries:function()
  {
    var dels = new Array();

    dels[0]={day:1,time:600,sector:1};
    dels[1]={day:1,time:1200,sector:2};
    dels[2]={day:1,time:1800,sector:1};
    dels[3]={day:1,time:2400,sector:1};
    dels[4]={day:1,time:3000,sector:2};
    dels[5]={day:1,time:3600,sector:1};
    dels[6]={day:1,time:4200,sector:1};
    dels[7]={day:1,time:4800,sector:1};
    dels[8]={day:1,time:5200,sector:2};  
    dels[9]={day:1,time:5800,sector:2};

    return dels;
  },
  getResults:function()
  {

  }
}