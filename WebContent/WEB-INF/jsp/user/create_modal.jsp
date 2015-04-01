<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ page language="java" import="org.springframework.security.core.Authentication, org.springframework.security.core.context.SecurityContextHolder" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QModal </title>
<link rel="stylesheet" href="css/style.min.css">
<script type='text/javascript' src='js/jquery-2.1.3.min.js'></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css">
     
<!-- Theme CSS -->
<link href="css/theme.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
$().ready(function(){
	
   $("#modal_type .item a").click(function(e){

    e.preventDefault();
    var type = this.id;

    location.href="update_modal.htm?type="+type;
  });
});
</script>
<script type="text/javascript">
$().ready(function(){

   $('#delete-modal').click(function(e){

	   var types = [];
	    $('#modal_type :checked').each(function() {
	    	types.push($(this).val());
	    });
    
	    $.ajax({
		    type: "GET",
		    url: "delete_modal.htm",
		    data: 'types='+types,
		    async:false,
		    contentType: "application/json; charset=utf-8",
		    success: function(result){
		        alert("Modals Deleted Successfully");
		        console.log(result);
		        location.reload();
		    }
		});
  });

   $('#check-all').click(function(e){
	   $(':checkbox').prop("checked", true);
   });
   $('#uncheck-all').click(function(e){
	   $(':checkbox').prop("checked", false);
   });
   $('#cancel').click(function(){
	      $("#form-type").val("");
	      $("#form-header").val("");
		  $("#form-content").val("");
		  $("#form-footer").val("");
   });
});
</script>
<script type='text/javascript' src='js/qmodal.js'></script>
<script type="text/javascript">
$().ready(function(){

		$(document).on("click",".modalData",function(){
			var type = $(this).attr("id");
			//alert(type);

			  crossDomainAjax('modal.json',type, function (data) {
				  alert("Success");
				  alert(data.responseText);
				  
				  var parsedJSON = null;
				  if ('XDomainRequest' in window && window.XDomainRequest !== null) {
					parsedJSON = $.parseJSON(data.responseText);  
				  }
				  else{
					parsedJSON = JSON.parse(data.responseText);
				  }
				  
				 $.each(parsedJSON, function(key, value) {
						$("#"+key).html(value);
				 }); 

				 $('#myModal').modal();  
		  });  
	 });
});
</script>
<style>
    .error {
        color: red; font-weight: bold;
    }
</style>
</head>
 <body class="full-layout    nav-top-fixed   nav-right-small   responsive    clearfix breakpoint-975">

<div class="vd_body">
<header>
   <%@ include file="/WEB-INF/jsp/include/header.jsp" %>
</header>
<div class="content">
  <div class="container">
		<%@include file="/WEB-INF/jsp/include/navigation.jsp" %>

	<div style="min-height: 1048px;" class="vd_content-wrapper">
      <div style="min-height: 1048px;" class="vd_container">
