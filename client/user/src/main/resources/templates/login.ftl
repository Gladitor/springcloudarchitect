<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>用户登录</title>
        <script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
    </head>
    <body>
        <div>
            <input id="mobile" placeholder="请输入手机号" value="13566778899">
        </div>
        <div>
            <input id="password" placeholder="请输入密码" type="password">
        </div>
        <div>
            <input type="button" onclick="javascript:login();" value="登录">
        </div>
        <script>
            function login(){
                var mobile = $("#mobile").val();
                var password = $("#password").val();
                if(!mobile || !mobile.length){
                    alert("请输入手机号！");
                    return;
                }
                if(!password || !password.length){
                    alert("请输入密码！");
                    return;
                }
                $.ajax({
                    url:"/user/authorizelogin",
                    contentType: 'application/json',
                    type:"POST",
                    dataType:"json",
                    data:JSON.stringify({mobile:mobile,password:password}),
                    success:function(data){
                        if(data.code == 1){
                            window.location.href = "/oauth2/authorize?clientId=${authorize.clientId}&responseType=${authorize.responseType}&redirectUri=${authorize.redirectUri}";
                            return false;
                        }else{
                            alert(data.message);
                        }
                    }
                });
            };
        </script>
    </body>
</html>