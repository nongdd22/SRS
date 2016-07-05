/**
 * 
 */
$(document).ready(function(){
			var semester = $("#semester").val();
			getScheduleOfClasses(semester);
			$("#semester").change(function() {
				$("#showclass").empty();
				semester = $("#semester").val();
				getScheduleOfClasses(semester);
			});

});
	function getScheduleOfClasses(semester){
		$.getJSON("../getScheduleOfClasses",{semester:semester},function(jsonData){
			if(jsonData.length==0){
				var Html="<p id=\"warning\" class=\"bg-warning\" style=\"text-align:center;padding:4px 0\">你现在没有加入任何课程</p>"
			}else{
			var Html="<table class=\"table table-hover\" id=\"classList\"><tr><th>#</th><th>课程名称</th><th>任课教师</th><th>上课时间</th><th>上课教室</th><th>容量</th><th>选课</th>";
			for (var i=0;i < jsonData.length;i++) {
				Html+="<tr><td>"+(i+1)+"</td><td>"+jsonData[i].courseName+"</td><td>"+jsonData[i].professor+"</td>" +
					  "<td>"+jsonData[i].dayOfWeek+"&emsp;"+jsonData[i].timeOfDay+"</td><td>"+jsonData[i].room+"</td>" +
					  "<td>"+jsonData[i].seatingCapacity+"</td><td><button type='button' id='choose' class=\"btn btn-info\" onclick=\"enrollclass("+jsonData[i].sectionNo+");\">选课</button></td></tr>";
			}
			Html+="</table>";
			}
			$("#showclass").append(Html);
		});
	}
	function Search(){
		$("#showclass").empty();
		var courseName = $("#search").val();
		$.getJSON("../getSectionsbyCourse",{courseName:courseName}, function(jsonData) {	
			var Html="<table class=\"table table-hover\" id=\"classList\"><tr><th>#</th><th>课程名称</th><th>任课教师</th><th>上课时间</th><th>上课教室</th><th>容量</th><th>选课</th></tr>";
			for (var i=0;i < jsonData.length;i++) {
				Html+="<tr><td>"+jsonData[i].sectionNo+"</td><td>"+jsonData[i].courseName+"</td><td>"+jsonData[i].professor+"</td>" +
					  "<td>"+jsonData[i].dayOfWeek+"&emsp;"+jsonData[i].timeOfDay+"</td><td>"+jsonData[i].room+"</td>" +
					  "<td>"+jsonData[i].seatingCapacity+"</td><td><button class=\"btn  btn-info\" onclick=\"enrollclass("+jsonData[i].sectionNo+");\">选课</button></td></tr>";
			}
			Html+="</table>";
			$("#showclass").append(Html);
		});
	}
	function enrollclass(sectionNo){
		$.getJSON("../enrollClass",{sectionNo:sectionNo},function(json){
			alert(json.value);
		});
		
	}
