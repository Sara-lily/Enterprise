<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="titleBar">
    <div class="titleBarContent">
		<h2>Event Prediction Parameters</h2>
    </div>
</div>

<form method="post" action="eventList.html">
	<div class="form" id="prediction">
		<div class="formContent" id="predictionContent">
			<c:forEach var="property" items="${properties.keys()}">
			   	<label class="label" for="property">${property}:</label>
			   	<input class="alignBox input" type="text" name="${property}" value="${properties[property]}"/>
			</c:forEach>
		</div>
	</div>
	<div class="formOptions">
		<div class="formOptionsContent">
			<input type="hidden" name="id" value="${ICPlan.getId()}"/>
			<button type="button" class="button">Back</button>
			<input class="button" type="submit" value="Next"/>
		</div>
	</div>
</form>

<a href="<spring:url value="/" htmlEscape="true" />">Go to welcome page without saving.</a>

<script>
function addParameter(count) {
	count++;
	$("<h3 id=" + count + ">Section " + count + "</h3>").appendTo("#accordion");
	$("<div><label for='parameter" + count + "'>Parameter Options:</label><select size='4' id='parameter" + count + "' name='parameter" + count + "' value='${ICPlan}'><c:forEach var='parameter' items='${parameters}'><option value='${parameter}'>${parameter}</option></c:forEach></select></div>").appendTo("#accordion");

	$( "#accordion" ).accordion( "refresh" );
	return count;
}
</script>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>