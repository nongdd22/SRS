/**
 * 
 */

$(document).ready(function(){
	$.getJSON("../getCourseCatalog", function(jsonData) {
		var html="";var option="<option value='0'>无</option>";
			for(var i=0;i<jsonData.length;i++){
				option+="<option>"+jsonData[i].courseName+"</option>";
				html+="<tr><td>"+(i+1)+"</td><td>"+jsonData[i].courseNo+"</td><td>"+jsonData[i].courseName+"</td><td>"+jsonData[i].credits+"</td>" +
					  "<td><a onclick=\"showCourse('"+jsonData[i].courseName+"');\" href=\"#modal-container-updatecourse\" data-toggle='modal' id=\"modal-updatecourse\" " +
				      "class=\"glyphicon glyphicon-pencil\" ></a><a class=\"glyphicon glyphicon-trash\" onclick=\"deleteCourse('"+jsonData[i].courseNo+"');\"></a>" +
					  "<a type=\"button\" href=\"showSectionsByCourse.html?courseNo="+jsonData[i].courseNo+"\"  class=\"glyphicon glyphicon-list-alt\"></a></td></tr>";
			}
		$("#prerequisites").append(option);
		$("#showcourses").append(html);
	});
	
});
function deleteCourse(courseNo){
	if(confirm("操作不可恢复，确认要删除吗？")==true){
		$("#CourseCatalog").attr("action","../deleteCourse?courseNo="+courseNo);
		/*$("#CourseCatalog").ajaxSubmit(); */
		$("#CourseCatalog").submit(); 
	}
}
function addCourse(){
	$("#addcourse").submit(); 
}

function Search(){
	$("#showcourses").empty();
	var courseName = $("#search").val();
	$.getJSON("../singleCourse",{courseName:courseName}, function(jsonData) {		
		var html="<tr><th>#</th><th>课程序号</th><th>课程名称</th><th>学分</th><th>操作</th></tr>";
		html+="<tr><td>1</td><td>"+jsonData.courseNo+"</td><td>"+jsonData.courseName+"</td><td>"+jsonData.credits+"</td>" +
			  "<td><button class=\"glyphicon glyphicon-trash\" onclick=\"deleteCourse('"+jsonData.courseNo+"');\"></button>" +
			  "<button href=\"#modal-container-creatclass\" data-toggle='modal' id=\"modal-creatclass\" class=\"glyphicon glyphicon-list-alt\" onclick=\"showSectionsByCourse('"+jsonData.courseNo+"');\"></button></td></tr>";
		$("#showcourses").append(html);
	});
	
}
function showCourse(courseName){
	$.getJSON("../singleCourse",{courseName:courseName}, function(jsonData) {
				$("#cNo").empty();
				$("#cNo").append(jsonData.courseNo);
				$("#courNo").val(jsonData.courseNo);
				$("#cName").val(jsonData.courseName);
				$("#cCredits").val(jsonData.credits);
				$.getJSON("../getCourseCatalog", function(json) {
					var option="<option value='no'>无</option>";
					for(var i=0;i<json.length;i++){
						if(jsonData.pcourseName==json[i].courseName){
							option+="<option selected='selected'>"+json[i].courseName+"</option>";
						}else{
							option+="<option>"+json[i].courseName+"</option>";
						}		
					}
					$("#pre").append(option);
				});
	});
}

function updateCourse(){
	var courseNo=$("#courNo").val();
	$("#updatecourse").attr("action","../updateCourse?courseNo="+courseNo);
	$("#updatecourse").submit(); 
}
