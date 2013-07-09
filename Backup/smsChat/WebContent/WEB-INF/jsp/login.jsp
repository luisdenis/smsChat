<%-- 
==========================================================
Autor(s):    BLVE C.A
==========================================================
Fecha:       05-Mayo-2013
Descripcion: 
             Pagina de Inicio de Sesion  
Controlador(es) asociado(s): 
             1.- loginController
==========================================================
--%>
<%@ include file="/WEB-INF/jsp/jspf/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="es">
    <%@ include file="/WEB-INF/jsp/jspf/header.jsp" %>    
    <body onload="inciarLogin();">
    	<div>&nbsp;</div>
		<div id= "content" class="container">
			
			<form method="post" action="./j_spring_security_check" name="loginForm" id="loginForm"  class="form-signin" >
				<core:choose>
		             <core:when test="${param.login_error==1}">
		             	<div id="message" class="alert alert-block alert-error fade in">
							<strong>Error!!</strong>
							<p><fmt:message key="errors.auth.invalid"/></p>
						</div>
		             </core:when>
		             <core:when test="${param.login_error==2}">
		             	<div id="message" class="alert alert-block alert-error fade in">
							<strong><fmt:message key="errors.session.expired"/></strong>
						</div>
		             </core:when>
		          </core:choose>
		        <div width= "100%" align="center"><img src="images/logo_login.png"></div>
				<h2  class="form-signin-heading"><fmt:message key="TITLE.AUTHENTICATION"/></h2>
				<input id="nombre" type="text" class="input-block-level" placeholder="Nombre de usuario" name="j_username" maxlength="30" />
				<input id="password" type="password" class="input-block-level" placeholder="Contraseña"  name="j_password" maxlength="30" />
				<span id="idbutton">
					<input id="submit" class="btn btn-large btn-danger" name="entrar" type="submit" id="entrar" value="<fmt:message key="BUTTON.ENTER"/>"/>
				</span>
			</form>  
		</div>
    </body>
</html>