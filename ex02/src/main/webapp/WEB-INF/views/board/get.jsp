<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
		
			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<form id='operForm' action="/board/modify" method="get">
					<div class="form-group">
						<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno }"/>'>
						<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
						<input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
						<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
						<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
						
					</div>
				</form>
					
					<div class="form-group">
						<label>Title</label><input class="form-control" name='title' 
						value='<c:out value="${board.title }"/>' readonly="readonly"
						>
					</div>
					
					<div class="form-group">
						<label>Text area</label>
						<textarea class="form-control" rows="3" 
						name='content' readonly="readonly"><c:out  value="${board.content }"/></textarea>
					</div>
					
					<div class="form-group">
						<label>Writer</label><input class="form-control" name='writer' 
						value='<c:out value="${board.writer }"/>' readonly="readonly"
						>
					</div>
					
					<button data-oper='modify' class="btn btn-default">Modify</button>
					<button data-oper='list' class="btn btn-info">List</button>
					
				
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var operForm = $("#operForm");
		$("button[data-oper='modify']").on("click", function(e){
			console.log(1);
			operForm.attr("action","/board/modify").submit();
		});
		$("button[data-oper='list']").on("click",function(e){
			console.log(2);
			
			operForm.find("#bno").remove();
			operForm.attr("action","/board/list")
			operForm.submit();
		});
	});
</script>




<%@ include file="../includes/footer.jsp" %>