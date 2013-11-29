<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="titleBar">
    <div class="titleBarContent">
		<h2>Events</h2>
    </div>
</div>

<div class="form">
	<div class="formContent">
		<table>
		   <tr>
		      <td>
		      <c:choose>
      			<c:when test="${events.isEmpty()==true}">
      				<p class="event">No events can be predicted for these parameters.</p>
      				<br />
      			</c:when>

      			<c:otherwise>
      				<table>
      					<tr>
							<td class="event">Begin</td>
	      					<td class="event">End</td>
	      					<td class="event">Box</td>
	      					<td class="event">Target Data Files</td>
	      					<td class="event">Reference Data Files</td>
	      				</tr>
		      			<c:forEach var="event" items="${events}">
		      				<tr>
					      		<td class="event">${event.begin.getTime() }</td>
					      		<td class="event">${event.end.getTime() }</td>
					      		<td class="event">north: ${event.latNorth }, south: ${event.latSouth }, east: ${event.lonEast }, west: ${event.lonWest }</td>
					      		<td class="event">${event.targetData.getDataFiles()}</td>
					      		<td class="event">${event.referenceData.getDataFiles()}</td>
					      	</tr>
					    </c:forEach>
			     	
			     	</table>
      				<br />
      			</c:otherwise>
			</c:choose>
		      </td>
		    </tr>
		</table>
	</div>
</div>

<form method="get" action="dataCollection.html">
	<div class="formOptions">
		<div class="formOptionsContent">
			<input type="hidden" name="id" value="${ICPlanId}"/>
			<button type="button" class="button">Back</button>
			<input class="button" type="submit" value="Next"/>
		</div>
	</div>
</form>