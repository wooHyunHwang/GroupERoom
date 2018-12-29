<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="com.semi.admin.base.model.vo.*"%>
<%
	Department dept = (Department) request.getAttribute("dept");
%>
<link rel="stylesheet" type="text/css" href="/semi/assets/css/admin/base.css">
<jsp:include page="/views/layout/treeview/admin/layout-up.jsp" />

<script type="text/javascript">
	var jsonData = treeviewJson.adminJson;
	var nodeName = "부서 관리";
</script>

<section class="content">
	<div class="content-left">
		<div id="treeview"></div>
	</div>

	<div class="content-right container">
		<div class="custom_deptForm">
			<form id="updateForm" method="post">
				<table>
					<tr>
						<td><label for="inputDeptId">부서코드</label></td>
						<td><input type="text" class="form-control" id="deptId" name="deptId" value="<%=dept.getDeptId()%>" readonly></td>
					</tr>
	
					<tr>
						<td><label for="inputDeptName">부서명</label></td>
						<td><input type="text" class="form-control" id="deptName" name="deptName" value="<%=dept.getDeptName()%>" readonly></td>
					</tr>
	
					<tr>
						<td><label for="inputDeptActive">활성화 여부</label></td>
						<td>
							<select class="form-control" id="deptActive" name="deptActive" >
								<option value="<%=dept.getDeptAct()%>"></option>
								<!-- <option value="Y" selected="selected">활성화</option>
								<option value="N">비활성화</option> -->
							</select>
						</td>
					</tr>
					
					<tr>
						<td><label for="inputDeptNote">비고</label></td>
						<td><textarea id="deptNote" name="deptNote" rows="5" cols="20" class="form-control" ><%=dept.getDeptNote()%></textarea></td>
					</tr>
				</table>
	
	
				<div class="deptSaveBtn">
					<input type="submit" class="btn btn-default" id="saveBtn" value="저장" onclick="insertDept();">
				</div> 
			</form>
		</div>
	</div>
	
	<script>
	console.log(<%=dept.getDeptAct()%>);
	</script>
</section>
<jsp:include page="/views/layout/treeview/admin/layout-down.jsp" />