<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="titleBar">
    <div class="titleBarContent">
		<h2>Analysis Options</h2>
    </div>
</div>

<div id="accordion">
	<h3>Chart a Data Variable</h3>
		<div>
			<form:form method="post" modelAttribute="ICPlan" action="chartADataVariable">
	
			</form:form>
		</div>
	<h3>Regression</h3>
		<div>
			<form:form method="post" modelAttribute="ICPlan" action="regression">
			
			</form:form>
		</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>