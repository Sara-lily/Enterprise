<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="titleBar">
    <div class="titleBarContent">
		<h2>Event/Study Parameters</h2>
    </div>
</div>


<form:form method="post" modelAttribute="ICPlan" action="createPlan.html">
	<div class="form" id="study">
		<div class="formContent">
		<!--  ID is not editable -->
		<form:hidden path="id"/>
		<form:errors path="*" cssClass="error"/>
			<table>
				<tr>
					<td>
						<form:label class="label" path="name">Study Name:</form:label>
						<form:input class="alignBox input" type="text" path="name"/>
					</td>
					<td>
		        		<form:errors path="name" cssClass="error"/>
		        	</td>
			</table>
			<table>
			    <tr>
			      <td>
			      	<form:label class="label" path="tgt">Target Instrument:</form:label>
			      	<form:select class="alignBox input" path="tgt">
			      		<form:option value="NONE" label="Select"/>
			      		<form:options items="${instruments.instrumentList}" itemValue="name" itemLabel="name"/>
			      	</form:select>
			      </td>
			      <td>
			      	<form:errors path="ref" cssClass="error"/>
			      </td>
			    </tr>
			</table>
			
			<table>
			    <tr>
			      <td>
			      	<form:label class="label" path="ref">Reference Instrument:</form:label>
			      	<form:select class="alignBox input" path="ref">
			      		<form:option value="NONE" label="Select"/>
			      		<form:options items="${instruments.instrumentList}" itemValue="name" itemLabel="name" />
			      	</form:select>
			      </td>
			      <td>
			      	<form:errors path="ref" cssClass="error"/>
			      </td>
			    </tr>
			</table>
			
			<table>
				<tr>
					<td>
						<form:label class="label" path="eventPredictionRanges[0].beginDate.time">Start Date:</form:label>
						<form:input class="alignBox input" type="text" path="eventPredictionRanges[0].beginDate.time" id="datepicker1"/>
					</td>
					<td>
		        		<form:errors path="eventPredictionRanges[0].beginDate.time" cssClass="error"/></td>
		        	<td>
				</tr>	
			</table>
			
			<table>
				<tr>
					<td>
						<form:label class="label" path="eventPredictionRanges[0].endDate.time">End Date:</form:label>
						<form:input class="alignBox input" type="text" path="eventPredictionRanges[0].endDate.time" id="datepicker2"/>
					</td>
					<td>
		        		<form:errors path="eventPredictionRanges[0].endDate.time" cssClass="error"/></td>
		        	<td>
				</tr>	
			</table>
		</div>
	</div>
	
	<div class="formOptions">
		<div class="formOptionsContent">
			<button class="button" type="button">Back</button>
			<input class="button" type="submit" value="Next"/> 
		</div>
	</div>
</form:form>


<a href="<spring:url value="/" htmlEscape="true" />">Go to welcome page without saving.</a>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>