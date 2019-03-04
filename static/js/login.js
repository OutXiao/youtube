    function hello() {
        var username = '';
        var password = '';
        var imageCode = '';
        username = $("input[name='username']").val();
        password = $("input[name='password']").val();
        imageCode = $("input[name='imageCode']").val();

        if(username == '' || password == '' || imageCode == ''){
            $('#message1').show();
        }else {
            $('#message1').hide();
                $.ajax({
                    type: 'post',
                    xhrFields: {
                        withCredentials: true
                    },
                    url: "http://localhost:8060/authentication/form",
                    data: {
                        'username': username,
                        'password': password,
                        'imageCode':imageCode
                    },
                    dataType: "text"
                }).success(function (data) {
                    alert("登陆成功");
                    /*将返回信息同时存入sessionStorage，cookie*/
                    sessionStorage.setItem('token', JSON.stringify(data));
                    document.cookie = "info="+JSON.stringify(data);
                    window.location.href="index.html"
                }).error(function () {
                    alert("账号密码不正确");

                });
        }


    }


    /*获取Cookie值*/
    function getCookie(key){
        var arr =document.cookie.split("; ");
        for(var i=0;i<arr.length;i++){
            var arr2=arr[i].split("=");
            if(arr2[0]==key){
                return arr2[1];
            }
        }
        return false;
    }