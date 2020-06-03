var contentType = "application/x-www-form-urlencoded; charset=utf-8"

window.onload = function(){  
    queryAllUser();
} ;

$("#showAllUser").click(function(){
    queryAllUser();
});


$("#addNewUser").click(function(){
    $('#userModal').modal('show');
    $('#deleteUser').css("visibility","hidden");
    $('#InputUserName').attr("value","");
    $('#InputTel').attr("value","");
    $('#InputEmail').attr("value","");
    $('#InputPassword').attr("value","");

    $('#submitUser').click(function(){
        var username = $('#InputUserName').val();
        var tel = $('#InputTel').val();
        var email = $('#InputEmail').val();
        var password = $('#InputPassword').val();

        if(username == "" || tel == "" || email == "" || password == "")
        {
            alert("所有选项不可为空!");
            return;
        }
        params = {}
        params.userName = username;
        params.tel = tel;
        params.email = email;
        params.password = password;
        
        $.ajax({
            type : "POST",
            contentType: contentType,
            url : "http://monkeydoc.liadrinz.cn/rest/user",
            data : params,
            dataType : 'json',
            success : function(data) {
                $('#userModal').modal('hide');
                window.location.reload();
            },
            error : function(e){
                console.log(e.status);
                console.log(e.responseText);
            }
        });

    })
});


