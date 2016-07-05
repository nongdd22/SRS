/**
 * 
 */

$(document).ready(function(){
	$.getJSON("../getenrolledSections", function(jsonData) {
		var html="";
			for(var i=0;i<jsonData.length;i++){
				html+="<tr><td>"+(i+1)+"</td><td>"+jsonData[i].sectionNo+"</td><td>"+jsonData[i].courseName+"</td>" +
					  "<td>"+jsonData[i].professor+"</td><td>"+jsonData[i].dayOfWeek+"&emsp;"+jsonData[i].timeOfDay+"</td>" +
					  "<td>"+jsonData[i].room+"</td><td><button type='button' class=\"btn btn-info\" onclick=\"dropclass("+jsonData[i].sectionNo+");\">退选</button></td></tr>";
			}
		$("#showclasses").append(html);
	});
});

function dropclass(sectionNo){
	
	$("#drop").attr("action","../dropClass?sectionNo="+sectionNo);
	$("#drop").submit();

}
