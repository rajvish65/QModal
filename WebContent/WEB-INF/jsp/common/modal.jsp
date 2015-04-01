<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modal</title>
<script type='text/javascript' src='js/jquery-2.1.3.min.js'></script>
<script type="text/javascript">
$().ready(function(){
	var type = "whizible";
	 
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
				$("#"+key).html(value);

		 }); 
		  $('#result').html(data.responseText);
    });
});

	function crossDomainAjax (url, type ,callback) {
	    
		// IE8 & 9 only Cross domain JSON GET request
		if ('XDomainRequest' in window && window.XDomainRequest !== null) {
                var xdr = new XDomainRequest();
                var query = url+'?type='+type;
                if (xdr) {
                    xdr.onload = function () {
					callback(xdr);
                    }
                    xdr.onerror = function () { console.log("Failed"); }
                    xdr.open('GET', query);
                    xdr.send();
                }
        }
		// IE7 doesn't support cross domain request at all! :( :)
		// jQuery AJAX for other browsers         
		else {
		$.ajax({
		url: url,
		cache: false,
		dataType: 'json',
		data : {
			   'type' : type
			  },
		type: 'GET',
		async: false, // must be set to false
		complete: function (data, success) {
		callback(data);
		},
		failure: function (data, success) {
			console.log("Failed");
			}
		});
		}
		}

</script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="css/font-awesome.css" rel="stylesheet" type="text/css">
     
<!-- Theme CSS -->
<link href="css/theme.css" rel="stylesheet" type="text/css">
</head>
<body>
<p>This is Modal Example!!!</p>
<br/>
<br/>
<br/>

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
<script type='text/javascript' src='js/jquery.js'></script>
<script type="text/javascript">
    $(document).ready(function() {
    	// Load dialog on load
    		$('#myModal').modal();
    });
    </script> 
    <script type="text/javascript" src="js/bootstrap.js"></script> 
 
    

</body>
</html>