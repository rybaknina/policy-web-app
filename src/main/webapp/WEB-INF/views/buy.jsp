<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>Insurance Page</title>
	<link rel="stylesheet"
		  href='<c:url value="/resources/lib/bootstrap-3.3.6/css/bootstrap.min.css"/>'>

	<link rel="stylesheet"
		  href='<c:url value="/resources/lib/jquery/jquery-ui-1.10.4.custom.css"/>'>

	<style type="text/css">
		th {
			text-align: left;
		}
		.button {
			background-color: #008CBA;
			border: none;
			color: white;
			padding: 15px 32px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			margin: 4px 2px;
			cursor: pointer;
		}
	</style>

</head>
<body>

<div style="width: 95%; margin: 0 auto;">
<div style="margin: 1%" class="page-header">
<h1>
	Buy Insurance
</h1>
</div>

<c:url var="buyAction" value="/buysave/${policy.id}/${empty user.username? 'empty': user.username}" />

<form:form id = "buy" commandName="buy" method="post"
		   action="${buyAction}" class="form-horizontal">

	<form:input path="id" type="hidden" />

	<div class="form-group">
		<label for="firstName" class="col-xs-1 control-label">FirstName</label>
		<div class="col-xs-2">
			<div class="form-check">
				<form:input path="firstName" placeholder="FirstName"
							class="form-control is-invalid" required="required"/>
			</div>
		</div>

	</div>

	<div class="form-group">
		<label for="${policy.company.name}" class="col-xs-1 control-label">Company</label>
		<div class="col-xs-2">
			<div class="form-control">
				<c:out value = "${policy.company.name}"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="${policy.type.name}" class="col-xs-1 control-label">Type</label>
		<div class="col-xs-2">
			<div class="form-control">
				<c:out value = "${policy.type.name}"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="${buy.amount}" class="col-xs-1 control-label">Amount</label>
		<div class="col-xs-2 ">
			<div class="form-control">
				<c:out value = "${buy.amount}"/>
			</div>
		</div>

	</div>

	<div class="container">
		<div class="col-xs-2" >
			<div class="form-select-button"  style="display: inline-block; ">
				<input type="submit" class="btn btn-info" value="Buy Me">
				<input type="submit" class="btn btn-info" onclick="window.location.replace('${contextPath}/')" value="Cancel" />
			</div>
		</div>

	</div>
</div>
</form:form>
	<script type="text/javascript"
			src='<c:url value="/resources/lib/jquery/jquery-1.10.2.js"/>'></script>
	<script type="text/javascript"
			src='<c:url value="/resources/lib/jquery/jquery-ui-1.10.4.custom.js"/>'></script>
	<script type="text/javascript"
			src='<c:url value="/resources/lib/bootstrap-3.3.6/js/bootstrap.min.js"/>'></script>
</body>
</html>