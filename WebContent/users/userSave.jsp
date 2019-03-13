<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
function loadCityInfo(){
	<!--jquery-->
	var c_povince=$("#c_povince").val();
	$("#c_city option[value!='-1']").remove();
	if(c_povince==-1){
		$("#c_city").val(-1);
		return;
	}
	$.post("dict!getCityByProvince",{provCode:c_povince},function(result){
		$("#c_city").val("-1");
		var result=eval("("+result+")");
		//alert(result);
		$.each(result,function(i,item){
			$("<option value='"+item.itemcode+"'>"+item.itemdesc+"</option>").appendTo($("#c_city"));
		});
	  });
}
</script>
<div class="data_content">、
  <div class="row-fluid">
  	<div class="span3">
  	</div>
  	<div class="span6">
	<form class="form-horizontal" action="user!userSave" method="post">
		<table  align="center" class="table">
			<tr>
				<td>姓名：</td>
				<td><input type="text" class="form-control" name="user.name" value="${user.name}"}/></td>
			</tr>
			<tr>
				<td>年龄：</td>
				<td><input type="text" class="form-control" name="user.age" value="${user.age}"/></td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<select  class="form-control" name="user.sex">
						<option value="-1">请选择性别</option>
						<c:forEach var="sexData" items="${sexDict}" >
							<option value="${sexData.itemcode}" ${user.sex==sexData.itemdesc?'selected':''}>${sexData.itemdesc}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>籍贯：</td>
				<td>
					<select id="c_povince" class="form-control" name="user.province"  onchange="loadCityInfo()">
						<option value="-1">请选择省份</option>
						<c:forEach var="provData" items="${provDict}" >
							<option value="${provData.itemcode}" ${user.province==provData.itemdesc?'selected':''}>${provData.itemdesc}</option>
						</c:forEach>
					</select>
					<select id="c_city"  class="form-control" name="user.city" >
						<option value="-1">请选择城市</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>联系电话：</td>
				<td><input type="text" class="form-control"  name="user.phone" value="${user.phone}"/></td>
			</tr>
			<tr>
				<td>邮箱：</td>
				<td><input type="text" class="form-control"  name="user.email" value="${user.email}"/></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="hidden" id="id" name="id" value="${user.id }"/>
					<input type="submit" class="btn btn-primary" value="提交"/>
				</td>
			</tr>
			
		</table>
		
	</form>
  </div>
    <div class="span3">
  	</div>
  </div>
</div>