var listSubscriptor,searchNumber,searchEstado,page;

function inciarLogin(){
	$('input, text').placeholder();
	
}

function pageUserService(page_index, jq){
        
	
    var urlS= "serviceUser.htm";
    document.getElementById('idtable').innerHTML = " ";
    document.getElementById('idloadingdiv').innerHTML = '<img id="loading" alt="Cargando" src="images/loading.gif" width="30" height="30" />&nbsp;Cargando....';
    page = page_index+1;
      var xhrServicesTest = $.ajax({ url: urlS, dataType: 'JSON', type: "POST", data: {n  : "1", page : page_index+1, path : "userService", flagAjax : true} ,
	  async: false, cache:false, success: function(list){
	           listSubscriptor = list;	 
			// Create our containing table
			    var table = document.createElement('table');
			    table.setAttribute('class','table table-hover table-condensed table-bordered');
			    var thead = document.createElement('thead');
			    table.appendChild(thead);

			    var tr = document.createElement('tr');
			    thead.appendChild(tr);

			    var thLabel = document.createElement('th');
			    tr.appendChild(thLabel);
			    thLabel.innerHTML = '#';
			    thLabel = document.createElement('th');
			    tr.appendChild(thLabel);
			    thLabel.innerHTML = 'Suscriptor';
			    thLabel = document.createElement('th');
			    tr.appendChild(thLabel);
			    thLabel.innerHTML = 'Alias';
			    thLabel = document.createElement('th');
			    tr.appendChild(thLabel);
			    thLabel.innerHTML = 'Suscrito';
			    thLabel = document.createElement('th');
			    tr.appendChild(thLabel);
			    thLabel.innerHTML = 'Estado';
			    thLabel = document.createElement('th');
			    tr.appendChild(thLabel);
			    thLabel.innerHTML = 'Fecha de Última Actividad';
			    thLabel = document.createElement('th');
			    tr.appendChild(thLabel);
			    thLabel.innerHTML = 'Ultima Fecha de Cobro';
			    thLabel = document.createElement('th');
			    tr.appendChild(thLabel);
			    thLabel.innerHTML = 'Operaciones';

			    var tbody = document.createElement('tbody');
			    table.appendChild(tbody);

			      for( var i in list){
			            
			    	  	var tr = document.createElement('tr');
			            tbody.appendChild(tr);
			            tr.setAttribute("id",""+list[i].subscribeaid);
			            if(i % 2 == 0)
			            tr.setAttribute("class","row2");
			            else
			            tr.setAttribute("class","row1");
			            var tdLabel1 = document.createElement('td');
			            //tdLabel1.setAttribute("onclick","detailMaquina("+list[i].id_maquina+")");
			            tr.appendChild(tdLabel1);
			            tdLabel1.innerHTML = ''+list[i].subscribeaid;

			            
			            var tdLabel1 = document.createElement('td');
			            //tdLabel1.setAttribute("onclick","detailMaquina("+list[i].id_maquina+")");
			            tr.appendChild(tdLabel1);
			            tdLabel1.innerHTML = ''+list[i].msisdn;

			            var tdLabel1 = document.createElement('td');
			            //tdLabel1.setAttribute("onclick","detailMaquina("+list[i].id_maquina+")");
			            tr.appendChild(tdLabel1);
			            tdLabel1.innerHTML = ''+list[i].alias;

			            tdLabel1 = document.createElement('td');
			            //tdLabel1.setAttribute("onclick","detailMaquina("+list[i].id_maquina+")");
			            tr.appendChild(tdLabel1);
			            var listSubscribeB = list[i].subscribebTbls;
			            var stringBuilder = "<ul>";
			            for( var j in listSubscribeB){
			            	if(j < 5)
			            	stringBuilder = stringBuilder +"<li>"+listSubscribeB[j].msisdn+"- "+listSubscribeB[j].alias+" </li>";
			            	
			            }
			            stringBuilder = stringBuilder +"</ul>";
			            if(listSubscribeB.length > 5) stringBuilder = stringBuilder+'<div align = "right"><a  href = "javascript:consultarServicioUser('+list[i].subscribeaid+');" >ver más...</a></div>';
			            	
			            tdLabel1.innerHTML = ""+stringBuilder;
			            
			            
			            tdLabel1 = document.createElement('td');
			            tdLabel1.setAttribute("id",list[i].subscribeaid+"-estadolist");
			            //tdLabel1.setAttribute("onclick","detailMaquina("+list[i].id_maquina+")");
			            tr.appendChild(tdLabel1);
			            tdLabel1.innerHTML = ''+list[i].estado;

			            tdLabel1 = document.createElement('td');
			            //tdLabel1.setAttribute("onclick","detailMaquina("+list[i].id_maquina+")");
			            tr.appendChild(tdLabel1);
			            tdLabel1.innerHTML = ''+list[i].fechaultimaactividad;

			            tdLabel1 = document.createElement('td');
			           // tdLabel1.setAttribute("onclick","detailMaquina("+list[i].id_maquina+")");
			            tr.appendChild(tdLabel1);
			            tdLabel1.innerHTML = ''+list[i].fechacobro;

			            tdLabel1 = document.createElement('td');
			            tdLabel1.setAttribute("id",list[i].subscribeaid+"-operacionlist");
			            tr.appendChild(tdLabel1);
			            var stringBuilder  = '<img src = "images/server_detail.png"  rel="tooltip"  onclick = "consultarServicioUser('+list[i].subscribeaid+');"  />&nbsp;';
			            if(list[i].estado != "INACTIVO")
			            stringBuilder = stringBuilder +'<img src = "images/server_shutdown.png"  rel="tooltip" title="ShutDown"  onclick = "eliminarMaquina('+list[i].subscribeaid+','+list[i].msisdn+');" />  ';
			            tdLabel1.innerHTML = ''+stringBuilder;
			      }
			      
			      document.getElementById("idtable").appendChild(table);
			      
	  }, error: function(xhrServicesTest, textStatus, errorThrown){
	    alert("No se pudo establecer comunicacion con el servidor.\ Por favor intenta de nuevo.");
		console.log(""+textStatus);
		console.log(""+errorThrown);
	  }
	  });
     document.getElementById('idloadingdiv').innerHTML = '';
    
    
    //obtenerListaBitacora(page_index+1);
    //document.getElementById('idloadingdiv').innerHTML = '';
}


