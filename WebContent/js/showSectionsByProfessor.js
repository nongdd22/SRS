/**
 * 
 */

var url = location.href;
var es=/PSsn=/;
es.exec(url);
var PSsn=RegExp.rightContext;
$(document).ready(function(){
	$.getJSON("../singleProfessor",{PSsn:PSsn}, function(json) {
		$("#professorname").append(json.name+"排课情况");
	});
	$.getJSON("../getSectionsbyProfessor",{PSsn:PSsn}, function(jsonData) {
		var html="";
			for(var i=0;i<jsonData.length;i++){
				html+="<tr><td>"+(i+1)+"</td><td><a href=\"enrolledStudents.html?sectionNo="+jsonData[i].sectionNo+"\">"+jsonData[i].sectionNo+"</td>" +
					  "<td><a href=\"enrolledStudents.html?sectionNo="+jsonData[i].sectionNo+"\">"+jsonData[i].courseName+"</td>" +
					  "<td>"+jsonData[i].dayOfWeek+"&emsp;"+jsonData[i].timeOfDay+"</td><td>"+jsonData[i].room+"</td><td>"+jsonData[i].seatingCapacity+"</td></tr>";
			}
		$("#showcourses").append(html);
	});
});





