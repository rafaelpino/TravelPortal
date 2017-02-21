<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="css/mobile.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/loadingoverlay.min.js"></script>
<script
	src="https://raw.githubusercontent.com/MrRio/jsPDF/master/dist/jspdf.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/2.0.16/jspdf.plugin.autotable.js"></script>
<title>Login</title>
</head>
<body>

	<div class="easyui-layout" style="width: 100%; height: 600px;">
		<div id="welcome"
			data-options="region:'north',title:'Welcome',split:true"
			style="height: 100px;">
		<a href="/" class="easyui-linkbutton" plain="true" iconCls="icon-cancel">Logout</a>	
		</div>

		<div data-options="region:'west',split:true" style="width: 35%;">
			<table id="tt" title="Available Offers" class="easyui-datagrid"
				url="offers" scrollbarSize="1" idField="itemid" pagination="true"
				fitColumns="true" fit="true" singleSelect="true">
				<thead>
					<tr>
						<th field="ck" checkbox="true" width="10"></th>
						<th field="from" width="80">From</th>
						<th field="to" width="80" align="right">To</th>
						<th field="price" width="30" align="right">Unit Cost</th>
						<th field="currency" width="30" align="right">Currency</th>
					</tr>
				</thead>
			</table>
		</div>

		<div data-options="region:'center',split:true" style="width: 30%;">
			<table id="ticketList" title="My Tickets" class="easyui-datagrid"
				scrollbarSize="1" idField="itemid" pagination="true"
				fitColumns="true" fit="true" singleSelect="true">
				<thead>
					<tr>
						<th field="from" width="30">From</th>
						<th field="to" width="30" align="right">To</th>
						<th field="amount" width="30" align="right">Amount</th>
					</tr>
				</thead>
			</table>
		</div>

		<div data-options="region:'east',split:true" style="width: 30%;">
			<!-- <ul id="balancesList" class="easyui-datalist" title="Balances">
			</ul>-->
			<table id="balancesList" title="Balance" class="easyui-datagrid"
				scrollbarSize="1" idField="itemid" pagination="true"
				fitColumns="true" fit="true" singleSelect="true">
				<thead>
					<tr>
						<th field="accountName" width="30">Account</th>
						<th field="balance" width="30" align="right">Balance</th>
						<th field="currency" width="10" align="right">Currency</th>
					</tr>
				</thead>
			</table>
		</div>
		<div data-options="region:'south',title:'Options',split:true"
			style="height: 100px;">
			<a href="#" onclick="buy('tt','buyTicket','ticketList','myTickets')" class="easyui-linkbutton" iconCls="icon-ok">Buy</a>
			<a href="#" onclick="exportPDF('tt')" class="easyui-linkbutton" iconCls="icon-print">Export Offers</a>
			<a href="#" onclick="exportPDF('ticketList')" class="easyui-linkbutton" iconCls="icon-print">Export Tickets</a>
			<a href="#" onclick="mailMe('mailMe')" class="easyui-linkbutton" iconCls="icon-large-chart">Send me a report</a>
			
		</div>

	</div>
</body>
</html>
