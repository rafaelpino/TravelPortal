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
<title>Login</title>
</head>
<body>
    <div class="easyui-navpanel">
        <header>
            <div class="m-toolbar">
                <span class="m-title">Welcome to Travel Portal, please enter your credentials</span>
            </div>
        </header>
        <div style="margin:20px auto;width:100px;height:100px;overflow:hidden">
            <img src="../images/crossover.png" style="margin:0;width:100%;height:100%;">
        </div>
        <div style="padding:0 20px">
            <div style="text-align:center;margin-top:30px">
               <input  id="userName" class="easyui-textbox" data-options="prompt:'Enter a email address...',validType:'email'" style="width:50%;height:38px">
            </div>
            <div style="text-align:center;margin-top:30px">
                <input id="userPass" class="easyui-passwordbox" data-options="prompt:'Type password',validType:'length[0,8]'" style="width:50%;height:38px">
            </div>
            <div style="text-align:center;margin-top:30px">
                <a href="#" onclick="login('login','index');" class="easyui-linkbutton" style="width:50%;height:40px"><span style="font-size:16px">Login</span></a>
            </div>
            <div style="text-align:center;margin-top:30px">
                <a href="#" onclick="register('register','index');" class="easyui-linkbutton" plain="true" outline="true" style="width:100px;height:35px"><span style="font-size:16px">Register</span></a> 
            </div>
        </div>
    </div>
</body>    
</html>