function consultarServicioUser(id){

    $("#myModal").off("hidden");
    $('#myModal').modal('show');
    $('#formularioagregarmaquina').unbind('submit');
    document.getElementById("idListaNumberB").innerHTML = "";
    
    for(var i in listSubscriptor){
    	
    	if(listSubscriptor[i].subscribeaid == id){
    		
    		$("#idNumeroA").val(listSubscriptor[i].msisdn);
    		$("#estadoConsulta").val(listSubscriptor[i].estado);
    		$("#fechaActividad").val(listSubscriptor[i].fechaultimaactividad);
    		$("#fechaCobro").val(listSubscriptor[i].fechacobro);
    		
    		if(listSubscriptor[i].estado == "INACTIVO")
    		$("#guardar").attr("disabled", "disabled");
    		else $("#guardar").removeAttr("disabled");
    		
    		
    		var listNumberB = listSubscriptor[i].subscribebTbls;
    		//Tabla para la lista de numero
    		
    		if(listNumberB.length > 0){
	    		var table = document.createElement('table');
	    	    table.setAttribute('class','table table-hover table-condensed');
	    	    table.setAttribute('id','idtablecomponente');
	    	    var thead = document.createElement('thead');
	    	    table.appendChild(thead);
	
	    	    var tr = document.createElement('tr');
	    	    thead.appendChild(tr);
	
	    	    var thLabel = document.createElement('th');
	    	    tr.appendChild(thLabel);
	    	    thLabel.innerHTML = '#';
	    	    thLabel = document.createElement('th');
	    	    tr.appendChild(thLabel);
	    	    thLabel.innerHTML = 'Alias';
	    	    thLabel = document.createElement('th');
	    	    tr.appendChild(thLabel);
	    	    thLabel.innerHTML = 'Número B';
	    	    thLabel = document.createElement('th');
	    	    tr.appendChild(thLabel);
	    	    thLabel.innerHTML = 'Estado';
	    	    thLabel = document.createElement('th');
	    	    tr.appendChild(thLabel);
	    	    thLabel.innerHTML = 'Fecha actividad';
	    	    
	    	    thLabel = document.createElement('th');
	    	    tr.appendChild(thLabel);
	    	    thLabel.innerHTML = 'Fecha cobro';
	    	    
	    	    thLabel = document.createElement('th');
	    	    tr.appendChild(thLabel);
	    	    thLabel.innerHTML = 'Operación';
	
	    	    var tbody = document.createElement('tbody');
	    	    table.appendChild(tbody);
	
	    	    for(var j in listNumberB ){
	    	            var tr = document.createElement('tr');
	    	            tr.setAttribute("id",""+listNumberB[j].subscribebid+"-consulta");
	    	            tbody.appendChild(tr);
	    	            var tdLabel1 = document.createElement('td');
	    	            tdLabel1.setAttribute("style","text-align: center;");
	    	            tr.appendChild(tdLabel1);
	    	            var cont = parseInt(j)+1;
	    	            tdLabel1.innerHTML = ''+cont;
	
	    	            tdLabel1 = document.createElement('td');
	    	            tdLabel1.setAttribute("style","text-align: center;");
	    	            tr.appendChild(tdLabel1);
	    	            tdLabel1.innerHTML = ''+listNumberB[j].alias;
	    	            
	    	            tdLabel1 = document.createElement('td');
	    	            tdLabel1.setAttribute("style","text-align: center;");
	    	            tr.appendChild(tdLabel1);
	    	            tdLabel1.innerHTML = ''+listNumberB[j].msisdn;
	    	            
	    	            tdLabel1 = document.createElement('td');
	    	            	    	            
	    	            tdLabel1.setAttribute("id",listNumberB[j].subscribebid+"-consultaestado");
	    	            tdLabel1.setAttribute("style","text-align: center;");
	    	            tr.appendChild(tdLabel1);
	    	            tdLabel1.innerHTML = ''+listNumberB[j].estado;
	    	            
	    	            tdLabel1 = document.createElement('td');
	    	            tdLabel1.setAttribute("style","text-align: center;");
	    	            tr.appendChild(tdLabel1);
	    	            tdLabel1.innerHTML = ''+listNumberB[j].fechaultimaactividad;
	    	            
	    	            tdLabel1 = document.createElement('td');
	    	            tdLabel1.setAttribute("style","text-align: center;");
	    	            tr.appendChild(tdLabel1);
	    	            tdLabel1.innerHTML = ''+listNumberB[j].fechacobro;
	    	            
	    	            tdLabel1 = document.createElement('td');
	    	            tdLabel1.setAttribute("id",listNumberB[j].subscribebid+"-consultaoperation");
	    	            tdLabel1.setAttribute("style","text-align: center;");
	    	            tr.appendChild(tdLabel1);
		    	        if(listNumberB[j].estado != "INACTIVO"){
		    	            tdLabel1.innerHTML = '<img align="center" src="images/server_shutdown.png"  onclick = "eliminarMaquinaInConsulta('+listNumberB[j].subscribebid+','+listNumberB[j].msisdn+');"  />';
	    	            }
	    	      }
	
	    	      document.getElementById("idListaNumberB").appendChild(table);
    		}else{
    			
    			document.getElementById("idListaNumberB").innerHTML = "NO TIENE NÚMERO ASOCIADO";
    		}
    	}
    }
  }

