
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        $.ajax({
        url:"",
        data:{},
        type: "",
        dataType:"json",
        success:function (data){}


})






        String createBy=((User)request.getSession().getAttribute("user")).getName();//创建人，当前登录用户
        String id= UUIDUtil.getUUID();



</body>
</html>
