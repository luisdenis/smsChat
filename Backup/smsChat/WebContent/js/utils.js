/**
* Objeto creado para agrupar las funciones de validacion
* requeridas dentro de la aplicacion
*/
var validador = new Object();

/**
* Ejecuta la validacion del formulario de inicio de 
* 
* @param form formulario web donde se encuentran los campos a validar
*/
validador.verficarInicioSesion=function(form){
    if(form.j_username.value==''){
        alert("El nombre de usuario es requerido");
        
        form.j_username.focus();
        
        return false;
        
    } else if(form.j_password.value==''){
	    alert("El password de acceso es requerido");
	    
	    form.j_password.focus();   
	    
	    return false;
	} else {
		return true;    
	}
}


//----------------------------------------------------------//
/**
* Ejecuta la validacion del formulario de actualizacion de usuario 
* 
* @param form formulario web donde se encuentran los campos a validar
*/

var verificador = new Object();

verificador.verficarUserUpdate=function(form){
    if(form.name.value==''){
        alert("El nombre de usuario es requerido");
        
        form.name.focus();
        
        return false;
        
    } else if(form.lastName.value==''){
	    alert("El apellido de usuario es requerido");
	    
	    form.lastName.focus();   
	    
	    return false;
	} else if(form.nuIdentification.value==''){
		alert("La cedula de usuario es requerida");
	    
	    form.nuIdentification.focus();   
	    
	    return false;
	}else if(form.email.value==''){
		alert("El email de usuario es requerido");
	    
	    form.mail.focus();   
	    
	    return false;
	}else if(form.login.value==''){
		alert("El login de usuario es requerido");
	    
	    form.login.focus();   
	    
	    return false;
	}else if(form.password.value!=undefined && form.password.value==''){
		alert("El password de usuario es requerido");
	    
	    form.password.focus();   
	    
	    return false;
	}else if(!validarRoles(form)){
		
		alert("Seleccionar un rol de usuario es requerido");
	        
	    return false;
	}else {
		return true;    
	}
}

function validarRoles(form){		
	 for(i=0; ele=form.co_role[i]; i++){
	    if (ele.checked){
	     return true;
	    }
	 }
	  return false;			
}
//----------------------------------------------------------//
/**
* Objeto que encapsula la definicion de algunas operaciones
* requeridas para la gestion de roles
*/
var gestorRoles = new Object();

/**
* Permite confirmar la operacion de eliminacion del rol
* seleccionado.
*
* @param rolName nombre del rol a eliminar
* @param rolDesc descripcion del rol
*/
gestorRoles.confirmarEliminacion=function(rolName, rolDesc) {
    
    if (confirm("¿Esta seguro de eliminar: " + rolDesc + " ?")) {
        
        location.href = "crudRol.htm?operacion=1&rolName=" + rolName;
    }    
}

/**
* Permite desplegar en una nueva ventana la informacion 
* detallada del rol seleccionado.
*
* @param rolName nombre del rol del cual se desea conocer detalles
*/
gestorRoles.abrirDetalle=function(rolName) {
    window.open('crudRol.htm?operacion=0&rolName=' + rolName,'',
        'scrollbars=yes, left='+((screen.availWidth - 600)/2)+
        ', top='+((screen.availHeight - 570)/2)+ 
        ', height=570, width=600','');
}

/**
* Permite desplegar la informacion del rol seleccionado 
* a fin de poder ser editada.
*
* @param rolName nombre del rol a modificar
*/
gestorRoles.mostrarActualizacion=function(rolName) {        
    location.href = "crudRol.htm?operacion=2&rolName=" + rolName;        
}

/**
* Permite gestionar la paginacion de los resultados mostrados
* durante la busqueda de roles
*
* @param rolName nombre del rol buscado
* @param pagina numero de la pagina a mostrar
* @param limite total de registros a mostrar por pagina
*/
gestorRoles.paginar=function(pagina, rolName, limite) {        
    location.href = "crudRol.htm?operacion=3&pagina=" + pagina +
    "&rolName=" + rolName + "&searchLimit=" + limite ;
}

