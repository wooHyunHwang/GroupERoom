<%@page import="com.semi.myPage.model.Etc.vo.Attend"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
	ArrayList<Attend> list = (ArrayList<Attend>)request.getAttribute("list");
	int count = 1;
	boolean exist = false;
	if(list.size() == 0){
		exist = false;
	}else{
		exist = true;
	}
%>

<jsp:include page="/views/layout/treeview/mypage/layout-up.jsp" />

<style>
.btn{
	text-align: center;
	background-color: #205181;
	padding: 5px;
	color:white;
	border-radius: 10px;
	width: 120px;
	height: 40px;
}
#btn1{
	margin-bottom: 20px;
}
#btn2{
	margin-left: 20px;
	margin-bottom: 20px;
}
.line{
	border: 2px solid skyblue;
	border-collapse: collapse;
	padding: 8px;
	text-align: center;
}
#messageList{
	width: 700px;
}
#alignDiv{
	margin-top: 80px;
}
</style>

<script>
	var jsonData = treeviewJson.myPageJson;
	var nodeName = "근태관리";
</script>

<section class="content">

	<div class="content-left">
		<div id="treeview"></div>
	</div>

	<div class="content-right container">
		<form action="" method="post" id="formId">
		<div align="center" id="alignDiv">
			<table id="messageList" class="line" align="center">
				<% if(exist) { %>
				<tr>
					<th class="line"></th>
					<th class="line">이름</th>
					<th class="line" style="width: 250px;">출근 시간</th>
					<th class="line" style="width: 250px;">퇴근 시간</th>
				</tr>
				<% 	for(Attend at : list) { %>
					<% if(at.getGetOff() != null) { %>
				<tr>
					<td class="line"><%= at.getRnum() %></td>
					<td class="line"><%= at.getEmpName() %></td>
					<td class="line"><%= at.getAttendance() %></td>
					<td class="line"><%= at.getGetOff() %></td>
				</tr>
					<% } else { %>
				<tr>
					<td class="line"><%= at.getRnum() %></td>
					<td class="line"><%= at.getEmpName() %></td>
					<td class="line"><%= at.getAttendance() %></td>
					<td class="line"></td>
				</tr>
				<% 		count++; %>
				<% 	   } %>
				<% } %>
				<% } else { %>
				<tr>
					<th class="line" colspan="4"><p>휴가를 사용한 내역이 없습니다.</p></th>
				</tr>
				<% } %>
			</table>
			<div class="paging" align="center">
				<ul class="pagination">
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
</section>

<script type="text/javascript">
	// 전체선택 쿼리
	$(document).ready(function() {
		$("#all").click(function() {
			if ($("#all").prop("checked")) {
				$("input[name=chkList]").prop("checked", true);
			} else {
				$("input[name=chkList]").prop("checked", false);
			}
		});
	});
</script>
<jsp:include page="/views/layout/treeview/mypage/layout-down.jsp" />