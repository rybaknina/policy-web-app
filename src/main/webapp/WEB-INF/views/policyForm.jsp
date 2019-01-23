<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:url var="addAction" value="save" />

<form:form id = "policyForm" commandName="policy" method="post"
		   action="${addAction}" class="form-horizontal">

	<form:input path="id" type="hidden" />

	<div class="form-group">
		<label for="price" class="col-xs-4 control-label">Price</label>
		<div class="col-xs-8">
			<form:input path="price" placeholder="Price" maxlength="10"
				class="form-control is-invalid" required="required"/>
			<div class="invalid-tooltip">
				Please enter a price.
			</div>
		</div>

	</div>
	<div class="form-group">
		<label for="company.id" class="col-xs-4 control-label">Company</label>
		<div class="col-xs-8">
			<form:select path="company.id" placeholder="Company" class="form-control is-invalid">
				<form:option value="" label="--Please Select--"/>
				<form:options items="${companyList}" itemValue="id" itemLabel="name" />
			</form:select>
			<div class="invalid-tooltip">
				Please choose a company.
			</div>
		</div>
	</div>
	<div class="form-group">
		<label for="type.id" class="col-xs-4 control-label">Type</label>
		<div class="col-xs-8">
			<form:select path="type.id" placeholder="Type" class="form-control is-invalid">
                	    <form:option value="" label="--Please Select--"/>
                	    <form:options items="${typeList}" itemValue="id" itemLabel="name" />
			</form:select>
			<div class="invalid-tooltip">
				Please choose a type.
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="active" class="col-xs-4 control-label">Active</label>
		<div class="col-xs-8">
			<div class="form-check">
				<form:checkbox path="active" placeholder="Active"
							class="form-check-input is-invalid" required="required"/>
			</div>
		</div>

	</div>



</form:form>