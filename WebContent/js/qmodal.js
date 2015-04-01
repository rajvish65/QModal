/**
 * QModal CORS js
 */
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
