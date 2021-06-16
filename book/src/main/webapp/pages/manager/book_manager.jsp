<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.deleteClass").click(function (){
				return confirm("你确定要删除吗【"+$(this).parent().parent().find("td:first").text()+"】?");
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
            <c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>

			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
			</tr>	
		</table>
<%--		分页条的开始--%>
		<div id="page_nav">
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="${ requestScope.page.url}&pageNo=1">首页</a>
				<a href="${ requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
			</c:if>
			<c:choose>
				<c:when test="${requestScope.page.pageTotal<=5}">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="${requestScope.page.pageTotal}"/>
 				</c:when>

				<c:when test="${requestScope.page.pageTotal>5}">
					<c:choose>
						<c:when test="${requestScope.page.pageNo<=3}">
							<c:set var="begin" value="1"/>
							<c:set var="end" value="5"/>
						</c:when>
						<c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
							<c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
							<c:set var="end" value="${requestScope.page.pageTotal}"/>
						</c:when>
						<c:otherwise>
							<c:set var="begin" value="${requestScope.page.pageNo-2}"/>
							<c:set var="end" value="${requestScope.page.pageNo+2}"/>
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			<c:forEach begin="${begin}" end="${end}" var="i">
				<c:if test="${i ==requestScope.page.pageNo}">
					【${i}】
				</c:if>
				<c:if test="${i!=requestScope.page.pageNo}">
					<a href="${ requestScope.page.url}&pageNo=${i}">${i}</a>
				</c:if>
			</c:forEach>
			<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
				<a href="${ requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
				<a href="${ requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
			</c:if>
			共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
			到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
			<input id="searchPageBtn" type="button" value="确定">

			<script type="text/javascript">

				$(function (){
					$("#searchPageBtn").click(function (){
						var pageNo=$("#pn_input").val();
						location.href="${pageScope.basePath}${ requestScope.page.url}&pageNo="+pageNo;
					});
				});

			</script>
		</div>
<%--		分页跳的结束--%>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>