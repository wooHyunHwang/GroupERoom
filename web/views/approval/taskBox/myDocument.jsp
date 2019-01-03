<%@page import="com.semi.admin.user.model.vo.Employee"%>
<%@page import="com.semi.approval.document.vo.MyDocument"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	ArrayList<MyDocument> list = (ArrayList<MyDocument>)request.getAttribute("list");
	Employee employee = (Employee)session.getAttribute("loginUser");
%>

<jsp:include page="/views/layout/treeview/approval/layout-up.jsp" />
<link rel="stylesheet" type="text/css"
	href="/semi/assets/css/approval/taskBox.css">

<script>
	var jsonData = treeviewJson.approvalJson;
	var nodeName = "내 문서함";
</script>
<section class="content">

	<div class="content-left">
		<div id="treeview"></div>
	</div>

	<div class="content-right container">

		<a href="/semi/views/approval/taskBox/choiceDocument.jsp"><button class="writeBtn">작성</button></a>
		<button class="sendBtn" onclick="send()">상신</button>
		<button class="deleteBtn" onclick="trash()">삭제</button>
		<table>
			<thead>
				<tr>
					<th><input type="checkbox" name="checkAll" id="checkAlltr"
						onclick="checkAll();" style="height: 17px; width: 17px;"></th>
					<th>번 호</th>
					<th>작 성 자</th>
					<th>부 서</th>
					<th>문 서 번 호</th>
					<th>작 성 날 짜</th>

				</tr>
			</thead>
			<tbody>
			<% if(list != null) { %>
			<% for(int i=0; i<list.size(); i++) { 
				   	if(list.get(i).getWriterNum() == employee.getEmpid()) {
				   		if(list.get(i).getSubmission().equals("N")) {
				%>
				<tr>
					<td><input type="checkbox" name="checkTd"
						style="height: 17px; width: 17px;"></td>
					<td><%= list.get(i).getNum() %></td>
					<td><%= list.get(i).getWriter() %></td>
					<td><%= list.get(i).getDeptName() %></td>
					<td><a class="detailA" href="/semi/selectOne.so?num=<%=list.get(i).getDocNum() %>"><%= list.get(i).getDocNum() %></a></td>
					<td><%= list.get(i).getWriteDay() %></td>
				</tr>
				<% } %>
				<% } %>
				<% } %>
				<% }else { %>
				<tr>
					<td><input type="checkbox" name="checkTd"
						style="height: 17px; width: 17px;"></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<% } %>
			</tbody>
		</table>
		<div class="btnArea">
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
	</div>
</section>

<script>
	//전체 체크시 색 바뀜
	function checkAll() {
		if ($("#checkAlltr").is(':checked')) {
			$("input[name=checkTd]").prop("checked", true);
			$("input[name=checkTd]").parent().parent().addClass("selected");
			
		} else {
			$("input[name=checkTd]").prop("checked", false);
			$("input[name=checkTd]").parent().parent().removeClass("selected");
		}
	}
	
	//체크시 색 바뀜
	 $("input:checkbox").on('click', function() { 
		if ( $(this).prop('checked') ) { 
		$(this).parent().parent().addClass("selected"); 
		} 
		else { 
		$(this).parent().parent().removeClass("selected"); 
		} 
	 }); 
	 
	function send() {
		var sendArr = new Array();
		var checkbox = $("input[name=checkTd]:checked");
	 	checkbox.each(function(i){
	 		var tr = checkbox.parent().parent().eq(i);
	 		var td = tr.children();
	 		
            var docNum = td.eq(4).text();
            sendArr.push(docNum);
            location.href="<%= request.getContextPath()%>/apprSendDocument.asd?docNum=" + sendArr + ",";
		});
	}
	function trash() {
		var trashArr = new Array();
		var checkbox = $("input[name=checkTd]:checked");
	 	checkbox.each(function(i){
	 		var tr = checkbox.parent().parent().eq(i);
	 		var td = tr.children();
	 		
            var docNum = td.eq(4).text();
            var my = "my";
            trashArr.push(docNum);
            location.href="<%= request.getContextPath()%>/sendTrash.st?docNum=" + trashArr + "," + my;
	 	});
	}
</script>
<jsp:include page="/views/layout/treeview/approval/layout-down.jsp" />