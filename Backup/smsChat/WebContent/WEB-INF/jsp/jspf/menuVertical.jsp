<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     <div class="span2">
        <div class="well">
             <ul class="nav nav-list" id = "idmenu">
				<li class="nav-header">SMS CHAT</li>
				<core:choose>
					<core:when test="${menu == 0}">
						
						<li>
							<a href="serviceUser.htm?n=1"> Baja de servicio</a>
						</li>
						<li >
							<a href="serviceStatus.htm?n=2">Consulta de estatus</a>
						</li>
						<li >
							<a href="promotion.htm?n=3">Promociones</a>
						</li>
								
					</core:when>
					<core:when test="${menu == 1}">
							<li class = "active" >
								<a href="serviceUser.htm?n=1"> Baja de servicio</a>
							</li>
							<li>
								<a href="serviceStatus.htm?n=2">Consulta de estatus</a>
							</li>
							<li>
								<a href="promotion.htm?n=3">Promociones</a>
							</li>
					</core:when>
					
					<core:when test="${menu == 2}">
					<li>
						<a href="serviceUser.htm?n=1"> Baja de servicio</a>
					</li>
					<li class = "active">
						<a href="serviceStatus.htm?n=2">Consulta de estatus</a>
					</li>
					<li>
						<a href="promotion.htm?n=3">Promociones</a>
					</li>
							
					</core:when>
					
					<core:when test="${menu == 3}">
					
					<li>
						<a href="serviceUser.htm?n=1"> Baja de servicio</a>
					</li>
					<li >
						<a href="serviceStatus.htm?n=2">Consulta de estatus</a>
					</li>
					<li class = "active" >
						<a href="promotion.htm?n=3">Promociones</a>
					</li>
							
					</core:when>
   				</core:choose>
			</ul>
        </div>
   </div>