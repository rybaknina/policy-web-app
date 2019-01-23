<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<title>Admin Insurance List</title>

<link rel="stylesheet"
	href='<c:url value="/resources/lib/bootstrap-3.3.6/css/bootstrap.min.css"/>'>
	
<link rel="stylesheet"
	href='<c:url value="/resources/lib/jquery/jquery-ui-1.10.4.custom.css"/>'>

<style type="text/css">
th {
	text-align: left
}
</style>


</head>

<body>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${contextPath}/"><img src="${contextPath}/resources/pict/car-tire-change.svg" height="36" alt="Insurance"></a>
		</div>
		<ul class="nav navbar-nav">
			<li ><a href="${contextPath}/">Home</a></li>
			<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
				<li class="active"><a href="${contextPath}/listPolicys">Admin Insurance</a></li>
			</c:if>
			<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
				<li><a href="${contextPath}/listBuy">Admin Buy</a></li>
			</c:if>
		</ul>
		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${contextPath}/registration"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
				<li><a href="${contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</c:if>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
			<p class="nav navbar-nav navbar-right">
				Hello, ${pageContext.request.userPrincipal.name} | <a href="${contextPath}/" class="navbar-link" onclick="document.forms['logoutForm'].submit()"><span class="glyphicon glyphicon-log-out"></span>Logout  </a>
			</p>
		</c:if>
	</div>
</nav>
	<div style="width: 95%; margin: 0 auto;">
		<div id="policyDialog" style="display: none;">
			<%@ include file="policyForm.jsp"%>
		</div>

		<h1>Insurance List</h1>

		<button class="btn btn-primary" onclick="addPolicy()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add Policy
		</button>
		<br>
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th width="12%">Company</th>
					<th width="12%">Price</th>
					<th width="12%">Type</th>
					<th width="12%">Active</th>
					<th width="12%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${policyList}" var="policy">
					<tr >
						<td><c:out value="${policy.company.name}" /></td>
						<td><c:out value="${policy.price}" /></td>
						<td><c:out value="${policy.type.name}" /></td>
						<td><c:out value="${policy.active == true? 'Yes': 'No'}"/></td>
						<td><div class="btn-group">
								<button class="btn btn-primary"
									onclick='editPolicy("${policy.id}")'>

									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit
								</button>

								<a class="btn btn-primary"
									onclick="return confirm('Are you sure you want to delete this policy?');"
									href="delete/${policy.id}"> 
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
								</a>

                        </div></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${paginationResult.totalPages > 1}">
			<ul class="pagination">
				<c:forEach items="${paginationResult.navigationPages}" var = "page">
					<c:if test="${page != -1 }">
						<li><a class="page-link" href="listPolicys?page=${page}">${page}</a></li>
					</c:if>
					<c:if test="${page == -1 }">
						<li><span class="page-link"> ... </span></li>
					</c:if>
				</c:forEach>

			</ul>
		</c:if>
	</div>

	<!--  It is advised to put the <script> tags at the end of the document body so they don't block rendering of the page -->
	<script type="text/javascript"
		src='<c:url value="/resources/lib/jquery/jquery-1.10.2.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/resources/lib/jquery/jquery-ui-1.10.4.custom.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/resources/lib/bootstrap-3.3.6/js/bootstrap.min.js"/>'></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/js-for-listPolicys.js"/>'></script>
</body>
</html>