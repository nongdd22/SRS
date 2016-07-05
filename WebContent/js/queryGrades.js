/**
 * 
 */
$(document).ready(function(){
	$.getJSON("../getStudentTranscript",function(jsonData) {
		var html="";
			for(var i=0;i<jsonData.length;i++){
				html+="<tr><td>"+(i+1)+"</td><td>"+jsonData[i].courseName+"</td><td>"+jsonData[i].professor+"</td>" +
					  "<td>"+jsonData[i].grade+"</td></tr>";
			}
		$("#transcript").append(html);
	});
});