//-------------------------------------------------------------------//

/**
* Objeto que encapsula las operaciones requeridas
* para la gestion de usuarios.
*/
var gestorUsuarios = new Object();

/**
* Permite confirmar la operacion de eliminacion del usuario
* seleccionado.
*
* @param userFname nombre del usuario a eliminar
* @param userLname apellido del usuario
* @param userCi cedula del usuarios
*/
gestorUsuarios.confirmarEliminacion=function(userFname, userLname, userCi) {
    
    if (confirm("¿Esta seguro de eliminar el usuario " + userFname + " " + userLname + " ?")) {
        
        location.href = "crudUsuario.htm?operacion=1&userCedula=" + userCi;
    }    
}

/**
* Permite desplegar la informacion del usuarios seleccionado 
* a fin de poder ser editada.
*
* @param userCi cedula del usuario a mostrar
*/
gestorUsuarios.mostrarActualizacion=function(userCi) {
    
    location.href = "crudUsuario.htm?operacion=0&userCedula=" + userCi;
    
}

/**
* Permite gestionar la paginacion de los resultados mostrados
* durante la busqueda de usuarios
*
* @param nombre nombre del usuario buscado
* @param apellido apellido del usuario buscado
* @param cedula cedula del usuario buscado
* @param rol rol del usuario buscado
* @param pagina numero de la pagina a mostrar
* @param limite total de registros a mostrar por pagina
*/
gestorUsuarios.paginar=function(pagina, nombre, apellido, cedula, rol, limite) {        
    
    location.href = "crudUsuario.htm?operacion=2&pagina=" + pagina +
    "&searchLimit=" + limite + 
    "&nombre=" + nombre + "&apellido="+ apellido +
    "&ci=" + cedula + "&rol=" + rol ;
}


//-----------------------------------------------------------------------//

/**
* Objeto que encapsula las funciones necesarias para
* asociar los roles y componentes seleccionados al 
* momento de agregar y/o actualizar roles y usuarios
*/
var checkBoxUtil = new Object();

/**
* Funcion utilitaria utilizada para asociar al nuevo 
* usuario los roles definidos como checkBox dentro de
* de la pagina nuevoUsuario.jsp y actualizarUsuario.jsp
*
*
*/
checkBoxUtil.asociarRoles=function() {     
    var nodeList = document.getElementsByTagName("INPUT");
    
    var items = '';
    
    for (var i = 0; i < nodeList.length; i++){
        if(nodeList[i].type == 'checkbox' && nodeList[i].checked) {
            items = nodeList[i].value + "," + items;
        }
    }
    
    document.getElementById("rolesSeleccionados").value = items;
} 

/**
* Function que permite activar y desactivar los checkboxes 
* asociados a las permisologias de cada uno de los componentes
* que pueden ser asociados al rol
*/
checkBoxUtil.activarPermisos=function(check_comp, compName){
    if (check_comp.checked) { 
        if (document.getElementById("i_"+compName) != null){
            document.getElementById("i_"+compName).disabled = false;
            
        }
        if (document.getElementById("m_"+compName) != null){
            document.getElementById("m_"+compName).disabled = false;
            
        }
        if(document.getElementById("e_"+compName) != null){
            document.getElementById("e_"+compName).disabled = false;
        }
        
    }else {
        if (document.getElementById("i_"+compName) != null){
            document.getElementById("i_"+compName).disabled = true;

        }
        if (document.getElementById("m_"+compName) != null){
            document.getElementById("m_"+compName).disabled = true;

        }
        if(document.getElementById("e_"+compName) != null){
            document.getElementById("e_"+compName).disabled = true;
        }
    }
}
/**
* Funcion que permite asociar mediante un campo oculto
* los componentes que han sido asociados a un rol junto
* con sus permisologias. EL valor asociado a este campo
* oculto, una vez verificados cuales componentes han sido
* asociados, tiene la siguiente estructura:
*
*   nombre_componente(permisoInsercion, permisoModificacion, permisoEliminacion):
*
*   Ejemplo:
*
*   adm_usu(true,false,true):adm_roles(false,false, true):
*/
checkBoxUtil.asociarComponentes=function() {
    var nodeList = document.getElementsByTagName("INPUT");
    
    var items = '';
    
    for (var i = 0; i < nodeList.length; i++){
        if(nodeList[i].type == 'checkbox' && 
            nodeList[i].checked && nodeList[i].name == 'checkCmp') {        
        
            items = items + nodeList[i].value + "(";

            if (document.getElementById("i_"+ nodeList[i].value) != null){

                items = items + 
                    document.getElementById("i_"+ nodeList[i].value).checked +
                    ",";       
            } else {
                items = items + "false,";
            }

            if (document.getElementById("m_"+ nodeList[i].value) != null){
                items = items + 
                    document.getElementById("m_"+ nodeList[i].value).checked +
                    ",";
            } else {
                items = items + "false,";
            }

            if(document.getElementById("e_"+ nodeList[i].value) != null){
                items = items + 
                    document.getElementById("e_"+ nodeList[i].value).checked;
            } else {
                items = items + "false";
            }

            items = items + "):";

            //alert(items);          
        }
    }
    
    document.getElementById("cmpsSeleccionados").value = items;
}

