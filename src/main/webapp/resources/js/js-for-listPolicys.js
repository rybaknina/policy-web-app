function addPolicy() {
	$('#policyDialog').dialog("option", "title", 'Add Policy');
	$('#policyDialog').dialog('open');
}

function editPolicy(id) {

	$.get("get/" + id, function(result) {

		$("#policyDialog").html(result);

		$('#policyDialog').dialog("option", "title", 'Edit Policy');

		$("#policyDialog").dialog('open');

	});
}


function resetDialog(form) {

	form.find("input").val("");
}

$(document).ready(function() {

	$('#policyDialog').dialog({

		autoOpen : false,
		position : 'center',
		modal : true,
		resizable : false,
		width : 440,
		buttons : {
			"Save" : function() {
				$('#policyForm').submit();
			},
			"Cancel" : function() {
				$(this).dialog('close');
			}
		},
		close : function() {

			resetDialog($('#policyForm'));

			$(this).dialog('close');
		}
	});

});
