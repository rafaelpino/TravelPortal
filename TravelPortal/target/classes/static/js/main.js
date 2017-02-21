/**
 * 
 */
var balancesList = 'balancesList';
var ticketsList = 'ticketList';
var balancesURL = 'getBalances';
var ticketsURL = 'myTickets';
$(function() {
  // Handler for .ready() called.
	getBalances(balancesURL,balancesList);
	getTickets(ticketsURL,ticketsList);
	$( '#welcome' ).append( "<p>"+localStorage['user-email']+"</p>" );
});

// Redirects to an URL
function redirect(url) {
	window.location = url;
}

// Validates not empty fields
function isNotEmpty(elem,nom) {
    var str = document.getElementById(elem).value;
    if(str == null || str.length == 0) {
        alert("Please fill your data " + nom);
        setTimeout("focusElement('" + elem + "')", 0);
        return false;
    } else {
        return true;
    }
 }

// Function for login action
function login(post_url,redirect_url){
	$.LoadingOverlay("show");
	 var userName = document.getElementById("userName");
	 var userPass = document.getElementById("userPass");
	 if(null!=userName && null!=userPass){
		 var params = { "email" : userName.value, "password": userPass.value};
		 $.ajax({
				type : "GET",
				url : post_url,
				data: params,
				success : function(data) {
					$.LoadingOverlay("hide");
					// Process data
					if(data){
						$.messager.alert('Info','Authorized');
						// Set e-mail in session 
						localStorage['user-email'] = userName.value;
						redirect(redirect_url);
					}else{
						$.messager.alert('Info','Unauthorized, check your credentials or register first');
					}
					
				},
				error : function(result) {
					$.LoadingOverlay("hide");
				}
			});
	 }
}

function register(post_url,redirect_url){
	$.LoadingOverlay("show");
	 var userName = document.getElementById("userName");
	 var userPass = document.getElementById("userPass");
	 if(null!=userName && null!=userPass){
		 var params = { "email" : userName.value, "password": userPass.value};
		 $.ajax({
				type : "POST",
				url : post_url,
				data: params,
				success : function(data) {
					$.LoadingOverlay("hide");
					// Process data
					if(data){
						$.messager.alert('Info','Authorized');
						localStorage['user-email'] = userName.value;
						redirect(redirect_url);
					}else{
						$.messager.alert('Info','Problem creating user, please try again later');
					}
					
				},
				error : function(result) {
					$.LoadingOverlay("hide");
				}
			});
	 }	 
	 
}

// Function to export table data
function exportPDF(tableName){
	var dg = $('#'+tableName);
	var columns = dg.datagrid('options').columns[0];
	$.map(columns, function(col){
		col.dataKey = col.field;
	});
	var rows = dg.datagrid('getRows');

	var doc = new jsPDF('p', 'pt');
	doc.autoTable(columns, rows);
	doc.save(tableName+".pdf");
}

// Function to buy a ticket
function buy(tableName,post_url,refreshList,refreshURL){
	var row = $('#'+tableName).datagrid('getSelected');
	if (row){
		$.messager.prompt('My Title', 'Please type something', function(amount){
            if (amount){
            	$.LoadingOverlay("show");
                var params = { "email" : localStorage['user-email'], "amount": amount, "from":row.from, "to":row.to, "price":row.price};
        	    $.ajax({
        			type : "POST",
        			url : post_url,
        			data: params,
        			success : function(data) {
        				$.LoadingOverlay("hide");
        				if(data){
        					$('#'+refreshList).datagrid('reload');
        					 // Refresh balances
        					 getBalances(balancesURL,balancesList);
        				}else{
        					$.messager.alert('Info','Can not buy this ticket, check your balance');
        				}
        				
        			},
        			error : function(result) {
        				$.LoadingOverlay("hide");
        			}
        		});
            }
        });
	}else{
		$.LoadingOverlay("hide");
	}
}

//Function to send a report of your tickets
function mailMe(post_url){
		$.LoadingOverlay("show");
	    var params = { "email" : localStorage['user-email']};
	    $.ajax({
			type : "POST",
			url : post_url,
			data: params,
			success : function(data) {
				$.LoadingOverlay("hide");
				$.messager.alert('Info','A report of your tickets has been sent to your e-mail account.');
			},
			error : function(result) {
				// handle the error
			}
		});
	   
}

//Function to buy a ticket
function getBalances(post_url,balancesList){
	     
	    var params = { "email" : localStorage['user-email']};
	    $('#'+balancesList).datagrid({
	        url: post_url+"?email="+localStorage['user-email'],
	        checkbox: false,
	        lines: true
	    });
}

//Function to list tickets
function getTickets(post_url,ticketsList){
	     
	    var params = { "email" : localStorage['user-email']};
	    $('#'+ticketsList).datagrid({
	        url: post_url+"?email="+localStorage['user-email'],
	        checkbox: false,
	        lines: true
	    });
}

//Function to list tickets
function getOrders(post_url,ticketsList){
	     
	    var params = { "email" : localStorage['user-email']};
	    $('#'+ticketsList).datagrid({
	        url: post_url+"?email="+localStorage['user-email'],
	        checkbox: false,
	        lines: true
	    });
}


