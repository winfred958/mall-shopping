<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>微服务统一认证</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="sign_body">
<div class="container form-margin-top">
    <form class="form-signin" action="/mall-oauth2/support/login/form" method="post">
        <h2 class="form-signin-heading" align="center">统一认证系统</h2>
        <input type="hidden" name="client_id" class="form-control" value="mall-oauth2" placeholder="所属客户端">
        <input type="hidden" name="grant_type" class="form-control" value="password" placeholder="grant_type">
        <input type="text" name="username" class="form-control form-margin-top" placeholder="账号" required autofocus>
        <input type="password" name="password" class="form-control" placeholder="密码" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">sign in</button>
        <#if error??>
            <span style="color: red; ">${error}</span>
        </#if>
    </form>
</div>
</body>
<style type="text/css">
    .sign_body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #eee;
    }

    .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }
    .form-margin-top {
        margin-top: 50px;
    }
    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }
    .form-signin .checkbox {
        font-weight: normal;
    }
    .form-signin .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }
    .form-signin .form-control:focus {
        z-index: 2;
    }
    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }
    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
    footer{
        text-align: center;
        position:absolute;
        bottom:0;
        width:100%;
        height:100px;
    }

</style>
</html>
