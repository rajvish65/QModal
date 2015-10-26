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
<title>Activity</title>
	<link rel="stylesheet" href="css/style.min.css">
	<script type='text/javascript' src='js/jquery-2.1.3.min.js'></script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css">
     
<!-- Theme CSS -->
<link href="css/theme.css" rel="stylesheet" type="text/css">
</head>

<body>
		<div class="row page" id="docs" style="display: block; min-height: 2791px;">
			<div class="col-md-12" style="">
				<h3>QModal Admin Page!!</h3>
				<div class="row">
					<div class="col-md-4">
						<ul id="modal_type">
							<c:forEach items="${modals}" var="modal">
	  						<li class="item">
	  						<c:set var="type" value="${modal.type}"/>
	  						<a href="" id="${modal.type}">
	  						<c:out value="${modal.type}"></c:out></a>
	  						</li>
	  						</c:forEach>					       
						</ul>
				</div>
			 </div>
		 </div>
	  </div>
	
    <div style="position: absolute;left: 90%;top: 20px;font-size: large;">
                <a href="j_spring_security_logout"><h4>Logout</h4></a>
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
                            <div id="news-image" class="form-group">
                              <img alt="News Image" src="" style="height: 150px; width: 200px;">
                            </div>
                            <div class="form-group">
                              <label class="col-sm-4 control-label" id ="content"></label>

                            </div>
                            <div class="form-group">
                              <label class="col-sm-4 control-label"></label>
                            </div>
                            
                        
                        </div>
                        <div class="modal-footer background-login">
                          <h6 class="modal-footer" id="footers"></h6>
                        </div>
                      </div>
                      <!-- /.modal-content --> 
                    </div>
                    <!-- /.modal-dialog --> 
                  </div>	
<script type='text/javascript' src='js/jquery.js'></script>
<script type='text/javascript' src='js/qmodal.js'></script>
<script type="text/javascript">
$().ready(function(){

$("#modal_type .item a").click(function(e){

    e.preventDefault();

    var type = this.id;
   
    crossDomainAjax('modal.json',type, function (data) {
		  //alert("Success");
		  //alert(data.responseText);
		  
		  var parsedJSON = null;
		  if ('XDomainRequest' in window && window.XDomainRequest !== null) {
			parsedJSON = $.parseJSON(data.responseText);  
		  }
		  else{
			parsedJSON = JSON.parse(data.responseText);
		  }
		  
		 $.each(parsedJSON, function(key, value) {
			 if(key != "newsImageData")
					$("#"+key).html(value);
			    else
				if(key == "newsImageData")
					$('#news-image img').attr("src","data:image/jpg;base64,"+value);

		 }); 

		 $('#myModal').modal();  
  });
});
});
</script>
 <script type="text/javascript" src="js/bootstrap.js"></script> 		                
</body>
</html> 