$(function() {
	$.getJSON("data/sector?sectorId=0", function(json) {
		// Adding root list for the root sector
		$("<ul/>").attr("id", "sectorId0").appendTo("#sectorContainer");
		$("<li/>").text(json.name).appendTo("#sectorId0");
		$.each(json.subSectors, addSubSector);
	});
});

function addSubSector(i, item) {

	$("<li/>")
	// .attr("id","sectorEl" + item.id)
	.click(function() {
		$("#companyContainer").empty();
		$("#personContainer").empty();
		
		var colNames = "<th>Id</th><th>Name</th>";
		$("<tr/>").append(colNames).appendTo("#companyContainer");

		$.getJSON("data/company?sectorId=" + item.id, getCompanies);
	}).text(item.name).appendTo("#sectorId" + item.parentId);

	var subs = item.subSectors;
	// if (subs instanceof Object) {
	// TODO what is the difference between "!= null" and "instanceof Object" ?
	if (subs != null) {
		$("<ul/>").attr("id", "sectorId" + item.id).appendTo(
				"#sectorId" + item.parentId);
		$.each(subs, addSubSector);
	}
}

function getCompanies(json) {
	$.each(json, function(i, item) {
		var row = "<td>" + item.id + "</td><td>" + item.name + "</td>";
		$("<tr/>").append(row).click(function() {
			$.getJSON("data/person?companyId=" + item.id, getPersons);
		}).appendTo("#companyContainer");
	});
}

function getPersons(json) {
	$("#personContainer").empty();
	var colNames = "<th>Id</th><th>FirstName</th><th>SecondName</th><th>Description</th>";
	$("<tr/>").append(colNames).appendTo("#personContainer");
	$.each(json, function(i, item) {
		var row = "<td>" + item.id + "</td><td>" + item.firstName + "</td>"
				+ "<td>" + item.secondName + "</td><td>" + item.description
				+ "</td>";
		$("<tr>").append(row).appendTo("#personContainer");
	});
}
