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
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/lib/datatables/jquery.dataTables.min.css">
    <script type="text/javascript" src="${contextPath}/resources/lib/datatables/jquery.dataTables.min.js"></script>
    <script src="${contextPath}/resources/js/boostrapTableGrid.js"></script>
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
            <li ><a href="${contextPath}/">Home</a></li>
            <li class="active"><a href="${contextPath}/policySort">Sort Expierence</a></li>
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

    </head>

    <h3>Insurance List</h3>
    <div>
        <table id="policyTable" class="table table-bordered table-striped">
            <thead>
            <tr>
                <th class="col-sm-1">Company</th>
                <th class="col-sm-1">Price</th>
                <th class="col-sm-3">Type</th>
                <th class="col-sm-3">Active</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${listReadPolicys}" var="policy">
                    <tr >
                        <td><c:out value="${policy.company.name}" /></td>
                        <td><c:out value="${policy.price}" /></td>
                        <td><c:out value="${policy.type.name}" /></td>
                        <td><c:out value="${policy.active == true? 'Yes': 'No'}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

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