<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息维护</title>
<script>
	function userDelete(userId){
		var flag=confirm("确定删除用户"+userId+"的记录吗？");
		if(flag){
			$.post("user!userDelete",{id:userId},function(result){
				var result=eval("("+result+")");
				if(result.error){
					alert(result.error);
				}else{
					alert("删除成功");
					window.location.href="${pageContext.request.contextPath}/user!userQuery";
				}
				
			})
		}
		
	}
</script>
</head>
<body>
<div class="data_content">、
  <div class="row-fluid">
     <div class="span2">
  	</div>
  	 <div class="span8">
  	 	<form class="form-inline"  action="user!userQuery" method="post">
				<label>编号</label>
				<input type="text" class="form-control" name="selectUser.id" value="${selectUser.id==-1?'':selectUser.id}"/>
				<label>姓名</label>
				<input type="text" class="form-control" name="selectUser.name" value="${selectUser.name}"/>
				<label>性别</label>
				<select class="form-control" name="selectUser.sex">
					<option value="-1">请选择性别</option>
						<c:forEach var="sexData" items="${selectSexDict}" >
							<option value="${sexData.itemcode}" ${selectUser.sex==sexData.itemdesc?'selected':''}>${sexData.itemdesc}</option>
						</c:forEach>
				</select>
				<input type="submit" class="btn btn-primary" value="查询"/>
		</form>
		
  	 	<table class="table">
  	 		<tr>
					<th>编号</th>
					<th>姓名</th>
					<th>年龄</th>
					<th>性别</th>
					<th>籍贯</th>
					<th>联系电话</th>
					<th>邮箱</th>
					<th>操作</th>
			</tr>
  	 		<c:forEach var="qryUser" items="${userList}" >
			 <tr>
					<td>${qryUser.id}</td>
					<td>${qryUser.name}</td>
					<td>${qryUser.age}</td>
					<td>${qryUser.sex}</td>
					<td>${qryUser.birthPlace}</td>
					<td>${qryUser.phone}</td>
					<td>${qryUser.email}</td>
					<td>
						<button class="btn" type="button" onclick="javascript:window.location='user!userPreSave?id=${qryUser.id}'">修改</button>
						<button class="btn" type="button" onclick="userDelete('${qryUser.id}')">删除</button>
					</td>
			</tr>
			</c:forEach>
		</table>
  	 </div>  	
  	 <div class="span2">
  	 </div>
  </div>
 </div>
</body>
</html>