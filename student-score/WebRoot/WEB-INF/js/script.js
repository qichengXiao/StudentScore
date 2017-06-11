window.onload = function() {
    //获取菜单
	 console.log("进入onload");
	 $.ajax({
	        url: 'http://localhost:8080/ScoreManage/getChannels',
	        method: 'POST',
	        success: function(data, status) {
	            if (data.status === '200') {
	                console.log(data.data);
	                for (var i in data.data) {
	                    console.log(i);
	                    var new_elem = '<div class="accordion-group">' +
	                        '<div class="accordion-heading">' +
	                        '<a class="accordion-toggle collapsed" data-toggle="collapse"' +
	                        ' data-parent="#accordion-897486" id="' + i + '">' + data.data[i] + '</a>' +
	                        '</div>' +
	                        '<div id="' + i + '" class="accordion-body collapse" style="display:inline">' +
	                        '</div>'+
	                    '</div>';
	                    $('.accordion').append(new_elem);

	                    (function(index) {
	                        $('#' + index).on('click', function() {
	                            var perpage_num = $('#perpage_num').val();
	                            console.log(perpage_num);
	                            getOneCourseScores(index, 1, perpage_num);
	                        });
	                    }(i));
	                }
	            } else {
	                alert('获取菜单失败,请重新刷新');
	            }
	        }
	    }) ;
	  $.ajax({
		  //获取学院班级
	        url: 'http://localhost:8080/ScoreManage/getClasses',
	        method: 'POST',
	        success: function(data, status) {
	        	console.log("进入getclasses");
	            if (data.status === '200') {
	                console.log(data.data);
	                for (var i in data.data) {
	                	
	                    console.log(i);
	                    var new_elem = '<div class="accordion-group">' +
	                        '<div class="accordion-heading">' +
	                        '<a class="accordion-toggle collapsed" data-toggle="collapse"' +
	                        ' data-parent="#accordion-8974862" id="' + 123456 + '">' + i + '</a>' +
	                        '</div>' +
	                        '<div id="' + 123456789 +'" class="accordion-body collapse" style="display:inline">' +
	                        '<div class="accordion-inner">' +
	                        '<ul>' ;
	                    var inter_elem='' ;
	                        for (var j = 0; j < data.data[i].length; j++) {
	                        	inter_elem= 
	                        		'<li>' +
	                        		//'<a href="$" id="' + data.data[i][j].id + '">'+data.data[i][j].className+'</a>' +
	                        		'<a href="#" id="' + data.data[i][j].id + '-hot">'+data.data[i][j].className+'</a>' +
	                        		'</li>' +inter_elem;
	                        }
	                       var end_elem = 
	                        '</ul>' +
	                        '</div>' +
	                        '</div>'+
	                    '</div>';
	                    $('.accordion2').append(new_elem+inter_elem+end_elem);
	                    for (var j = 0; j < data.data[i].length; j++) {
		                    (function(index) {
		                    	var classId = data.data[index][j].id;
		                    	
		                    	$('#' + classId + '-hot').on('click', function() {
		                            var perpage_num = $('#perpage_num').val();
		                            console.log("进入getOneClassScoress，classId="+classId);
		                           
		                            $('.order-select-hiddenId').text('');
		                            $('.order-select-hiddenId').text(classId);
		                            getOneClassScores(classId, 1, perpage_num);
		                        });
		                    }(i));	
	                    }
	                }
	            } else {
	                alert('获取班级失败,请重新刷新');
	            }
	        }
	    });

	    //获取该id课程下的所有学生成绩
	    var getOneCourseScores = function(id, curpage, pagesize) {
	    	 
	        $('#newslist').text('');
	        var data = {
	            channelId: id,
	            currentPage: curpage,
	            pageSize: pagesize
	        };
	        
	        $('.well-sm-title').text('');
	        $('.well-sm-title').append('<tr><td></td><td>学号</td><td>分数</td><td>姓名</td><td>班级</td></tr>');
			
	        
	        $.ajax({
	            url: 'http://localhost:8080/ScoreManage/oneCourseScores',
	            method: 'POST',
	            data: data,
	            success: function(data, status) {
	                console.log(data,data);
	                $('.num').text(data.totalCount);
	                $('.page_num').text(data.currentPage);
	                $('.whole_page_num').text(data.pageCount);
	                $('.first_page,.next_page,.prev_page,.last_page,.go_page').off('click');
	                $('#perpage_num').off('change');
	                $('.first_page').on('click', function() {
	                    var perpage_num = $('#perpage_num').val();
	                    getOneCourseScores(id, 1, perpage_num);
	                });
	                $('.last_page').on('click', function() {
	                    var perpage_num = $('#perpage_num').val();
	                    getOneCourseScores(id, data.pageCount, perpage_num);
	                });
	                $('.go_page').on('click', function() {
	                    var skipto = $('#skip_to').val();
	                    if (skipto <= data.pageCount && skipto >= 1) {
	                        var perpage_num = $('#perpage_num').val();
	                        getOneCourseScores(id, skipto, perpage_num);
	                    } else {
	                        alert('请输入合理的页数');
	                    }
	                });
	                if (curpage < data.pageCount) {
	                    $('.next_page').on('click', function() {
	                        var perpage_num = $('#perpage_num').val();
	                        getOneCourseScores(id, ++curpage, perpage_num);
	                    });
	                } else {
	                    $('.next_page').on('click', function() {
	                        return false;
	                    });
	                }
	                if (curpage > 1) {
	                    $('.prev_page').on('click', function() {
	                        var perpage_num = $('#perpage_num').val();
	                        getOneCourseScores(id, --curpage, perpage_num);
	                    });
	                } else {
	                    $('.prev_page').on('click', function() {
	                        return false;
	                    });
	                }
	                $('#perpage_num').change(function() {
	                    var perpage_num = $('#perpage_num').val();
	                    console.log(perpage_num);
	                    getOneCourseScores(id, 1, perpage_num);
	                    $('#perpage_num').blur();
	                });

	                if (status === 'success') {
	                    for (var i = 0; i < data.data.length; i++) {
	                        var new_elem = '<tr>' +
	                        	'<td>' +
	                        	'<input type="checkbox" name="checkbox" value="' + data.data[i].stu.id + '">'+
	                        	'</td>'+
	                        	 '<td>' +
		                            data.data[i].stu.id+
		                         '</td>' +
	                            '<td>' +
	                            data.data[i].mark+
	                            '</td>' +
	                            '<td>' +
	                            '<a href="' +'@待写@' + '">' + data.data[i].stu.name + '</a>' +
	                            '</td>' +
	                            '<td>' +
	                            '<a href="' + data.data[i].htmlpath + '">'+ data.data[i].stu.stuClass.academeName + '   ' + data.data[i].stu.stuClass.className+'</a>' +
	                            '</td>' +
	                            '</tr>';
	                        $('#newslist').append(new_elem);
	                    }
	                }
	            }
	        });
	    };
	    
	    //获取该id<<<<<班级>>>下的所有学生成绩
	    var getOneClassScores = function(id, curpage, pagesize) {
	        $('#newslist').text('');
	        $('.well-sm-title').text('');
	        var data = {
	            channelId: id,
	            currentPage: curpage,
	            pageSize: pagesize
	        };
	        
	      
	        $('.well-sm-title').append('<tr><td></td><td>学号</td><td>分数</td><td>姓名</td><td>成绩</td></tr>');
	        
	        $.ajax({
	            url: 'http://localhost:8080/ScoreManage/oneClassScores',
	            method: 'POST',
	            data: data,
	            success: function(data, status) {
	                console.log(data);
	                $('.num').text(data.pageCount * 10);
	                $('.page_num').text(data.currentPage);
	                $('.whole_page_num').text(data.pageCount);
	                $('.first_page,.next_page,.prev_page,.last_page,.go_page').off('click');
	                $('#perpage_num').off('change');
	                $('.first_page').on('click', function() {
	                    var perpage_num = $('#perpage_num').val();
	                    getOneClassScores(id, 1, perpage_num);
	                });
	                $('.last_page').on('click', function() {
	                    var perpage_num = $('#perpage_num').val();
	                    getOneClassScores(id, data.pageCount, perpage_num);
	                });
	                $('.go_page').on('click', function() {
	                    var skipto = $('#skip_to').val();
	                    if (skipto <= data.pageCount && skipto >= 1) {
	                        var perpage_num = $('#perpage_num').val();
	                        getOneClassScores(id, skipto, perpage_num);
	                    } else {
	                        alert('请输入合理的页数');
	                    }
	                });
	                if (curpage < data.pageCount) {
	                    $('.next_page').on('click', function() {
	                        var perpage_num = $('#perpage_num').val();
	                        getOneClassScores(id, ++curpage, perpage_num);
	                    });
	                } else {
	                    $('.next_page').on('click', function() {
	                        return false;
	                    });
	                }
	                if (curpage > 1) {
	                    $('.prev_page').on('click', function() {
	                        var perpage_num = $('#perpage_num').val();
	                        getOneClassScores(id, --curpage, perpage_num);
	                    });
	                } else {
	                    $('.prev_page').on('click', function() {
	                        return false;
	                    });
	                }
	                $('#perpage_num').change(function() {
	                    var perpage_num = $('#perpage_num').val();
	                    console.log(perpage_num);
	                    getOneClassScores(id, 1, perpage_num);
	                    $('#perpage_num').blur();
	                });

	                if (status === 'success') {
	                    for (var i = 0; i < data.data.length; i++) {
	                        var new_elem = '<tr>' +
	                            '<td>' +
	                            '<input type="checkbox" name="checkbox" value="' + data.data[i].id + '">'+
	                            '</td>' +
	                            '<td>' +
	                            data.data[i].id+	
	                            '</td>' +
	                            
	                            '<td>' +
	                           // '<a href="' +'@待写@' + '">' + data.data[i].stu.name + '</a>' +
	                            	data.data[i].name+
	                            '</td>' +
	                            
	                            '<td>' +
		                          data.data[i].stuClass.academeName + '   ' + data.data[i].stuClass.className+'</a>' +
		                        '</td>' ;
		                     var score_elem=''; 
		                         
		                         for(var j in data.data[i].scoreMap) {
		                         	score_elem=score_elem+'<td>' + j+':' +data.data[i].scoreMap[j]+'</td>' ;
	                    		}
	                            new_elem=new_elem+score_elem+'</tr>';
	                        $('#newslist').append(new_elem);
	                    }
	                }
	            }
	        });
	    };
	    
	  //添加学生
    $('.do-add-student').on('click', function() {
		var scores=null;
		var classId =$('.add-student-classId').val();
		var name = $('.add-student-stuName').val();
		var stuId = $('.add-student-stuId').val();
		var data = {
			scores : scores,
			classId : classId,
			name : name,
			stuId : stuId
		};
		console.log(data);
		 $.ajax({
			url : 'http://localhost:8080/ScoreManage/addStudent',
			method : 'POST',
			data : data,

			success : function(data, status) {
				console.log(data);
				console.log(status);
				if (status === 'success') {
					if(data.status==='200'){
						alert(data.message );
					}
					if(data.status==='400'){
						alert(data.message);
					}
				}
			}
		});
		 
    });
    
    $('.do-add-score').on('click', function() {
    	
		var courseName=$('.add-score-courseName').val();
		var stuId = $('#add-score-stuId').val();
		var mark = $('#add-score-mark').val();
		var stuName=$('#add-score-stuName').val();
		
		var data = {
				courseName : courseName,
				stuId : stuId,
				mark : mark,
				stuName : stuName
		};
		console.log(data);
		 $.ajax({
			url : 'http://localhost:8080/ScoreManage/addOneCoureseScore',
			method : 'POST',
			data : data,
			success : function(data, status) {
				console.log(data);
				
				if (data.status === '200') {
					
					alert(data.message);
				}
				if (data.status === '400') {
					$('.btn-cancel').click();
					alert(data.message);
				}
			
				
			}
		});
		 
    });   

    $('.do-add-class').on('click', function() {
		var data = {
				classId:$('.add-class-classId').val(),
				className : $('.add-class-className').val(),
				academeName : $('.add-class-acadeName').val(),
				
		};
		console.log(data);
		 $.ajax({
			url : 'http://localhost:8080/ScoreManage/addClass',
			method : 'POST',
			data : data,
			success : function(data, status) {
				console.log(data);
				console.log(status);
				$('.btn-cancel').click();
				alert(data.message);
			}
		});
		 
    });   
    

    $('.do-update-student').on('click', function() {
    	
    	var name = $('.update-student-stuName').val();
    	var classId=$('.update-student-classId').val();
    	var stuId = $('.update-student-stuId').val();
		var scores='';
		var chine=$('.update-student-语文').val();
		var math = $('.update-student-数学').val();
		var english = $('.update-student-英语').val();
	
		if(chine !== undefined){
			scores=scores+'语文,'+chine;
		}
		if(math !== undefined){
			if(scores!=='')
				scores=scores+',';
				scores=scores+'数学,'+math;
		}
		
		if(english !== undefined){
			if(scores!=='')
				scores=scores+',';
				scores=scores+'英语,'+english;
		}
		console.log(scores);
		
		var data = {
				name:name,
				classId:classId,
				stuId:stuId,
				scores:scores
		};
		console.log(data.classId);
		 $.ajax({
			url : 'http://localhost:8080/ScoreManage/upadateStudent',
			method : 'POST',
			data : data,
			success : function(data, status) {
				console.log(data);
				console.log(status);
				if (data.status === '200') {
					$('.btn-cancel').click();
					alert(data.message);
				}
			}
		});
		 
    });   
    
    $('.do-delete-student').on('click', function() {

		var data = {
				stuId: $('#delete-student-stuId').val()
				//stuName:stuName,
		};
		
		$.ajax({
			url : 'http://localhost:8080/ScoreManage/deleteStudent',
			method : 'POST',
			data : data,
			success : function(data, status) {
				if (data.status === '200') {
					alert(data.message);
				}
				if (data.status === '400') {
					$('.btn-cancel').click();
					alert(data.message);
				}
			}
		});
		 
    });   
    
    $('.checkbox-inline').change(function() {});
    
    
    
   
 $('.add-score').on('click', function() {    
	 $.ajax({
	        url: 'http://localhost:8080/ScoreManage/getChannels',
	        method: 'POST',
	        success: function(data, status) {
	            if (data.status === '200') {
	                console.log(data.data);
	                $('.add-score-courseName').text('');
	                for (var i in data.data) {
	                    var new_elem = '<option>'+data.data[i]+'</option>';
	             
	                    $('.add-score-courseName').append(new_elem);
	                }
	            } else {
	                alert('获取菜单失败,请重新刷新');
	            }
	        }
	    }) ;    	$('.add-score-bomb-box').click(); 
    });
    
 $('.add-class').on('click', function() {     
        
	 $.ajax({
	        url: 'http://localhost:8080/ScoreManage/getClasses',
	        method: 'POST',
	        success: function(data, status) {
	            if (data.status === '200') {
	                console.log(data.data);
	                $('.add-score-courseName').text('');
	                for (var i in data.data) {
	                    var new_elem = '<option>'+i+'</option>';
	                    $('.add-class-acadeName').append(new_elem);
	                }
	            } else {
	                alert('获取菜单失败,请重新刷新');
	            }
	        }
	    }) ;
    	$('.add-class-bomb-box').click(); 
    });
 
 $('.delete-student').on('click', function() {     
	 	$('.delete-student-bomb-box').click(); 
	 });
 
 $('.add-student').on('click', function() {     
	 	$('.add-student-bomb-box').click(); 
	 	
	 	 $.ajax({
			  //获取学院班级
		        url: 'http://localhost:8080/ScoreManage/getClasses',
		        method: 'POST',
		        success: function(data, status) {
		            if (data.status === '200') {
		                console.log(data.data);
		                $('.add-student-classId').text('');
		                for (var i in data.data) {           	
		                        for (var j = 0; j < data.data[i].length; j++) {
		                        	var classId= data.data[i][j].id ;
		                        	 var className= i+'  '+data.data[i][j].className;
		                        	   var new_elem = '<option value='+classId+'>'+className+'</option>';
		                        	 $('.add-student-classId').append(new_elem);
		                        	 
		                        }
		                      
		                }
		            }
		                  
		         }
	 	 });

	});
 
 
 $('.update-student').on('click', function() {
     var selectedIds = [];
     $('input[name="checkbox"]:checked').each(function() {
         var thisdata = {
             id: this.value,
        };
         selectedIds.push(thisdata);

     });
     if (selectedIds.length === 1) {
    	 console.log("成功"+selectedIds[0]['id']);
     var data={
    	 stuId:selectedIds[0]['id']
	};
 	console.log(data);		
    $('.update-student-scores').text('');
    	 $.ajax({
		        url: 'http://localhost:8080/ScoreManage/searchStudentById',
		        method: 'POST',
		        data:data,
		        
		        success: function(data, status) {
		            if (data.status === '200') {
		            	console.log(data);
		            	console.log(data.data['stu'].id);
		            	$('.update-student-bomb-box').click(); 
		            	console.log(data.data['stu'].id);
		            	$('.update-student-stuId').val(data.data['stu'].id);
		            	$('.update-student-stuName').val(data.data['stu'].name);
		            	$('.update-student-class').val(data.data['stu'].stuClass.academeName+'  '+data.data['stu'].stuClass.className);   
		            	$('.update-student-classId').val(data.data['stu'].stuClass.id);   
		            	
		            	
		            	 for (var j = 0; j < data.data['scoreList'].length; j++) {
		            		 var s = data.data['scoreList'][j];
		            		 console.log(s.courseName+s.mark);
	                        var 	elem='<div class="form-group" >'+
							'	<label for="update-student-classId" class="col-sm-2 control-label">'+s.courseName+'</label>'+
								'<div class="col-sm-8">'+
									'<input type="text" class="form-control update-student-'+s.courseName+'" id="update-student-classId" value="'+s.mark+'"required>'+
								'</div>'+
								'<div class="col-sm-2"></div>'+
							'</div>';
	                        	 $('.update-student-scores').append(elem);
	                        }
		            }            
		            else{
		            	alert(data.message);
		            }
		         }
	 	 });

     } else {
         alert('每次只能修改一条');
     }
 });
 
 
 $('.btn-search-studId').on('click', function() {     
	   $('.update-student-scores').text('');
		 var data={
		    	 stuId:$('.search-stuId').val()
			};
		console.log(data);
		$.ajax({
		        url: 'http://localhost:8080/ScoreManage/searchStudentById',
		        method: 'POST',
		        data:data,
		        
		        success: function(data, status) {
		            if (data.status === '200') {
		            	console.log(data);
		            	console.log(data.data['stu'].id);
		            	$('.update-student-bomb-box').click(); 
		            	console.log(data.data['stu'].id);
		            	$('.update-student-stuId').val(data.data['stu'].id);
		            	$('.update-student-stuName').val(data.data['stu'].name);
		            	$('.update-student-class').val(data.data['stu'].stuClass.academeName+'  '+data.data['stu'].stuClass.className);   
		            	$('.update-student-classId').val(data.data['stu'].stuClass.id);   
		            	
		            	
		            	 for (var j = 0; j < data.data['scoreList'].length; j++) {
		            		 var s = data.data['scoreList'][j];
		            		 console.log(s.courseName+s.mark);
	                        var 	elem='<div class="form-group" >'+
							'	<label for="update-student-classId" class="col-sm-2 control-label">'+s.courseName+'</label>'+
								'<div class="col-sm-8">'+
									'<input type="text" class="form-control update-student-'+s.courseName+'" id="update-student-classId" value="'+s.mark+'"required>'+
								'</div>'+
								'<div class="col-sm-2"></div>'+
							'</div>';
	                        	 $('.update-student-scores').append(elem);
	                        }
		            	
		            }    
		            else{
		            	alert("找不到该学号");
		            }
		         }
	 	 });
	});
 
 
 $('.btn-search-className').on('click', function() {     
	   $('#newsList').text('');
		 var data={
		    	 className:$('.search-className').val()
			};
		console.log(data);
		$.ajax({
		        url: 'http://localhost:8080/ScoreManage/searchStudentByClassName',
		        method: 'POST',
		        data:data,
		        
		        success: function(data, status) {
		            if (data.status === '200') {
		            	
		            	 var perpage_num = $('#perpage_num').val();
                        console.log("进入getOneClassScoress，classId="+data.data);
                        getOneClassScores(data.data, 1, perpage_num);
		            	
		            }    
		            else{
		            	alert("找不到该班级");
		            }
		         }
	 	 });
	});
 $('option[id^="order-select"]' ).on('click', function() {   
	  var perpage_num = $('#perpage_num').val();
	  var classId=$('.order-select-hiddenId').text();
     getOneCourseScoresByOrder(classId, 1, perpage_num,this.value);

 });
 
 
 var getOneCourseScoresByOrder = function(id, curpage, pagesize,orderType) {
	  $('#newslist').text('');
      var data = {
          classId: id,
          currentPage: curpage,
          pageSize: pagesize,
          orderType:orderType
      };
      
      $('.well-sm-title').text('');
      $('.well-sm-title').append('<tr><td></td><td>学号</td><td>分数</td><td>姓名</td><td>班级</td></tr>');
     console.log(data);
     $.ajax({
         url: 'http://localhost:8080/ScoreManage/oneClassScoresByOrder',
         method: 'POST',
         data: data,
         success: function(data, status) {
             console.log(data);
             $('.num').text(data.pageCount * 10);
             $('.page_num').text(data.currentPage);
             $('.whole_page_num').text(data.pageCount);
             $('.first_page,.next_page,.prev_page,.last_page,.go_page').off('click');
             $('#perpage_num').off('change');
             $('.first_page').on('click', function() {
                 var perpage_num = $('#perpage_num').val();
                 getOneClassScores(id, 1, perpage_num);
             });
             $('.last_page').on('click', function() {
                 var perpage_num = $('#perpage_num').val();
                 getOneClassScores(id, data.pageCount, perpage_num);
             });
             $('.go_page').on('click', function() {
                 var skipto = $('#skip_to').val();
                 if (skipto <= data.pageCount && skipto >= 1) {
                     var perpage_num = $('#perpage_num').val();
                     getOneClassScores(id, skipto, perpage_num);
                 } else {
                     alert('请输入合理的页数');
                 }
             });
             if (curpage < data.pageCount) {
                 $('.next_page').on('click', function() {
                     var perpage_num = $('#perpage_num').val();
                     getOneClassScores(id, ++curpage, perpage_num);
                 });
             } else {
                 $('.next_page').on('click', function() {
                     return false;
                 });
             }
             if (curpage > 1) {
                 $('.prev_page').on('click', function() {
                     var perpage_num = $('#perpage_num').val();
                     getOneClassScores(id, --curpage, perpage_num);
                 });
             } else {
                 $('.prev_page').on('click', function() {
                     return false;
                 });
             }
             $('#perpage_num').change(function() {
                 var perpage_num = $('#perpage_num').val();
                 console.log(perpage_num);
                 getOneClassScores(id, 1, perpage_num);
                 $('#perpage_num').blur();
             });

             if (status === 'success') {
                 for (var i = 0; i < data.data.length; i++) {
                     var new_elem = '<tr>' +
                         '<td>' +
                         '<input type="checkbox" name="checkbox" value="' + data.data[i].id + '">'+
                         '</td>' +
                         '<td>' +
                         data.data[i].id+	
                         '</td>' +
                         
                         '<td>' +
                        // '<a href="' +'@待写@' + '">' + data.data[i].stu.name + '</a>' +
                         	data.data[i].name+
                         '</td>' +
                         
                         '<td>' +
	                          data.data[i].stuClass.academeName + '   ' + data.data[i].stuClass.className+'</a>' +
	                        '</td>' ;
	                     var score_elem=''; 
	                         
	                         for(var j in data.data[i].scoreMap) {
	                         	score_elem=score_elem+'<td>' + j+':' +data.data[i].scoreMap[j]+'</td>' ;
                 		}
                         new_elem=new_elem+score_elem+'</tr>';
                     $('#newslist').append(new_elem);
                 }
             }
         }
     });
 };
 
 $('.class-condition').on('click', function() {  

 	 $.ajax({
		  //获取学院班级
	        url: 'http://localhost:8080/ScoreManage/class-condition',
	        method: 'POST',
	        success: function(data, status) {
	            if (data.status === '200') {
	                console.log(data.data);
	            	$('.condition-class-from').text(''); 
	             	var elem='';
	                 for (var j = 0; j < data.data.length; j++) {
	             	  	   elem=elem+
	             	   '<div class="form-group">'+
	            		'<label for="update-student-stuId" class="col-sm-2 control-label"></label>'+
	            		'<div class="col-sm-16">'+
	            		
	            			'<p class="form-control">' +    data.data[j]  + '</p>'+
	             	   '</div>'+
	            	
	            		'<div class="col-sm-2"></div>'+
	             	   '</div>';
	             	   
	             	   
	             	   
	                 }
	                 $('.condition-class-from').append(elem);
	             	   $('.class-condition-bomb-box').click(); 
	              
	            }
	                  
	         }
 	 });
 	
 
	 	
	 });
     
   
};
