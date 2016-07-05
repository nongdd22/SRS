/**
 * 
 */

$(document).ready(function(){
	$.getJSON("../getProfessors", function(jsonData) {
		var html="";
			for(var i=0;i<jsonData.length;i++){
				html+="<tr><td>"+(i+1)+"</td><td>"+jsonData[i].PSsn+"</td><td>"+jsonData[i].name+"</td><td>"+jsonData[i].title+"</td><td>"+jsonData[i].department+"</td>" +
					  "<td><a onclick=\"showProfessor('"+jsonData[i].name+"');\" href=\"#modal-container-updateprofessor\" data-toggle='modal' id=\"modal-updateprofessor\" " +
				      "class=\"glyphicon glyphicon-pencil\" ></a><a class=\"glyphicon glyphicon-trash\" onclick=\"deleteProfessor('"+jsonData[i].PSsn+"');\"></a>" +
					  "<a type=\"button\" href=\"showSectionsByProfessor.html?PSsn="+jsonData[i].PSsn+"\"  class=\"glyphicon glyphicon-list-alt\"></a></td></tr>";
			}
		$("#showprofessors").append(html);
	});
	
});
function showProfessor(name){
	$.getJSON("../singleProfessor",{name:name}, function(jsonData) {
				$("#pSsn").empty();
				$("#pSsn").append(jsonData.PSsn);
				$("#pssn").val(jsonData.PSsn);
				$("#pwd").val(jsonData.password);
				$("#pname").val(jsonData.name);
				$("#ptitle").val(jsonData.title);
				$("#dept").val(jsonData.department);
	});
}

function addProfessor(){
	$("#addprofessor").submit(); 
}

function deleteProfessor(PSsn){
	if(confirm("操作不可恢复，确认要删除吗？")==true){
		$("#Faculty").attr("action","../deleteProfessor?PSsn="+PSsn);
		$("#Faculty").submit(); 
	}
}

function Search(){
	$("#showprofessors").empty();
	var pName = $("#search").val();
	$.getJSON("../singleProfessor",{name:pName}, function(jsonData) {		
		var html="<tr><th>#</th><th>PSsn</th><th>教师姓名</th><th>教师职称</th><th>所属学院</th><th>操作</th></tr>";
		html+="<tr><td>1</td><td>"+jsonData.PSsn+"</td><td>"+jsonData.name+"</td><td>"+jsonData.title+"</td><td>"+jsonData.department+"</td>" +
			  "<td><a onclick=\"showProfessor('"+jsonData.name+"');\" href=\"#modal-container-updateprofessor\" data-toggle='modal' id=\"modal-updateprofessor\" " +
			  "class=\"glyphicon glyphicon-pencil\" ></a><a class=\"glyphicon glyphicon-trash\" onclick=\"deleteProfessor('"+jsonData.PSsn+"');\"></a>" +
			  "<a type=\"button\" href=\"showSectionsByProfessor.html?PSsn="+jsonData.PSsn+"\"  class=\"glyphicon glyphicon-list-alt\"></a></td></tr>";
		$("#showprofessors").append(html);
	});
	
}

function updateProfessor(){
	var PSsn=$("#pssn").val();
	$("#updateprofessor").attr("action","../updateProfessor?PSsn="+PSsn);
	$("#updateprofessor").submit(); 
}
