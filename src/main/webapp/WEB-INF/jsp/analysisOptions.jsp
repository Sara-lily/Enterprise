<%@ include file="/WEB-INF/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="titleBar">
    <div class="titleBarContent">
		<h2>Analysis Options</h2>
    </div>
</div>
<div id="accordionSize">
	<div id="accordion">
		<h3>Chart a Data Variable</h3>
			<div>
				<form:form method="post" modelAttribute="ICPlan" action="chartADataVariable">
					<table>
						<tr>
							<td>
								<label class="label">Select a data variable</label>
							</td>
							<td>
								<select class="input"  id="selectBox" onclick="addToBox('selectBox');">	
									<c:forEach var="variable" items="${ICPlan.getTargetDataVariableRefs()}">	
										<option name="${variable.ID}" value="${variable.ID}">${variable.ID}</option>
									</c:forEach>
									<c:forEach var="variable" items="${ICPlan.getReferenceDataVariableRefs()}">	
										<option name="${variable.ID}" value="${variable.ID}">${variable.ID}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<div class="rightButtons">
									<input class="right button" type="submit" value="Next"/>
								</div>
							</td> 
						</tr>
					</table>
					
				</form:form>
			</div>
		<h3>Regression</h3>
			<div>
				<form:form method="post" modelAttribute="ICPlan" action="regression">
					<table>
						<tr>
							<td>
								<label class="label">Independent Variable:</label>
							</td>
							<td>
								<select class="input"  id="selectBox" onclick="addToBox('selectBox');">	
									<c:forEach var="variable" items="${ICPlan.getTargetDataVariableRefs()}">	
										<option name="${variable.ID}" value="${variable.ID}">${variable.ID}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								<label class="label">Dependent Variable:</label>
							</td>
							<td>
								<select class="input"  id="selectBox" onclick="addToBox('selectBox');">	
									<c:forEach var="variable" items="${ICPlan.getReferenceDataVariableRefs()}">	
										<option name="${variable.ID}" value="${variable.ID}">${variable.ID}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<label class="specialLabel padding">Select a Range for the Variables</label>
					<table>
						<tr>
							<td>
								<input class="alignBox input sized" type="text"/>
							</td>
							<td>
								<label class="magicLabel sized">&#8804</label>
							</td>
							<td>
							   <label class="magicLabel">Independent Variable</label>
							</td>
							<td>
							   <label class="magicLabel sized">&#8804</label>
							</td>
							<td>
								<input class="alignBox input sized" type="text"/>
							</td>
						</tr>
											<tr>
							<td>
								<input class="alignBox input sized" type="text"/>
							</td>
							<td>
								<label class="magicLabel sized">&#8804</label>
							</td>
							<td>
							   <label class="magicLabel">Dependent Variable</label>
							</td>
							<td>
							   <label class="magicLabel sized">&#8804</label>
							</td>
							<td>
								<input class="alignBox input sized" type="text"/>
							</td>
						</tr>
					</table>
					<div class="rightButtons">
						<input class="right button" type="submit" value="Next"/> 
					</div>
				</form:form>
			</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>