/*Algoritmo para calcular md5 en javascript*/

/*
 * A JavaScript implementation of the RSA Data Security, Inc. MD5 Message
 * Digest Algorithm, as defined in RFC 1321.
 * Version 2.1 Copyright (C) Paul Johnston 1999 - 2002.
 * Other contributors: Greg Holt, Andrew Kepert, Ydnar, Lostinet
 * Distributed under the BSD License
 * See http://pajhome.org.uk/crypt/md5 for more info.
 */

/*
 * Configurable variables. You may need to tweak these to be compatible with
 * the server-side, but the defaults work in most cases.
 */
var hexcase = 0;  /* hex output format. 0 - lowercase; 1 - uppercase        */
var b64pad  = ""; /* base-64 pad character. "=" for strict RFC compliance   */
var chrsz   = 8;  /* bits per input character. 8 - ASCII; 16 - Unicode      */

/*
 * These are the functions you'll usually want to call
 * They take string arguments and return either hex or base-64 encoded strings
 */
function hex_md5(s){ return binl2hex(core_md5(str2binl(s), s.length * chrsz));}
function b64_md5(s){ return binl2b64(core_md5(str2binl(s), s.length * chrsz));}
function str_md5(s){ return binl2str(core_md5(str2binl(s), s.length * chrsz));}
function hex_hmac_md5(key, data) { return binl2hex(core_hmac_md5(key, data)); }
function b64_hmac_md5(key, data) { return binl2b64(core_hmac_md5(key, data)); }
function str_hmac_md5(key, data) { return binl2str(core_hmac_md5(key, data)); }

/*
 * Perform a simple self-test to see if the VM is working
 */
function md5_vm_test()
{
  return hex_md5("abc") == "900150983cd24fb0d6963f7d28e17f72";
}

/*
 * Calculate the MD5 of an array of little-endian words, and a bit length
 */
