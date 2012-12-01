$(document).ready(function() 
{
  $(".place>.pventa").hover(
  function () 
  {
    $(this).siblings(".orders").css("visibility","visible");
  }, 
  function () 
  {
    $(this).siblings(".orders").css("visibility","hidden");
  }
  );
  $("#correr").click(
  function()
  {
    doh.loadConfig();
    doh.start();
    $(this).attr("id","parar");
  }  
  );
  $("#parar").click(
  function()
  {
    doh.stopSim();
    $(this).attr("id","correr");
  }  
  );
  
});

var doh = {
  timetoprepare:300000,
  timetodeliver:700000,
  timeforqueue:500000,
  scale:3000,
  deliveries:new Array(),
  locals:
  [
    {
      bikes:1,
      orders:0,
      records:[0,0,0,0],
      queue:0
    },
    {
      bikes:1,
      orders:0,
      records:[0,0,0,0],
      queue:0
    },
    {
      bikes:1,
      orders:0,
      records:[0,0,0,0],
      queue:0
    },
    {
      bikes:1,
      orders:0,
      records:[0,0,0,0],
      queue:0
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
    doh.wipeRecords();
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
            doh.runSim();
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
    var localBikes = doh.locals[sector].bikes;
    var localQueue = doh.locals[sector].queue;
    //Fill local with deliver
    var newPosVenta = (localQueue+1)*101;
    $("#local"+sector+">.place>.pventa").css("background-position","0 -"+newPosVenta+"px");
    //Prepare delivery
    setTimeout(
        function()
        {
          //If bikes available
          if (localBikes>0) 
          {
            //Bike's gone
            local.bikes=local.bikes-1;
            //Time to deliver
            var timeto = (doh.timetodeliver/doh.scale*2)+(doh.timeforqueue*localQueue)/doh.scale;
            //Attend delivery
            console.log('Attend data:'+day+" sector:"+sector);
            $("#local"+sector+">.road>.bike").animate({left: '+=440'}, timeto, 
              function() 
              {
                //Bike gets to destination
                //Empty local
                var newPosVenta = (localQueue)*101;
                $("#local"+sector+">.place>.pventa").css("background-position","0 -"+newPosVenta+"px");
                var numCola = (doh.locals[sector].queue*54)+1;
                $("#local"+sector+">.place>.orders").css("background-position","0 -"+numCola+"px");
                //Pay
                $("#local"+sector+">.people>img").attr("src","res/img/cash.png");
                setTimeout(function()
                {
                  //Hello folk
                  var persona = Math.floor((Math.random()*12)+1);
                  $("#local"+sector+">.people>img").attr("src","res/img/people/"+persona+".png");
                },500);
                //New records
                local.records[day]=local.records[day]+1+local.queue;
                var suma = doh.locals[0].records[day]+doh.locals[1].records[day]+doh.locals[2].records[day]+doh.locals[3].records[day];
                $("#day"+day+">h2").html(suma);
                local.queue=0;
                console.log("Deliver arrived-"+localQueue);
                $("#local"+sector+">.road>.bike").css("background-position","-51px 0px");
                $("#local"+sector+">.road>.bike").animate({left: '-=440'}, doh.timetodeliver/doh.scale*2, 
                  function() 
                  {
                    //All done, phew!
                    console.log("Bike returned");
                    $("#local"+sector+">.road>.bike").css("background-position","0px 0px");
                    local.bikes=local.bikes+1;
                  }
                );
              }
            );
          }
          else
          {
            //To wait
            doh.locals[sector].queue+=1;
            var numCola = (doh.locals[sector].queue*54)+1;
            $("#local"+sector+">.place>.orders").css("background-position","0 -"+numCola+"px");
            $("#local"+sector+">.place>.orders").css("visibility","visible");
            setTimeout(
            function()
            {
              $("#local"+sector+">.place>.orders").css("visibility","hidden");
            },2000);
          }
        },
        doh.timetoprepare/doh.scale);
  },
  wipeRecords:function()
  {}
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
    dels[10]={day:2,time:600,sector:1};
    dels[11]={day:2,time:1200,sector:2};
    dels[12]={day:2,time:1800,sector:1};
    dels[13]={day:2,time:2400,sector:1};
    dels[14]={day:2,time:3000,sector:2};
    dels[15]={day:2,time:3600,sector:1};
    dels[16]={day:2,time:4200,sector:1};
    dels[17]={day:2,time:4800,sector:1};
    dels[18]={day:2,time:5200,sector:2};  
    dels[19]={day:2,time:5800,sector:2};

    return dels;
  },
  getResults:function()
  {

  }
}

function testGet()
{
  $.post('dataServlet.htm',{ method: "generate" },function(data)
    {
      alert(data);
    });
}