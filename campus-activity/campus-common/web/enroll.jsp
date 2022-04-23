<%--
  Created by IntelliJ IDEA.
  User: chenlj
  Date: 2020/12/29
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>活动签到系统</title>
    <meta charset="UTF-8">
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <title>活动签到系统</title>
    <link href="${APP_PATH}/css/Signin_files/bootstrap.min.css" rel="stylesheet">
    <link href="${APP_PATH}/css/Signin_files/signin.css" rel="stylesheet">

    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->

    <script src="${APP_PATH}/css/js/jquery-3.1.1.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="https://getbootstrap.com/docs/4.0/assets/img/favicons/favicon.ico">

    <title>Signin Template for Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">

</head>
<body class="text-center" style="zoom: 1;">
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    function getQueryVariable(variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
                return pair[1];
            }
        }
        return (false);
    }

    function submit() {
        // var activityId = document.getElementById("activityId").value;
        var activityId = getQueryVariable("id")
        var staffId = document.getElementById("staffId").value;
        var myData = {
            "activityId": activityId,
            "staffId": staffId,
        };
        var result;
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/activity/enroll",
            data: JSON.stringify(myData),
            contentType: "application/json;charset=UTF-8",
            success: function (callback) {
                result = callback;   // 赋值给刚才定义的值
                alert(result.message)

            }
        });
    }
</script>
<form class="form-signin">
<%--    <img class="mb-4" src="${APP_PATH}/static/Signin_files/bootstrap-solid.svg" alt="" width="72" height="72">--%>
    <h1 class="h3 mb-3 font-weight-normal">活动签到</h1>
    <label for="staffId" class="sr-only">输入您的工号</label>
    <input type="email" id="staffId" class="form-control" placeholder="输入您的工号" required="" autofocus="">
    <label for="name" class="sr-only">输入您的姓名</label>
    <input type="password" id="name" class="form-control" placeholder="输入您的姓名" required="">
    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick = "submit()">提交</button>

    <p class="mt-5 mb-3 text-muted">© 2020-2021</p>
</form>
</body>
</html>