function core_md5(x, len)
{
  /* append padding */
  x[len >> 5] |= 0x80 << ((len) % 32);
  x[(((len + 64) >>> 9) << 4) + 14] = len;

  var a =  1732584193;
  var b = -271733879;
  var c = -1732584194;
  var d =  271733878;

  for(var i = 0; i < x.length; i += 16)
  {
    var olda = a;
    var oldb = b;
    var oldc = c;
    var oldd = d;

    a = md5_ff(a, b, c, d, x[i+ 0], 7 , -680876936);
    d = md5_ff(d, a, b, c, x[i+ 1], 12, -389564586);
    c = md5_ff(c, d, a, b, x[i+ 2], 17,  606105819);
    b = md5_ff(b, c, d, a, x[i+ 3], 22, -1044525330);
    a = md5_ff(a, b, c, d, x[i+ 4], 7 , -176418897);
    d = md5_ff(d, a, b, c, x[i+ 5], 12,  1200080426);
    c = md5_ff(c, d, a, b, x[i+ 6], 17, -1473231341);
    b = md5_ff(b, c, d, a, x[i+ 7], 22, -45705983);
    a = md5_ff(a, b, c, d, x[i+ 8], 7 ,  1770035416);
    d = md5_ff(d, a, b, c, x[i+ 9], 12, -1958414417);
    c = md5_ff(c, d, a, b, x[i+10], 17, -42063);
    b = md5_ff(b, c, d, a, x[i+11], 22, -1990404162);
    a = md5_ff(a, b, c, d, x[i+12], 7 ,  1804603682);
    d = md5_ff(d, a, b, c, x[i+13], 12, -40341101);
    c = md5_ff(c, d, a, b, x[i+14], 17, -1502002290);
    b = md5_ff(b, c, d, a, x[i+15], 22,  1236535329);

    a = md5_gg(a, b, c, d, x[i+ 1], 5 , -165796510);
    d = md5_gg(d, a, b, c, x[i+ 6], 9 , -1069501632);
    c = md5_gg(c, d, a, b, x[i+11], 14,  643717713);
    b = md5_gg(b, c, d, a, x[i+ 0], 20, -373897302);
    a = md5_gg(a, b, c, d, x[i+ 5], 5 , -701558691);
    d = md5_gg(d, a, b, c, x[i+10], 9 ,  38016083);
    c = md5_gg(c, d, a, b, x[i+15], 14, -660478335);
    b = md5_gg(b, c, d, a, x[i+ 4], 20, -405537848);
    a = md5_gg(a, b, c, d, x[i+ 9], 5 ,  568446438);
    d = md5_gg(d, a, b, c, x[i+14], 9 , -1019803690);
    c = md5_gg(c, d, a, b, x[i+ 3], 14, -187363961);
    b = md5_gg(b, c, d, a, x[i+ 8], 20,  1163531501);
    a = md5_gg(a, b, c, d, x[i+13], 5 , -1444681467);
    d = md5_gg(d, a, b, c, x[i+ 2], 9 , -51403784);
    c = md5_gg(c, d, a, b, x[i+ 7], 14,  1735328473);
    b = md5_gg(b, c, d, a, x[i+12], 20, -1926607734);

    a = md5_hh(a, b, c, d, x[i+ 5], 4 , -378558);
    d = md5_hh(d, a, b, c, x[i+ 8], 11, -2022574463);
    c = md5_hh(c, d, a, b, x[i+11], 16,  1839030562);
    b = md5_hh(b, c, d, a, x[i+14], 23, -35309556);
    a = md5_hh(a, b, c, d, x[i+ 1], 4 , -1530992060);
    d = md5_hh(d, a, b, c, x[i+ 4], 11,  1272893353);
    c = md5_hh(c, d, a, b, x[i+ 7], 16, -155497632);
    b = md5_hh(b, c, d, a, x[i+10], 23, -1094730640);
    a = md5_hh(a, b, c, d, x[i+13], 4 ,  681279174);
    d = md5_hh(d, a, b, c, x[i+ 0], 11, -358537222);
    c = md5_hh(c, d, a, b, x[i+ 3], 16, -722521979);
    b = md5_hh(b, c, d, a, x[i+ 6], 23,  76029189);
    a = md5_hh(a, b, c, d, x[i+ 9], 4 , -640364487);
    d = md5_hh(d, a, b, c, x[i+12], 11, -421815835);
    c = md5_hh(c, d, a, b, x[i+15], 16,  530742520);
    b = md5_hh(b, c, d, a, x[i+ 2], 23, -995338651);

    a = md5_ii(a, b, c, d, x[i+ 0], 6 , -198630844);
    d = md5_ii(d, a, b, c, x[i+ 7], 10,  1126891415);
    c = md5_ii(c, d, a, b, x[i+14], 15, -1416354905);
    b = md5_ii(b, c, d, a, x[i+ 5], 21, -57434055);
    a = md5_ii(a, b, c, d, x[i+12], 6 ,  1700485571);
    d = md5_ii(d, a, b, c, x[i+ 3], 10, -1894986606);
    c = md5_ii(c, d, a, b, x[i+10], 15, -1051523);
    b = md5_ii(b, c, d, a, x[i+ 1], 21, -2054922799);
    a = md5_ii(a, b, c, d, x[i+ 8], 6 ,  1873313359);
    d = md5_ii(d, a, b, c, x[i+15], 10, -30611744);
    c = md5_ii(c, d, a, b, x[i+ 6], 15, -1560198380);
    b = md5_ii(b, c, d, a, x[i+13], 21,  1309151649);
    a = md5_ii(a, b, c, d, x[i+ 4], 6 , -145523070);
    d = md5_ii(d, a, b, c, x[i+11], 10, -1120210379);
    c = md5_ii(c, d, a, b, x[i+ 2], 15,  718787259);
    b = md5_ii(b, c, d, a, x[i+ 9], 21, -343485551);

    a = safe_add(a, olda);
    b = safe_add(b, oldb);
    c = safe_add(c, oldc);
    d = safe_add(d, oldd);
  }
  return Array(a, b, c, d);

}

