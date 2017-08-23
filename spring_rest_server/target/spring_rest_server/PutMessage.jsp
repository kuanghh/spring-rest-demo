<%--
  Created by IntelliJ IDEA.
  User: FSTMP
  Date: 2017/8/23
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
发送数据包

<script type="text/javascript">
    $(function () {

        var data = {"birthday":"1503459069546","id":"10","name":"user10"}; //<---这个是json对象
        var jsondata = JSON.stringify(data);// <----这个是json对象的字符串
        //400报错
        $.ajax({
            type: "PUT",
            url: "/spring_rest_server/demo5/putUser2.action",
            data: jsondata,
            contentType:"application/json",
            success: function(msg){
                alert( "Data Saved: " + msg );
            }
        });

    })

</script>
</body>
</html>
