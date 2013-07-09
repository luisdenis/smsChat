
<%@ attribute name="page" required="true" %>
<%@ attribute name="total" required="true" %>
<%@ attribute name="numPage" required="true" %>
<%@ attribute name="numRows" required="true" %>
<%@ attribute name="totalPages" required="true" %>
<%@ attribute name="menu" required="false"%>

<table width="95%" border="0" cellpadding="0" cellspacing="0" align="center">
    <tr bgcolor="#FFFFFF">
        <td colspan="4" align="right">&nbsp;</td>
    </tr>
<!-- Se determina la cantidad de paginas -->

	<tr bgcolor="#FFFFFF">
	    <td width="25%" align="right">
		<core:if test='${(numPage)>1}'>
	    	<a href="javascript:paging('${page}?page=1&menu=${menu}')"><img src="images/first.gif" alt="<fmt:message key='icons.tooltip.first'/>"  border="0" /><a>
	    </core:if>
	    &nbsp;
<!-- Se muestra la imagen de anterior pagina -->
	    <core:if test='${(numPage-1)!=0}'>
	    	<a href="javascript:paging('${page}?page=${numPage-1}&menu=${menu}')"><img src="images/prev.gif" alt="<fmt:message key='icons.tooltip.previous'/>"  border="0" /><a>
	    </core:if>
	    </td>
	    <td width="25%" class="font12" align="center"><strong>
		
		<fmt:message key='PAGE_X_OF_Y'>
			<fmt:param value="${numPage}" />
			<fmt:param value="${totalPages}" />
		</fmt:message>
	   
	    </td>
	    <td width="25%" align="left">
	    
<!-- Se muestra la imagen de siguiente pagina -->
 		<core:if test='${totalPages!= numPage}'>
	    	<a href="javascript:paging('${page}?page=${numPage+1}&menu=${menu}')"><img src="images/next.gif" alt="<fmt:message key='icons.tooltip.next'/>"  border="0" /></a>
	    </core:if>
	    &nbsp;
	    <core:if test='${(numPage)!=totalPages}'>
	    	<a href="javascript:paging('${page}?page=${totalPages}&menu=${menu}')"><img src="images/last.gif" alt="<fmt:message key='icons.tooltip.last'/>"  border="0" /><a>
	    </core:if>
	    </td>
	    
<!-- Se muestra el total de registros -->	    
	    <td width="25%" class="font12" align="right"><strong><fmt:message key="TOTAL_RESULTS" />: ${total} </strong></td>
	</tr> 
</table>