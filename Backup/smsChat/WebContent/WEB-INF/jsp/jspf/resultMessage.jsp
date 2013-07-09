  
 <table width="80%" cellpadding="0" cellspacing="0" border="0" align="center" >
	<tr class="font12"> 
    	<td align="center">
	      <core:if test="${msg!=null}">
				 <core:choose>
					<core:when test="${msg.resultType==0}">
						<img src="images/check.gif"/>
					</core:when>
					<core:when test="${msg.resultType==1}">
						<img src="images/error.gif"/>
					</core:when>
					<core:when test="${msg.resultType==2}">
						<img src="images/warning1.gif"/>
					</core:when>
				 </core:choose>
		 		<strong>  <fmt:message key="${msg.resultCode}"/></strong>
				<core:set var="msg" value="${null}" scope="session" />
	    	</core:if>   
        
         </td>
     </tr>
 </table>  