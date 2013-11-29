<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ include file="/WEB-INF/jsp/signInBar.jsp" %>

<div class="titleBar">
    <div class="titleBarContent">
		<h2>Welcome!</h2>
    </div>
</div>

<div id="links" class="form">
	<div id="links-content" class="formContent">
		<ul>
		  <li><a href="<spring:url value="/eventStudyParameters" htmlEscape="true" />">Create a new study</a></li>
		  <li><a href="<spring:url value="/allPlans" htmlEscape="true" />">Edit a plan</a></li>
		</ul>
	</div>
</div>

<p>&nbsp;</p>
<p>&nbsp;</p>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
