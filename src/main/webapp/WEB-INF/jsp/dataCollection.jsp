<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<script type="text/javascript" src="javascript/dataCollection.js"></script>


<div class="titleBar">
    <div class="titleBarContent">
		<h2>Data Collection</h2>
    </div>
</div>

<body>
	<h2 align="center">Select Data Variable for ${ICPlan.name}</h2>
	<form:form method="post" modelAttribute="ICPlan" action="addPlanVars">
		<div class="form">
			<div class="formContent">
				<form:errors path="*" cssClass="error" />
				<p>${error}</p>
				
				<table>
					<tr>
						<td class="fixed">
							<label class="specialLabel">Target Instrument:</label>
							<select class="specialInput" size="6" id="selectBox" onclick="addToBox('selectBox');">	
								<c:forEach var="variable" items="${ICPlan.tgt.dataVariableRefs}">	
									<option name="${variable.ID}" value="${variable.ID}">${variable.name}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<label class="specialLabel">Selected Target Variables:</label>
							<div id="${ICPlan.tgt.name}Error" class="error" style="display:none"><p>This value has already been added to the list</p></div>
							<div class="overflowBox" id="${ICPlan.tgt.name}"></div>
						</td>
						
					</tr>
					<tr>
						<td >
							<label class="specialLabel">Reference Instrument:</label>
							<select class="specialInput" size="6" id="selectBox2" onclick="addToBox('selectBox2');">	
								<c:forEach var="variable" items="${ICPlan.ref.dataVariableRefs}">	
									<option name="${variable.ID}" value="${variable.ID}">${variable.name}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<label class="specialLabel">Selected Reference Variables:</label>
							<div id="${ICPlan.ref.name}Error" class="error" style="display:none"><p>This value has already been added to the list</p></div>
							<div class="overflowBox" id="${ICPlan.ref.name}"></div>
						</td>
					</tr>
		
					<tr>
						<td><form:errors path="ref" cssClass="error" /></td>
						<td><form:errors path="tgt" cssClass="error" /></td>
					</tr>
				</table>
			</div>
		</div>
		
		<input type="hidden" name="id" value="${ICPlan.id}"/>
				
		<div class="formOptions">
			<div class="formOptionsContent">
				<button type="button" class="button">Back</button>
				<input class="button" type="submit" value="Next"/> 
			</div>
		</div>
	</form:form>

</body>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>

