<%--
==========================================================
Fragmento de pagina para definir los aspectos comun en el 
header de todas las paginas de la aplicacion. La definicion
de estilos, javascripts, meta instrucciones deben ser hechas
en este fragmento.
==========================================================
--%>
<head>
	<meta charset="utf-8" />
    <title><fmt:message key="PROJECT.HEADER.TITLE"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="BLVE" />
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
 	<meta http-equiv="content-language" content="es_VE" />
 	
	
	<link href="css/bootstrap.css" rel="stylesheet">
   	<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
    <link href="css/pagination.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
	
	
	<link REL="SHORTCUT ICON" href="images/logo_tigo.ico">
	
	
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/modernizr.custom.35794.js"></script>
	<script type="text/javascript" src="js/jquery.pagination.js"></script>
	
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript" src="js/utils.js" ></script>
	<script type="text/javascript" src="js/jquery.placeholder.js"></script>
	
    <meta HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE" />
    <meta http-equiv="refresh" content="<%= session.getMaxInactiveInterval() %>">
    
    
</head>