<!-- Compose Content -->
 <div class="vd_content clearfix">
          <div class="vd_title-section clearfix">
            <div class="vd_panel-header">
              <h1>Create New Modal</h1>
            </div>
          </div>
          <!-- vd_title-section -->
          
          <div class="vd_content-section clearfix" >
            <div class="row">
              <div class="col-md-9">
                <div class="panel widget light-widget">
                  <div class="panel-heading no-title"> </div>
                  <!-- vd_panel-heading -->
                  
                  <div class="panel-body" id="createModal">
                    <form:form action="create_modal.htm" modelAttribute="news" id="createModal" method="post" enctype="multipart/form-data">
                      <div class="form-group clearfix">
                        <label class="col-sm-2 control-label">Type</label>
                        <div class="col-sm-10 controls">
                          <input id="form-type" type="text" class="input-border-btm" name="type" placeholder="type name">
                      	  <form:errors path="type" cssClass="error"/>
                        </div>
                      </div>
                       <div class="form-group clearfix">
                        <label class="col-sm-2 control-label">Header</label>
                        <div class="col-sm-10 controls">
                          <input id="form-header" type="text" class="input-border-btm" name="header" placeholder="header">
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label class="col-sm-2 control-label">Content</label>
                        <div class="col-sm-10 controls">
                          <input id="form-content" type="text" class="input-border-btm" name="content" placeholder="content">
                        </div>
                      </div>
                      <div class="form-group  clearfix">
                        <label class="col-sm-2 control-label">Footer</label>
                        <div class="col-sm-10 controls">
                          <input type="text" id="form-footer" class="input-border-btm" name="footer" placeholder="footer">
                        </div>
                      </div>
                    <div>  


					<div class="imageDiv">
					<p>Select Images to upload..</p>
			 
		
                    	<input name="newsImage" type="file" />
					 </div>
					 </div>
                     
                     <br/>
                     <br/>

                      <div class="form-group form-actions">
                        <div class="col-sm-12">
                          <button type="submit" class="btn vd_btn vd_bg-green vd_white" name="save" >Save</button>
                          <button type="button" class="btn vd_btn vd_bg-yellow vd_white" name="cancel" id="cancel">Cancel</button>
                        </div>
                      </div>
                    </form:form>
                  </div>
                  <!-- panel-body  --> 
                  
                </div>
                <!-- panel --> 
              </div>
             
                           <!-- col-md-8 -->
              
              <div class="col-md-3">
                <div class="panel widget">
                  <div class="panel-heading vd_bg-yellow">
                    <h3 class="panel-title"> <span class="menu-icon"></span> Modal </h3>
                  </div>
                  <!-- vd_panel-heading -->
                  
                  <div class="panel-body" style="width: 256px; ">
                    <!-- <div class="form-group clearfix mgtp-10">
                      <div class="vd_input-wrapper light-theme"><span class="menu-icon"></span>
                        <input type="text" id="filter-text" placeholder="Search">
                      </div>
                    </div> -->
                    <br>
                          <a href="#" id="check-all">Check All</a> <span class="mgl-10 mgr-10">/</span> <a href="#" id="uncheck-all">Uncheck All</a>  
     
                          <hr class="mgtp-5">                   
                          <div class="form-group clearfix" style="height: 250px; overflow-y:scroll;">
                              <div class="col-md-12">
                                <div class="content-list content-menu" id="email-list">
                                  <div class="list-wrapper wrap-25 isotope" style="position: relative; height: 616px;">
                                  <div class="navbar-menu clearfix">
                                  <div class="vd_menu">
                                    <ul id="modal_type">
										<c:forEach items="${modals}" var="modal">
	  										<li class="item">
	                                    		<div style="position: absolute; left: 0px; top: 0px; padding-left: 4%; text-align:left; width: 80%;">
		                                    		<div style="display: inline-block;float: left;width: 15%;">
		                                    			<input type="checkbox" id="checkbox-1" value="${modal.type}">
		                                    		</div>
		                                      		<div style="display:inline;">
		                                      			<label style="cursor: pointer;" class="modalData" id="${modal.type}"><c:out value="${modal.type}"></c:out></label>
		                                      		</div>
	                                    		</div>
	  										</li>
	  									</c:forEach>					       
									</ul>
								  </div>
								  </div>
                                </div>
                                  <!-- list-wrapper --> 
                                </div>
                                <!-- content-list --> 
                              </div>
                              <!-- col-md-12 --> 
                            </div>
                            <!-- form-group -->
                            
                        
                      <hr>
                      <div class="form-group form-actions">
                        <div class="col-sm-12">
                          <button type="button" id="delete-modal" class="btn vd_btn vd_bg-blue vd_white">Delete</button>
                          <button type="button" class="btn vd_btn vd_bg-grey vd_white" onclick="location.href='create_modal.htm'">Add New</button>
                        </div>
                      </div>
                  </div>
                  <!-- panel-body  --> 
                  
                </div>
                <!-- panel --> 
              </div>
              <!-- col-md-8 --> 
            </div>
            <!-- row --> 
            
          </div>
          
          <!-- .vd_content-section --> 	
        </div>
        </div>
       </div>
      </div>
     </div>
<!-- Compose Content -->
	<%@include file="/WEB-INF/jsp/include/footer.jsp" %>
<!-- .vd_body END  -->
<a id="back-top" href="#" data-action="backtop" class="vd_back-top"> <i class="fa  fa-angle-up"> </i> </a>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                        <div class="modal-header vd_bg-blue vd_white">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                          <img src='images/x.png' alt='' /></button>
                          <h4 class="modal-title" id="header"></h4>
                        </div>
                        <div class="modal-body"> 
                        	
                            <div class="form-group">
                              <label class="col-sm-4 control-label"></label>

                            </div>
                            <div class="form-group">
                              <label class="col-sm-4 control-label" id ="content"></label>

                            </div>
                            <div class="form-group">
                              <label class="col-sm-4 control-label"></label>
                            </div>
                            
                        
                        </div>
                        <div class="modal-footer background-login">
                          <h6 class="modal-footer" id="footer"></h6>
                        </div>
                      </div>
                      <!-- /.modal-content --> 
                    </div>
                    <!-- /.modal-dialog --> 
                  </div>	
</body>
</html>