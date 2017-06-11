<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--  <!DOCTYPE html>-->
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>人大通管理中心</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-combined.min.css">
	<link rel="stylesheet" href="css/bootstrap-clockpicker.min.css">
	<link rel="stylesheet" href="css/main.css"></head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">人大通管理中心</a>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">菜单</h3>
					</div>
					<div class="panel-body">
						<div class="accordion" id="accordion-897486">
							<div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion-897486" href="#accordion-element-708546">新闻分类</a>
								</div>
								<div id="accordion-element-708546" class="accordion-body collapse">
									<div class="accordion-inner">
										<ul>
											<li>
												<a href="#">信息管理</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<!--  <div class="accordion-group">
								<div class="accordion-heading">
									<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-897486" href="#accordion-element-256831">模板管理</a>
								</div>
								<div id="accordion-element-256831" class="accordion-body collapse">
									<div class="accordion-inner">
										<ul>
											<li>
												<a href="#">静态化管理</a>
											</li>
										</ul>
									</div>
								</div>
							</div>-->
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-10">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">内容</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered content-table">
								<thead class="well well-sm">
									<tr>
										<td>
											<input type="checkbox"></td>
										<td>标题</td>
										<td>内容详情</td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											<input type="checkbox"></td>
										<td>
											<a href="javascript:void(0)">[政策解读]加快科技创新，实施科技创新卷补助</a>
										</td>
										<td>
											<a href="#">查看详情</a>
										</td>
									</tr>
									<tr>
										<td>
											<input type="checkbox"></td>
										<td>
											<a href="javascript:void(0)">[政策解读]加快科技创新，实施科技创新卷补助</a>
										</td>
										<td>
											<a href="#">查看详情</a>
										</td>
									</tr>
									<tr>
										<td>
											<input type="checkbox"></td>
										<td>
											<a href="javascript:void(0)">[政策解读]加快科技创新，实施科技创新卷补助</a>
										</td>
										<td>
											<a href="#">查看详情</a>
										</td>
									</tr>

								</tbody>

							</table>
							<div class="well well-sm">
								<div class="well-inner">
									<p>
										共
										<span class="num">3</span>
										条
									</p>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="#" class="disable">首页</a>
									&nbsp;&nbsp;
									<a href="#" class="disable">上一页</a>
									&nbsp;&nbsp;
									<a href="#">下一页</a>
									&nbsp;&nbsp;
									<a href="#">尾页</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<p>
										每页
										<span class="perpage_num">10</span>
										条
									</p>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<p>
										当前第
										<span class="page_num">1</span>
										页/共
										<span class="whole_page_num">4</span>
										页
									</p>
									<p class="skip_page">
										跳转到第
										<input type="text">
										页
										<a href="#">跳转</a>
									</p>
								</div>
							</div>
						</div>
						<div class="btn-group">
							<button type="button" class="btn btn-primary select-btn" data-toggle="modal" data-target="#myModal">消息推送</button>
							<button type="button" class="btn btn-success select-btn">热门标记</button>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">消息推送</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">推送内容</label>
							<div class="col-sm-6">
								<input type="email" class="form-control" id="inputEmail3"></div>
							<div class="col-sm-4"></div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">有效时间(秒)</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="inputPassword3"></div>
							<div class="col-sm-4"></div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">推送对象</label>
							<div class="col-sm-6">
								<input type="radio" class="form-control">
								所有对象
								<input type="radio" name="" id="">指定对象</div>
							<div class="col-sm-4"></div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">推送时间</label>
							<div class="col-sm-6">
								<div class="input-group clockpicker" data-placement="right" data-align="top" data-autoclose="true">
									<input type="text" class="form-control" value="09:32">
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-time"></span>
									</span>
								</div>
							</div>
							<div class="col-sm-4"></div>

						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary">推送</button>
					</div>
				</div>
			</div>
		</div>

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/bootstrap-clockpicker.min.js"></script>
		<script type="text/javascript">
    $('.clockpicker').clockpicker()
        .find('input').change(function(){
            // TODO: time changed
            console.log(this.value);
        });
    $('#demo-input').clockpicker({
        autoclose: true
    });
 
    
</script>       
</body>
	</html>