/*
 * These functions implement the four basic operations the algorithm uses.
 */
function md5_cmn(q, a, b, x, s, t)
{
  return safe_add(bit_rol(safe_add(safe_add(a, q), safe_add(x, t)), s),b);
}
function md5_ff(a, b, c, d, x, s, t)
{
  return md5_cmn((b & c) | ((~b) & d), a, b, x, s, t);
}
function md5_gg(a, b, c, d, x, s, t)
{
  return md5_cmn((b & d) | (c & (~d)), a, b, x, s, t);
}
function md5_hh(a, b, c, d, x, s, t)
{
  return md5_cmn(b ^ c ^ d, a, b, x, s, t);
}
function md5_ii(a, b, c, d, x, s, t)
{
  return md5_cmn(c ^ (b | (~d)), a, b, x, s, t);
}

/*
 * Calculate the HMAC-MD5, of a key and some data
 */
function core_hmac_md5(key, data)
{
  var bkey = str2binl(key);
  if(bkey.length > 16) bkey = core_md5(bkey, key.length * chrsz);

  var ipad = Array(16), opad = Array(16);
  for(var i = 0; i < 16; i++)
  {
    ipad[i] = bkey[i] ^ 0x36363636;
    opad[i] = bkey[i] ^ 0x5C5C5C5C;
  }

  var hash = core_md5(ipad.concat(str2binl(data)), 512 + data.length * chrsz);
  return core_md5(opad.concat(hash), 512 + 128);
}

/*
 * Add integers, wrapping at 2^32. This uses 16-bit operations internally
 * to work around bugs in some JS interpreters.
 */
function safe_add(x, y)
{
  var lsw = (x & 0xFFFF) + (y & 0xFFFF);
  var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
  return (msw << 16) | (lsw & 0xFFFF);
}

/*
 * Bitwise rotate a 32-bit number to the left.
 */
function bit_rol(num, cnt)
{
  return (num << cnt) | (num >>> (32 - cnt));
}

/*
 * Convert a string to an array of little-endian words
 * If chrsz is ASCII, characters >255 have their hi-byte silently ignored.
 */
function str2binl(str)
{
  var bin = Array();
  var mask = (1 << chrsz) - 1;
  for(var i = 0; i < str.length * chrsz; i += chrsz)
    bin[i>>5] |= (str.charCodeAt(i / chrsz) & mask) << (i%32);
  return bin;
}

/*
 * Convert an array of little-endian words to a string
 */
function binl2str(bin)
{
  var str = "";
  var mask = (1 << chrsz) - 1;
  for(var i = 0; i < bin.length * 32; i += chrsz)
    str += String.fromCharCode((bin[i>>5] >>> (i % 32)) & mask);
  return str;
}

/*
 * Convert an array of little-endian words to a hex string.
 */
