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


        var data = {"birthday":"1503474020289","id":"10","name":"用户10"}; //<---这个是json对象
        var jsondata = JSON.stringify(data);// <----这个是json对象的字符串
        $.ajax({
            type: "POST",
            url: "/spring_rest_server/demo5/postUser.action",
            data: jsondata,
            contentType:"application/json",
            success: function(msg){
                alert( "Data Saved: " + msg );
            }
        });



        var data2 = {"birthday":"Wed Aug 23 15:40:20 CST 2017","id":"10","name":"用户10"}; //<---这个是json对象
        var url = "/spring_rest_server/demo5/postUser2.action";
        $.post(url,data2,function (databack) {
            alert(databack);
        })

        $.ajax({
            type: "POST",
            url: "/spring_rest_server/demo5/postUser2.action",
            data: data2,
            contentType:"application/x-www-form-urlencoded",
            success: function(msg){
                alert( "Data Saved: " + msg );
            }
        });
    })

</script>
</body>
</html>
