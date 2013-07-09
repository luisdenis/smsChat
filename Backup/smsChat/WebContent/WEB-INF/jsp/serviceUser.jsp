<%@page import="com.blve.smschat.domain.SubscribeaTbl"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/jsp/jspf/taglibs.jsp" %>
<%@page import="java.lang.reflect.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson" %>
<%@page import="com.blve.persistence.ResultList" %>
<%@page import="com.blve.smschat.domain.SubscribeaTbl" %>
<%@page import="com.blve.smschat.domain.SubscribebTbl" %>


<!DOCTYPE html>
<html lang="es">
<%@ include file="/WEB-INF/jsp/jspf/header.jsp" %>
<body >
	
	<%@ include file="/WEB-INF/jsp/jspf/headerApp.jsp"%>
	 	<% 	
			ResultList resultsList = (ResultList)request.getAttribute("results");
			String json = null;
			if(resultsList!= null){
				List<SubscribeaTbl> subscribeaTbls =  resultsList.getList();
				Gson gson = new Gson();
				json = gson.toJson(subscribeaTbls).toString();
			}	     
        %>
        
        <script type="text/javascript">
           // Do something inline with variable from server.
			$(document).ready(function () {
			 var results = <%=json %>
			 if(results != null){
				 page = '${search.page}';
				 var number_page = '${search.numRows}';
			     var opt = {callback: pageUserService, current_page : page-1, items_per_page: number_page};
			     var cantLocal = ${results.totalRows};
			     $("#pagination").pagination(cantLocal, opt);
			     actualizaReloj();
				 listSubscriptor = <%=json %>
			 }
			});
       </script>
        
        
	<div id="idHomediv">
		<div class="row-fluid">
			<%@ include file="/WEB-INF/jsp/jspf/menuVertical.jsp"%>
			<div class="span10">
				<div class="well-white">
					<div class="row-fluid "
						style="padding-top: 0px; padding-bottom: 0px;">
						<div class="span12 well">&nbsp;<img src="images/logo_login.png" />&nbsp;
						</div>
					</div>
			
					<div class="row-fluid " style="padding-top: 0px;">
						<div class="span12" style="padding-right: 10px; padding-left: 10px;">
							<!-- AREA DE TRABAJO -->
							
							<form onsubmit="" method="post">
								<div class="row-fluid" style="padding-top: 0px;">
								
									<div class="span3">
										<spring:bind path="search.msisdn">
										Número de Teléfeno: <input id="msisdn" name="msisdn" class="input-medium" type="text" value="${search.msisdn}"  />
										</spring:bind>
									</div>
									<div class="span2">
										<spring:bind path="search.estado">
											Estado:<input id="estado" name="estado" class="input-medium" type="text" value="${search.estado}"  />
										</spring:bind>
									</div>
									<div class="span4"></div>
									<div class="span3">
										<input id="buscar" class="btn btn-small  btn-danger pull-right"  type="submit" name="SubmitButton" value="<fmt:message key="BUTTON.SEARCH"/>" />
									</div>
								</div>
								<hr/>
								<div  align="center" id = "idloadingdiv"></div>
							 <core:choose>
									<core:when test="${results.totalRows==-1 || results.totalRows==null || results.totalRows==0 }">
										<div align="center">
											<br />
											<img src="images/info.gif" alt="Informaci&oacute;n" align="center">
											<fmt:message key="notResults" />
											<br />
											<br />
											<br />
										</div>
									</core:when>
									<core:otherwise>
										<div id = "idtable">								
										<table class="table table-hover table-condensed table-bordered" border="0" cellpadding="1" cellspacing="2" >
											<thead>
												<tr>											
													<th>#</th>
													<th><fmt:message key="service.numero.a" /></th>
													<th><fmt:message key="service.alias" /></th>
													<th><fmt:message key="service.numero.b" /></th>
													<th><fmt:message key="service.estado" /></th>
													<th><fmt:message key="service.ult.fech.actividad" /></th>
													<th><fmt:message key="service.ult.fech.cobro" /></th>
													<th><fmt:message key="service.operaciones" /></th>
												</tr>												
											</thead>
											<tbody>
											<core:forEach items='${results.list}' var="subscribe" varStatus="vs">
											   
													<core:if test='${vs.count mod 2 eq 0}'>
														<core:set var="color" value="row1" />
													</core:if>
													<core:if test='${vs.count mod 2 != 0}'>
														<core:set var="color" value="row2" />
													</core:if>
													<tr id = "${subscribe.subscribeaid}" class="${color}">
														<td  >${subscribe.subscribeaid}</td>
														<td  >${subscribe.msisdn}</td>
														<td  >${subscribe.alias}</td>
														<td  >
															<ul>
															<core:forEach items='${subscribe.subscribebTbls}' var="subscribeb" varStatus="vsb">
																<core:if test='${vsb.count < 6 }'>
																	<core:if test='${subscribeb.msisdn == search.msisdn }'>
																		<core:set var="colort" value="color: #66CD00;" />
																	</core:if>
																	<core:if test='${subscribeb.msisdn != search.msisdn }'>
																		<core:set var="colort" value="" />
																	</core:if>
																	 <li style="${colort}"> ${subscribeb.msisdn} - ${subscribeb.alias}</li>
																</core:if>
															</core:forEach>
															<core:if test='${subscribe.subscribebTbls.size() > 5 }'>
																		<div align = "right"><a  href = "javascript:consultarServicioUser(${subscribe.subscribeaid});" >ver más...</a></div>
																 </core:if>
															</ul>
															
														</td>
														<td  id ="${subscribe.subscribeaid}-estadolist" >${subscribe.estado}</td>
														<td >${subscribe.fechaultimaactividad}</td>
														<td  >${subscribe.fechacobro}</td>	
														<td id ="${subscribe.subscribeaid}-operacionlist" >
														
															<img  src="images/server_detail.png" onclick = "consultarServicioUser(${subscribe.subscribeaid});" />
															<core:if test='${subscribe.estado != "INACTIVO" }'>
																<img  src="images/server_shutdown.png"  onclick = "eliminarMaquina(${subscribe.subscribeaid},${subscribe.msisdn});"   />
															</core:if>
														</td>												
													</tr>
												
												
											</core:forEach>
											</tbody>
										</table>
										</div>
										<div class="row-fluid" style="padding-top: 0px;">
						                    <div class="span5"></div>
						                    <div class="span6"></div>
						                    <div class="span1">
						                    <spring:bind path="search.numRows">
	                                            <select id = "numRows" name="numRows">
	                                              <core:forEach begin='10' end='50' step='10' var="count">
	                                                <core:choose>
	                                                  <core:when test="${count==search.numRows}">
	                                                    <option value="${count}" selected="selected">${count}</option>
	                                                  </core:when>
	                                                  <core:otherwise>
	                                                    <option value="${count}">${count}</option>
	                                                  </core:otherwise>
	                                                </core:choose>
	                                              </core:forEach>
	                                            </select>
	                                          </spring:bind>
						                    </div>
						                  </div>
										<div  class = "pagination pagination-centered"><ul  id = "pagination" ></ul></div>
									</core:otherwise>
								</core:choose>
								<br />
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	

					
		 <!-- Modal -->
		      <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		      <div class="modal-header">
		      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		      <h3 id="myModalLabel"><img src="images/logo_login.png">&nbsp; Consultar suscripción</h3>
		      </div>
		      <form id = "formularioagregarmaquina" method="post">
		      <div class="modal-body">
		      
		      <div class="row-fluid" style="padding-top: 0px;">
		          <div class="row-fluid" style="padding-top: 0px;">
				  <div class="span6" align="left"   >
		               <div id = "iddivnodo" >
		                   <label><b>Número A</b></label>
		                   <input id="idNumeroA" class="input-xlarge" type="text" disabled="disabled" />
		                   <span class="help-block">Número subscritor</span>
		               </div>
		           </div>
				<div class="span6">
					<label><b>Estado</b></label> 
					<input id="estadoConsulta" class = "input-xlarge" contenteditable="false" name="estado" type="text"  placeholder="Estado del servicio" disabled="disabled" />
					 <span class="help-block">Estado del servicio. Ejemplo: Activo e Inactivo.</span>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span6">
					<label><b>Fecha Última Actividad</b></label> 
					<input class="input-xlarge" name="fecUltActC" id="fechaActividad" contenteditable="false" type="text" placeholder="Fecha de la Última Actividad." disabled="disabled" />
					<span class="help-block">Ultima fecha en que tuvo algun tipo de actividad</span>
				</div>
				<div class="span6">
					<div class="span6">
					<label><b>Fecha de Cobro</b></label>
					<input class="input-xlarge" name="fecUltCobC" id="fechaCobro" contenteditable="false" type="text" placeholder="Fecha del Último Cobro." disabled="disabled" />
					<span class="help-block">Ultima fecha de cobro</span>
				</div>
				</div>
			</div>
			<div class="row-fluid" style="padding-top: 0px;">
               <div class="span12" align="left" >
                    <div id = "iddivnumberB">
                         <label><b>Lista de usuario Suscrito</b><span style="color:red;">*</span> </label>
                    </div>
                    <div id = "idmensajeresultconsulta" align="center"></div>
                    <div id = "idListaNumberB"></div>
                </div>
            </div>
			
			
		      </div>
		      
		      </div>
		      <div class="modal-footer">
		      <div id= "idenviando">
		          <button id = "Limpiar" class="btn btn-danger" data-dismiss="modal" aria-hidden="true" type="button" >cerrar</button>
		          <button id = "guardar" class="btn btn-primary" type="submit"  >Suspender</button>
		      </div>
		      </div>
		      </form>
		      </div>
				
	
</body>
</html>