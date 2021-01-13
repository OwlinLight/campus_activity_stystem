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
    <title>Title</title>
</head>
<body>
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

<table>
    <tr>
        <td colspan="2">活动签到</td>
    </tr>

    <tr>
        <td>工号：</td>
        <td><input type="text" id="staffId"></td>
    </tr>
    <tr>
        <td>姓名：</td>
        <td><input type="text" id="name"></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" name="提交" onclick="submit()">
    </tr>
</table>
</body>
</html>