function binl2hex(binarray)
{
  var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
  var str = "";
  for(var i = 0; i < binarray.length * 4; i++)
  {
    str += hex_tab.charAt((binarray[i>>2] >> ((i%4)*8+4)) & 0xF) +
           hex_tab.charAt((binarray[i>>2] >> ((i%4)*8  )) & 0xF);
  }
  return str;
}

/*
 * Convert an array of little-endian words to a base-64 string
 */
function binl2b64(binarray)
{
  var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
  var str = "";
  for(var i = 0; i < binarray.length * 4; i += 3)
  {
    var triplet = (((binarray[i   >> 2] >> 8 * ( i   %4)) & 0xFF) << 16)
                | (((binarray[i+1 >> 2] >> 8 * ((i+1)%4)) & 0xFF) << 8 )
                |  ((binarray[i+2 >> 2] >> 8 * ((i+2)%4)) & 0xFF);
    for(var j = 0; j < 4; j++)
    {
      if(i * 8 + j * 6 > binarray.length * 32) str += b64pad;
      else str += tab.charAt((triplet >> 6*(3-j)) & 0x3F);
    }
  }
  return str;
}



/***/







function changePasswordCheck(form) {

 md5= hex_md5(form.passwordACambiar.value);
// alert("este es el valor introducido convertido a md5: "+md5);
//alert("este es el valor del password actual: "+form.passwordActual.value);

	if(md5!=form.passwordActual.value){
	
        alert("La contraseña actual no coincide con la contraseña del Usuario  "+md5+"   y    "+form.passwordActual.value);
        
        form.passwordACambiar.focus();
        
        return false;
        
    }else if(form.password.value==''){
    	alert("El password no puede ser vacio");
    	form.password.focus();
    	return false;
    }else if(form.password2.value==''){
    	alert("La confirmación de password no puede ser vacio");
    	form.password2.focus();
    	return false;
    }else if(form.password.value!=form.password2.value){
	    alert("La contraseña y su confirmación no coinciden");	    
	    form.password.focus();		
		return false;
	}
	return true;
}



/*
Fecha y Hora By Chivi
*/

/* Coeminza el script del Reloj */

function actualizaReloj(){ 

/* Capturamos la Hora, los minutos y los segundos */
marcacion = new Date() 

/* Capturamos la Hora */
Hora = marcacion.getHours() 

/* Capturamos los Minutos */
Minutos = marcacion.getMinutes() 

/* Capturamos los Segundos */
Segundos = marcacion.getSeconds() 

/* Si la Hora, los Minutos o los Segundos
Son Menores o igual a 9, le añadimos un 0 */

if (Hora<=9)
Hora = "0" + Hora

if (Minutos<=9)
Minutos = "0" + Minutos

if (Segundos<=9)
Segundos = "0" + Segundos

/* Termina el Script del Reloj */


/* Coemienza eñ Script de la Fecha */

var Dia = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
var Mes = new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre");
var Hoy = new Date();
var Anio = Hoy.getFullYear();
var Fecha = Dia[Hoy.getDay()] + ", " + Hoy.getDate() + " de " + Mes[Hoy.getMonth()] + " de " + Anio + ", a las ";

/* Termina el script de la Fecha */


/* Creamos 4 variables para darle formato a nuestro Script */
var Inicio, Script, Final, Total

/*En Inicio le indicamos un color de fuente  y un tamaño */
//Inicio = "<font size=2 color=blue>"

/* En Reloj le indicamos la Hora, los Minutos y los Segundos */
Script = Fecha + Hora + ":" + Minutos + ":" + Segundos

/* En final cerramos el tag de la fuente */
//Final = "</font>"

/* En total Finalizamos el Reloj uniendo las variables */
Total = Script

/* Capturamos una celda para mostrar el Reloj */
document.getElementById('Fecha_Reloj').innerHTML = "<b>"+ Script+"</b>";

/* Indicamos que nos refresque el Reloj cada 1 segundo */
setTimeout("actualizaReloj()",1000) 
}



