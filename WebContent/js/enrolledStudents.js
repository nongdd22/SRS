/**
 * 
 */

var url = location.href;
var es=/sectionNo=/;
es.exec(url);
var sectionNo=RegExp.rightContext;
$(document).ready(function(){
	$.getJSON("../getenrolledStudents",{sectionNo:sectionNo}, function(jsonData) {
		var html="";
			for(var i=0;i<jsonData.length;i++){
				html+="<tr><td>"+(i+1)+"</td><td>"+jsonData[i].SSsn+"</td><td>"+jsonData[i].name+"</td>" +
					  "<td>"+jsonData[i].major+"</td><td>"+jsonData[i].degree+"</td></tr>";
			}
		$("#showstudents").append(html);
	});
});