function eliminarMaquina(id,numeroLocal){
    if (confirm("¿Está seguro de dar de baja al número "+numeroLocal+"?")) {
   // Respuesta afirmativa...
   var urlS= "serviceDelete.htm";
   xhrServicesTest = $.ajax({ url: urlS, dataType: 'JSON', type: "GET", data: {id: id, operation : 1} ,async: false, cache:false, success: function(data){
	  if(data == "true" ){
	      setTimeout("elimiarRow("+id+")", 2000);
	      $('#'+id).addClass("warning");
	      $('#'+id+"-estadolist").html("INACTIVO");
	      var stringBuilder  = '<img src = "images/server_detail.png"  rel="tooltip"  onclick = "consultarServicioUser('+id+');"  />';
	      $('#'+id+"-operacionlist").html(stringBuilder);
	      
	  }
    $("#idloadingdiv").html('<b class="text-success">El número de telefono ha sido dado de baja</b>&nbsp;<img src="img/check_si.png"/>');

     }, error: function(xhrServicesTest, textStatus, errorThrown){
       alert("No se pudo establecer comunicacion con el servidor.\ Por favor intenta de nuevo.");
       console.log(""+textStatus);
       console.log(""+errorThrown);
     }
     });
   }

 }

function eliminarMaquinaInConsulta(id,numeroLocal){
	if (confirm("¿Está seguro de dar de baja al número "+numeroLocal+"?")) {
	   // Respuesta afirmativa...
	   var urlS= "serviceDelete.htm";
	   xhrServicesTest = $.ajax({ url: urlS, dataType: 'JSON', type: "GET", data: {id: id, operation : 0} ,async: false, cache:false, success: function(data){
	  if(data == "1" ){
	      setTimeout(function(){
	    	  $('#'+id+'-consulta').removeClass("warning");
	    	  $("#idmensajeresultconsulta").html('');
	      }, 2000);
	      $('#'+id+'-consulta').addClass("warning");
	      $('#'+id+"-consultaestado").html("INACTIVO");
	      $('#'+id+"-consultaoperation").html("");
	      $("#idmensajeresultconsulta").html('<b class="text-success">El número de telefono ha sido dado de baja</b>');
	      
	      var urlS= "serviceUser.htm";
		  var xhrServicesTest = $.ajax({ url: urlS, dataType: 'JSON', type: "POST", data: {n  : "1", page : page, path : "userService", flagAjax : true} ,
			 async: true, cache:false, success: function(list){
				 listSubscriptor = list;	 
		  }
		  });
	      
	  }else{
		  $("#idmensajeresultconsulta").html('<b class="text-error">No se puedo dar de baja!!!</b>');
		  
	  }
	 }, error: function(xhrServicesTest, textStatus, errorThrown){
	   alert("No se pudo establecer comunicacion con el servidor.\ Por favor intenta de nuevo.");
	   console.log(""+textStatus);
	   console.log(""+errorThrown);
	     }
	     });
	}	
}

function elimiarRow(id){
	$('#'+id).removeClass("warning");
	$("#idloadingdiv").html('');
	
}
