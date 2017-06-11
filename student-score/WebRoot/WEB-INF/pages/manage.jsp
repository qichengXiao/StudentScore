<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!--  <!DOCTYPE html>-->
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>广东工业大学学生成绩管理中心</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/bootstrap-combined.min.css">
	<link rel="stylesheet" href="css/bootstrap-clockpicker.min.css">
	<link rel="stylesheet" href="css/main.css"></head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">广东工业大学学生成绩管理中心</a>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">课程</h3>
					</div>
					<div class="panel-body">
						<div class="accordion" id="accordion-897486"></div>
					</div>
					
					<div class="panel-heading">
						<h3 class="panel-title">班级</h3>
					</div>
					<div class="panel-body">
						<div class="accordion2" id="accordion-8974862"></div>
					</div>
				</div>
			</div>
			
			

			<div class="col-md-10">
			
			 		
							   	<form class="bs-example bs-example-form" role="form">
							    	  <div class="row">
							      	   <div class="col-lg-3">
							     	       <div class="input-group">
							     	        <input type="text" class="form-control search-stuId" >
							               <span class="input-group-btn">
							                  <button class="btn btn-default btn-search-studId" type="button">
							                     	搜索学号
							                  </button>
							               </span>
							            </div>
							         </div>
							        <div class="col-lg-3">
							     	       <div class="input-group">
							     	        <input type="text" class="form-control search-className " >
							               <span class="input-group-btn">
							                  <button class="btn btn-default btn-search-className" type="button">
							                     	搜索班级
							                  </button>
							               </span>
							            </div>
							         </div>
							         
							      </div>
							   </form>
							  
							
			
			
						
			
				<div class="btn-group">
				
						<button type="button" class="btn btn-success  add-score">添加成绩</button>
						<button type="button" style="display:none" data-toggle="modal" class="add-score-bomb-box" data-target="#myModal-add-score">添加成绩</button>
						
						
						
						<button type="button" class="btn btn-success  add-student">添加学生</button>
						<button type="button" style="display:none" data-toggle="modal" class="add-student-bomb-box" data-target="#myModal-add-student">添加成绩</button>
						
						
						<button type="button" class="btn btn-success  add-class">添加班级</button>
						<button type="button" style="display:none" data-toggle="modal" class="add-class-bomb-box" data-target="#myModal-add-class">添加班级</button>
						
		
						<button type="button" class="btn btn-success delete-student">删除学生信息</button>
						<button type="button" style="display:none" data-toggle="modal" class="delete-student-bomb-box" data-target="#myModal-delete-student">删除学生信息</button>
					
						<button type="button" class="btn btn-success class-condition">查询各班级成绩概况</button>
						<button type="button" style="display:none" data-toggle="modal" class="class-condition-bomb-box" data-target="#myModal-class-condition">查询各班级成绩概况</button>
					</div>
			
			
				<div class="panel panel-default">
					<div class="panel-heading">
						<p style="display:none" class="order-select-hiddenId" ></p>
						<h3 class="panel-title">内容
						<input id="order-select-classId" style="display: none;"/>
						
					
						<select name="order-select" id="order-select" style="left:300px;width: 150px">
										<option value="1" id="order-select-1"selected>按学号排序</option>
										<option value="21"id="order-select-21" >按语文成绩排序</option>
										<option value="22" id="order-select-22" >按数学成绩排序</option>
										<option value="23" id="order-select-23" >按英语成绩排序</option>
										<option value="3"  id="order-select-3" >按总成绩排序</option>
										<option value="4"  id="order-select-4" >按平均成绩排序</option>
									</select>
									
						</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-bordered content-table">
								<thead class="well well-sm-title">
									
								</thead>
								<tbody id="newslist"></tbody>

							</table>
							<div class="well well-sm">
								<div class="well-inner">
									<p>
										共
										<span class="num"></span>
										条
									</p>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="#" class="first_page">首页</a>
									&nbsp;&nbsp;
									<a href="#" class="prev_page">上一页</a>
									&nbsp;&nbsp;
									<a href="#" class="next_page">下一页</a>
									&nbsp;&nbsp;
									<a href="#" class="last_page">尾页</a>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<p>
										每页
										<!-- <span class="perpage_num">10</span>
									-->
									<select name="perpage_num" id="perpage_num">
										<option value="10" selected>10</option>
										<option value="20">20</option>
										<option value="50">50</option>
									</select>
									条
								</p>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<p>
									当前第
									<span class="page_num"></span>
									页/共
									<span class="whole_page_num"></span>
									页
								</p>
								<p class="skip_page">
									跳转到第
									<input type="text" id="skip_to">
									页
									<a href="#" class="go_page">跳转</a>
								</p>
							</div>
						</div>
					</div>
					<div class="btn-group">
						<button type="button" class="btn btn-success select-btn update-student">修改信息</button>
						<button type="button" style="display:none" data-toggle="modal" class="update-student-bomb-box" data-target="#myModal-update-student">修改信息</button>
						
					</div>

				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="myModal-add-score" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">添加单科成绩</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="add-score-stuId" class="col-sm-2 control-label">学号</label>
						<div class="col-sm-8">
							<input type="text" class="form-control add-score-stuId" id="add-score-stuId" required>
						</div>
						<div class="col-sm-2"></div>
					</div>
					
					<div class="form-group">
						<label for="add-score-stuName" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control add-score-stuName" id="add-score-stuName" required>
						</div>
						<div class="col-sm-2"></div>
					</div>				
					<div class="form-group">
						<label for="add-score-courseName" class="col-sm-2 control-label">科目</label>
						<div class="col-sm-8">
							<select class="form-control add-score-courseName" >
								
							</select>
						</div>
						<div class="col-sm-2"></div>
					</div>				
						<div class="form-group">
						<label for="add-score-mark" class="col-sm-2 control-label">成绩</label>
						<div class="col-sm-8">
							<input type="text" class="form-control add-score-mark" id="add-score-mark" required>
						</div>
						<div class="col-sm-2"></div>
					</div>	
				
					
				</form>
			</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary do-add-score">添加</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<div class="modal fade" id="myModal-add-student" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">添加学生信息</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="add-score-stuId" class="col-sm-2 control-label">学号</label>
						<div class="col-sm-8">
							<input type="text" class="form-control add-student-stuId" id="add-student-stuId" required>
						</div>
						<div class="col-sm-2"></div>
					</div>
					
					<div class="form-group">
						<label for="add-student-stuName" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control add-student-stuName" id="add-student-stuName" required>
						</div>
						<div class="col-sm-2"></div>
					</div>
					
					<div class="form-group">
						<label for="add-student-classId" class="col-sm-2 control-label">班级</label>
						<div class="col-sm-8">
							<select class="form-control add-student-classId" >
								
							</select>
														</div>
						<div class="col-sm-2"></div>
					</div>	
				</form>
			</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary do-add-student">添加</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	<div class="modal fade" id="myModal-add-class" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">添加班级</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="add-class-classId" class="col-sm-2 control-label">班级编号</label>
						<div class="col-sm-8">
							<input type="text" class="form-control add-class-classId" id="add-class-classId" required>
						</div>
						<div class="col-sm-2"></div>
					</div>
					
					<div class="form-group">
						<label for="add-class-acadeName" class="col-sm-2 control-label">学院名称</label>
						<div class="col-sm-8">
							<select class="form-control add-class-acadeName" >						
							</select>

						</div>
						<div class="col-sm-2"></div>
					</div>
					
					<div class="form-group">
						<label for="add-class-className" class="col-sm-2 control-label">班级名称</label>
						<div class="col-sm-8">
							<input type="text" class="form-control add-class-className" id="add-class-className" required>
						</div>
						<div class="col-sm-2"></div>
					</div>	
				
					
				</form>
			</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary do-add-class">添加</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="myModal-delete-student" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">更新学生信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="delete-student-stuId" class="col-sm-2 control-label">学生学号</label>
							<div class="col-sm-8">
								<input type="text" class="form-control delete-student-stuId" id="delete-student-stuId" required>
							</div>
							<div class="col-sm-2"></div>
						</div>
					</form>	
				</div>
			<div class="modal-footer">
					<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary do-delete-student">删除</button>
				</div>
			</div>
			</div>
		</div>


	<div class="modal fade" id="myModal-update-student" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">修改学生信息</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
					
						<div class="form-group">
							<label for="update-student-stuId" class="col-sm-2 control-label">学生学号</label>
							<div class="col-sm-8">
								<input type="text" class="form-control update-student-stuId" id="update-student-stuId" disabled='disabled'  required>
							</div>
							<div class="col-sm-2"></div>
						</div>
						
						
						<div class="form-group">
							<label for="update-student-stuName" class="col-sm-2 control-label">学生姓名</label>
							<div class="col-sm-8">
								<input type="text" class="form-control update-student-stuName" id="update-student-stuName" required>
							</div>
							<div class="col-sm-2"></div>
						</div>
						
						<div class="form-group">
							<label for="update-student-class" class="col-sm-2 control-label">学生班级</label>
							<div class="col-sm-8">
								<input type="text" class="form-control update-student-class" id="update-student-class" required>
							</div>
							<div class="col-sm-2"></div>
						</div>
						
						<div class="form-group" style="display:none">
							<label for="update-student-classId" class="col-sm-2 control-label">班级id</label>
							<div class="col-sm-8">
								<input type="text" class="form-control update-student-classId" id="update-student-classId" required>
							</div>
							<div class="col-sm-2"></div>
						</div>
						<div class="update-student-scores">
						</div>
						
						
					</form>	
				</div>
			<div class="modal-footer">
					<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary do-update-student">修改</button>
				</div>
			</div>
			</div>
		</div>




<!-- ###-->

<div class="modal fade" id="myModal-class-condition" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
		<div class="modal-dialog" role="document" style="width:800px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">班级成绩概况</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal condition-class-from">
				
						<div class="form-group">
							<label for="update-student-stuId" class="col-sm-2 control-label"></label>
							
						
							<div class="col-sm-2"></div>
						</div>
							
						
				
						
					</form>	
				</div>
			<div class="modal-footer">
					<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary do-update-student">修改</button>
				</div>
			</div>
			</div>
		</div>
	


	

	<script src="js/jquery.min.js"></script>
	<script src="js/script.js"></script>
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
