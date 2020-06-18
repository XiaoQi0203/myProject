<%--
  Created by IntelliJ IDEA.
  User: chao
  Date: 2020/6/8
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form enctype="multipart/form-data" action="/UserModel/user/saveUser" method="post">
        <p>
            姓名:<input name="name" type="text">
        </p>
        <p>
            性别:<input name="sex" type="text">
        </p>
        <p>
            密码:<input type="password" name="password">
        </p>
        <p>
            生日:<input type="date" name="birthday">
        </p>
        <p>
            选择文件: <input type="file" name="multipartFile" />
        </p>
        <p>
            邮箱:<input type="text" name="email">
        </p>
        <p>
            <input type="submit" value="提交" />
        </p>
    </form>
</body>
</html>
