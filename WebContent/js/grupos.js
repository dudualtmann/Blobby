function leaveGroup(){
	document.getElementById("formgroups").method = "post";
	document.getElementById("formgroups").action = "SvLeaveGroup";
	document.getElementById("formgroups").submit();
}