$("#searchUser").click(function(){
    var targetName = $("#SearchUserName").val();
    var targetTel = $("#SearchTel").val();
    var targetEmail = $("#SearchEmail").val();

    var params = {};
    if(targetName != "")
        params.userName = targetName;
    if(targetTel != "")
        params.tel = targetTel;
    if(targetEmail != "")
        params.email = targetEmail;


    if($.isEmptyObject(params))
    {
        queryAllUser();
    }
    else
    {
        $.ajax({
            type : "GET",
            url : "http://monkeydoc.liadrinz.cn/rest/user",
            data : params,
            dataType:'json',
            success : function(data) {
                var table = $("#userTable");
                $("#userTable tr:not(:first)").empty(); 
                loadUser(data,table);
            },
            error : function(e){
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }
});

$("#searchUserByid").click(function(){
    var targetId = $("#SearchUserId").val();


    if(targetId == "")
    {
        queryAllUser();
    }
    else
    {
        $.ajax({
            type : "GET",
            url : "http://monkeydoc.liadrinz.cn/rest/user/"+targetId,
            dataType:'json',
            success : function(obj) {
                var table = $("#userTable");
                $("#userTable tr:not(:first)").empty(); 
                
                var rowNum = $("#userTable").find("tr").length;
                var tempNum = rowNum-1;
                var $uid = $("<td id=\'userId"+tempNum+"\'></td>");
                $uid.html(obj.id);
        
                var $username = $("<td id=\'userName"+tempNum+"\'></td>");
                $username.html(obj.userName);
        
                var $tel = $("<td id=\'tel"+tempNum+"\'></td>");
                $tel.html(obj.tel);
        
                var $email = $("<td id=\'email"+tempNum+"\'></td>");
                $email.html(obj.email);
        
                var $password = $("<td id=\'password"+tempNum+"\'></td>");
                $password.html(obj.password);

                var $createTime = $("<td id=\'createTime"+tempNum+"\'></td>");
                $createTime.html(timestampToTime(obj.createTime));
        
                var $user = $("<tr id=\'user"+tempNum+"\'></tr>");
                $user.append($uid);
                $user.append($username);
                $user.append($tel);
                $user.append($email);
                $user.append($password);
                $user.append($createTime);
                table.append($user);
        
                $('#user' + tempNum).on('click','td', function() {
                    $('#userModal').modal('show');
                    $('#deleteUser').css("visibility","visible");
                    var uid = $('#userId' + $('#user' + tempNum).index()).html();
                    var username = $('#userName' + $('#user' + tempNum).index()).html();
                    var tel = $('#tel' + $('#user' + tempNum).index()).html();
                    var email = $('#email' + $('#user' + tempNum).index()).html();
                    $('#InputUserName').attr("value",$.trim(username));
                    $('#InputTel').attr("value",$.trim(tel));
                    $('#InputEmail').attr("value",$.trim(email));
        
                    $('#submitUser').click(function(){
                        username = $('#InputUserName').val();
                        tel = $('#InputTel').val();
                        email = $('#InputEmail').val();
                        var password = $('#InputPassword').val();
        
                        var params = "userName=" + username + "&tel=" + tel + "&email=" + email ;
                        if(password != "")
                            params = params + "&password=" + password;
                        
                        $.ajax({
                            type : "PUT",
                            url : "http://monkeydoc.liadrinz.cn/rest/user/"+uid+"?"+params,
                            dataType : 'json',
                            success : function(data) {
                                $('#userModal').modal('hide');
                                window.location.reload();
                            },
                            error : function(e){
                                console.log(e.status);
                                console.log(e.responseText);
                            }
                        });
        
                    })
        
                    $('#deleteUser').click(function(){
                        $('#warnModal').modal('show');
        
                        $('#confirmDeleteUser').click(function(){
                            $.ajax({
                                type : "delete",
                                url : "http://monkeydoc.liadrinz.cn/rest/user/"+uid,
                                dataType : 'json',
                                success : function(data) {
                                    $('#userModal').modal('hide');
                                    window.location.reload();
                                },
                                error : function(e){
                                    console.log(e.status);
                                    console.log(e.responseText);
                                }
                            });
                        })
        
                    })
        
                  });

                    },
                    error : function(e){
                        console.log(e.status);
                        console.log(e.responseText);
                    }
        });
    }
});

function loadUser(data,table)
{   
    $.each(data,function(idx,obj){
        var rowNum = $("#userTable").find("tr").length;
        var tempNum = rowNum-1;
        var $uid = $("<td id=\'userId"+tempNum+"\'></td>");
        $uid.html(obj.id);

        var $username = $("<td id=\'userName"+tempNum+"\'></td>");
        $username.html(obj.userName);

        var $tel = $("<td id=\'tel"+tempNum+"\'></td>");
        $tel.html(obj.tel);

        var $email = $("<td id=\'email"+tempNum+"\'></td>");
        $email.html(obj.email);

        var $password = $("<td id=\'password"+tempNum+"\'></td>");
        $password.html(obj.password);

        var $createTime = $("<td id=\'createTime"+tempNum+"\'></td>");
        $createTime.html(timestampToTime(obj.createTime));

        var $user = $("<tr id=\'user"+tempNum+"\'></tr>");
        $user.append($uid);
        $user.append($username);
        $user.append($tel);
        $user.append($email);
        $user.append($password);
        $user.append($createTime);
        table.append($user);

        $('#user' + tempNum).on('click','td', function() {
            $('#userModal').modal('show');
            $('#deleteUser').css("visibility","visible");
            var uid = $('#userId' + $('#user' + tempNum).index()).html();
            var username = $('#userName' + $('#user' + tempNum).index()).html();
            var tel = $('#tel' + $('#user' + tempNum).index()).html();
            var email = $('#email' + $('#user' + tempNum).index()).html();
            $('#InputUserName').attr("value",$.trim(username));
            $('#InputTel').attr("value",$.trim(tel));
            $('#InputEmail').attr("value",$.trim(email));

            $('#submitUser').click(function(){
                username = $('#InputUserName').val();
                tel = $('#InputTel').val();
                email = $('#InputEmail').val();
                var password = $('#InputPassword').val();

                var params = "userName=" + username + "&tel=" + tel + "&email=" + email ;
                if(password != "")
                    params = params + "&password=" + password;
                
                $.ajax({
                    type : "PUT",
                    url : "http://monkeydoc.liadrinz.cn/rest/user/"+uid+"?"+params,
                    dataType : 'json',
                    success : function(data) {
                        $('#userModal').modal('hide');
                        window.location.reload();
                    },
                    error : function(e){
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                });

            })

            $('#deleteUser').click(function(){
                $('#warnModal').modal('show');

                $('#confirmDeleteUser').click(function(){
                    $.ajax({
                        type : "delete",
                        url : "http://monkeydoc.liadrinz.cn/rest/user/"+uid,
                        dataType : 'json',
                        success : function(data) {
                            $('#userModal').modal('hide');
                            window.location.reload();
                        },
                        error : function(e){
                            console.log(e.status);
                            console.log(e.responseText);
                        }
                    });
                })

            })

          });
    })

}

function queryAllUser()
{
    $.ajax({
        type : "GET",
        url : "http://monkeydoc.liadrinz.cn/rest/user",
        dataType:'json',
        processData: false,
        success : function(data) {
            var table = $("#userTable");
            $("#userTable tr:not(:first)").empty(); 
            loadUser(data,table);
        },
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function timestampToTime(timestamp) {
    var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = (date.getMinutes() < 10 ? '0'+(date.getMinutes()) : date.getMinutes()) + ':';
    s = (date.getSeconds() < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
    return Y+M+D+h+m+s;
    }