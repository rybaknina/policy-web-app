<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insurance Page</title>
	<link rel="stylesheet" href='<c:url value="/resources/lib/bootstrap-3.3.6/css/bootstrap.min.css"/>'>
	<link rel="stylesheet" href='<c:url value="/resources/lib/jquery/jquery-ui-1.10.4.custom.css"/>'>

	<style type="text/css">
		th {text-align: left}

	</style>

</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${contextPath}/"><img src="${contextPath}/resources/pict/jeep-cabrio.svg" height="36" alt="Insurance"></a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="${contextPath}/">Home</a></li>
            <c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN')}">
                <li><a href="${contextPath}/listPolicys">Admin Insurance</a></li>
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

<h1>
    Choose an Insurance
</h1>

</head>

<c:url var="findAction" value="/policy/find" ></c:url>

<form:form action="${findAction}" commandName="policy" >

<table class="table table-striped ">
    <tr>
        <th width="10%">Price From</th>
        <th width="10%">Price To</th>
        <th width="10%">Type</th>
        <th width="10%">Active</th>

    </tr>
	<tr>
		<td>
			<input type="text" name="priceFrom" />
		</td>
        <td>
            <input type="text" name="priceTo" />
        </td>
		<td>
            <form:select path="typef">
                <form:option value="" label="" />
                <form:options items="${typeList}" itemValue="id" itemLabel="name" />
            </form:select>
         </td>
        <td>
             <input type="checkbox" name="activef" />
		</td>
    <td class="borderless">

        <input type="submit" class="btn btn-info" value="Search">
        <a href="<c:url value='/'/>" class="btn btn-info" role="button">Reset</a>

    </td>
</table>
</form:form>

<h3>Insurance List</h3>
<c:if test="${!empty listReadPolicys}">
	<table class="table table-striped table-bordered">
	<tr>
		<th width="12%">Company</th>
		<th width="12%">Price</th>
		<th width="12%">Type</th>
        <th width="12%">Active</th>
		<th width="12%">Byu</th>
	</tr>
	<c:forEach items="${listReadPolicys}" var="policy">
		<tr>
			<td>${policy.company.name}</td>
			<td>${policy.price}</td>
			<td>${policy.type.name}</td>
            <td>${policy.active == true? 'Yes': 'No'}</td>
			<td><a href="<c:url value='/buy/${policy.id}/${empty pageContext.request.userPrincipal.name? "empty": pageContext.request.userPrincipal.name}' />" class="btn btn-info" role="button">Buy</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<br>

<c:if test="${paginationResult.totalPages > 1}">
   <ul class="pagination">
      <c:forEach items="${paginationResult.navigationPages}" var = "page">
          <c:if test="${page != -1 }">
              <li><a class="page-link" href="${contextPath}?page=${page}">${page}</a></li>
          </c:if>
          <c:if test="${page == -1 }">
              <li><span class="page-link"> ... </span></li>
          </c:if>
      </c:forEach>

   </ul>
</c:if>
</div>

<!--  It is advised to put the <script> tags at the end of the document body so they don't block rendering of the page -->
<script src="${contextPath}/resources/lib/jquery/jquery-1.10.2.js"></script>
<script src="${contextPath}/resources/lib/jquery/jquery-ui-1.10.4.custom.js"></script>
<script src="${contextPath}/resources/lib/bootstrap-3.3.6/js/bootstrap.min.js"></script>
</body>
</html>