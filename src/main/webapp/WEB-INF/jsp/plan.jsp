<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<body>
<h2>Intercalibration Plan</h2>
<form method="post" action="addPlan.html">
<!--  ID is not editable -->
<input type="hidden" name="planID" value="${ICPlan.id}"/>
<table>
	<tr>
		<td colspan="2">
			<label for="name">Plan Name:</label>
			<input name="planName" value="${ICPlan.name}" maxlength="100"/>
		</td>
    <tr>
    	<td>
        	<label for="refInstrument">Reference Instrument:</label>
        	<select name="refInstrument" value="${ICPlan.ref.name}">
        		<option value="NONE">--- Select ---</option>
        		<c:forEach var="instrument" items="${instruments.instrumentList}">
					    		    
            		<option 
            			<c:if test="${instrument.name eq ICPlan.ref.name}">selected</c:if>  
            			value="${instrument.name}">${instrument.name}
            		</option>
        		</c:forEach>
    		</select>    	
        </td>
       	<td>
        	<label for="tgtInstrument">Target Instrument:</label>
        	<select name="tgtInstrument" value="${ICPlan.tgt.name}">
        		<option value="NONE">--- Select ---</option>
        		<c:forEach var="instrument" items="${instruments.instrumentList}">
            		<option 
            			<c:if test="${instrument.name eq ICPlan.tgt.name}">selected</c:if>  
            			value="${instrument.name}">${instrument.name}
            		</option>
        		</c:forEach>
    		</select>    	
        </td>
    </tr>
 	<tr>
        <td colspan="2">
            <input type="submit" value="Save Plan"/>
        </td>
    </tr>
</table> 
     
</form>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
