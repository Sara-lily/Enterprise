<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<h2>Plans:</h2>

<table>
  <thead>
    <th>Name</th>
    <th>Reference Instrument</th>
    <th>Target Instrument</th>
    <th>State</th>
  </thead>
  <c:forEach var="plan" items="${ICPlans}">
    <tr>
      <td><a href="<spring:url value="/plan?planID=${plan.id}" htmlEscape="true" />">${plan.name}</td>
      <td>${plan.ref.name}</td>
      <td>${plan.tgt.name}</td>
      <td>${plan.state}</td>
    </tr>
  </c:forEach>
</table>


<ul>
  <li><a href="<spring:url value="/plan" htmlEscape="true" />">Create new Plan</a></li>  
</ul>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
