var contentType = "application/json; charset=utf-8"

window.onload = function(){  
    queryAllDoc();
} ;

$("#showAllDoc").click(function(){
    queryAllDoc();
});


$("#addNewDoc").click(function(){
    $('#addNewDocModal').modal('show');

    $('#createNewDoc').click(function(){
        var userId = $('#InputCreator').val();
        var docName = $('#InputNewDocName').val();
        

        if(docName == "" || userId == "")
        {
            alert("所有选项不可为空!");
            return;
        }

        params = {};
        params.userId = userId;
        params.mdName = docName;
        
        
        $.ajax({
            type : "POST",
            contentType: contentType,
            url : "http://monkeydoc.liadrinz.cn/mvc/document/createDoc",
            data : JSON.stringify(params),
            dataType : 'json',
            success : function(data) {
                $('#addNewDocModal').modal('hide');
                window.location.reload();
            },
            error : function(e){
                console.log(e.status);
                console.log(e.responseText);
            }
        });

    })
});


$("#searchDoc").click(function(){
    var targetName = $("#SearchDocName").val();
    var targetRecycled = $("#SearchIsRecycled").val();

    var params = {};
    if(targetName != "")
        params.mdName = targetName;
    if(targetRecycled != "null")
        params.recycled = targetRecycled;



    if($.isEmptyObject(params))
    {
        queryAllDoc();
    }
    else
    {
        $.ajax({
            type : "GET",
            url : "http://monkeydoc.liadrinz.cn/rest/meta",
            data : params,
            dataType:'json',
            success : function(data) {
                var table = $("#docTable");
                $("#docTable tr:not(:first)").empty(); 
                loadDoc(data,table);
            },
            error : function(e){
                console.log(e.status);
                console.log(e.responseText);
            }
        });
    }
});

$("#searchDocByid").click(function(){
    var targetId = $("#SearchDocId").val();


    if(targetId == "")
    {
        queryAllDoc();
    }
    else
    {
        $.ajax({
            type : "GET",
            url : "http://monkeydoc.liadrinz.cn/rest/meta/"+targetId,
            dataType:'json',
            success : function(obj) {
                var table = $("#docTable");
                $("#docTable tr:not(:first)").empty(); 
                
                var rowNum = $("#docTable").find("tr").length;
                var tempNum = rowNum-1;
                var $did = $("<td id=\'docId"+tempNum+"\'></td>");
                $did.html(obj.id);
        
                var $mdName = $("<td id=\'docName"+tempNum+"\'></td>");
                $mdName.html(obj.mdName);
        
                var $recycled = $("<td id=\'recycled"+tempNum+"\'></td>");
                $recycled[0].innerHTML = obj.recycled;
        
                var $createTime = $("<td id=\'createTime"+tempNum+"\'></td>");
                $createTime.html(timestampToTime(obj.createTime));
        
                var $updateTime = $("<td id=\'updateTime"+tempNum+"\'></td>");
                $updateTime.html(timestampToTime(obj.updateTime));
        
                var $doc = $("<tr id=\'doc"+tempNum+"\'></tr>");
                $doc.append($did);
                $doc.append($mdName);
                $doc.append($recycled);
                $doc.append($createTime);
                $doc.append($updateTime);
                table.append($doc);
        
                $('#doc' + tempNum).on('click','td', function() {
                    $('#docModal').modal('show');
                    var did = $('#docId' + $('#doc' + tempNum).index()).html();
                    var mdName = $('#docName' + $('#doc' + tempNum).index()).html();
                    var recycled = $('#recycled' + $('#doc' + tempNum).index()).html();
                    $('#InputDocName').attr("value",$.trim(mdName));
                    $('#InputRecycled').val(recycled);
                    
                    $('#submitDoc').click(function(){
                        mdName  = $('#InputDocName').val();
                        recycled = $('#InputRecycled').val();
        
                        var params = "mdName=" + mdName + "&recycled=" + recycled;
                        
                        $.ajax({
                            type : "PUT",
                            url : "http://monkeydoc.liadrinz.cn/rest/meta/"+did+"?"+params,
                            dataType : 'json',
                            success : function(data) {
                                $('#docModal').modal('hide');
                                window.location.reload();
                            },
                            error : function(e){
                                console.log(e.status);
                                console.log(e.responseText);
                            }
                        });
        
                    })
        
                    $('#deleteDoc').click(function(){
                        $('#warnModal').modal('show');
        
                        $('#confirmDeleteDoc').click(function(){
                            $.ajax({
                                type : "delete",
                                url : "http://monkeydoc.liadrinz.cn/rest/meta/"+did,
                                dataType : 'json',
                                success : function(data) {
                                    $('#docModal').modal('hide');
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

function loadDoc(data,table)
{   
    $.each(data,function(idx,obj){
        var rowNum = $("#docTable").find("tr").length;
        var tempNum = rowNum-1;
        var $did = $("<td id=\'docId"+tempNum+"\'></td>");
        $did.html(obj.id);

        var $mdName = $("<td id=\'docName"+tempNum+"\'></td>");
        $mdName.html(obj.mdName);

        var $recycled = $("<td id=\'recycled"+tempNum+"\'></td>");
        $recycled[0].innerHTML = obj.recycled;

        var $createTime = $("<td id=\'createTime"+tempNum+"\'></td>");
        $createTime.html(timestampToTime(obj.createTime));

        var $updateTime = $("<td id=\'updateTime"+tempNum+"\'></td>");
        $updateTime.html(timestampToTime(obj.updateTime));

        var $doc = $("<tr id=\'doc"+tempNum+"\'></tr>");
        $doc.append($did);
        $doc.append($mdName);
        $doc.append($recycled);
        $doc.append($createTime);
        $doc.append($updateTime);
        table.append($doc);

        $('#doc' + tempNum).on('click','td', function() {
            $('#docModal').modal('show');
            var did = $('#docId' + $('#doc' + tempNum).index()).html();
            var mdName = $('#docName' + $('#doc' + tempNum).index()).html();
            var recycled = $('#recycled' + $('#doc' + tempNum).index()).html();
            $('#InputDocName').attr("value",$.trim(mdName));
            $('#InputRecycled').val(recycled);
            
            $('#submitDoc').click(function(){
                mdName  = $('#InputDocName').val();
                recycled = $('#InputRecycled').val();

                var params = "mdName=" + mdName + "&recycled=" + recycled;
                
                $.ajax({
                    type : "PUT",
                    url : "http://monkeydoc.liadrinz.cn/rest/meta/"+did+"?"+params,
                    dataType : 'json',
                    success : function(data) {
                        $('#docModal').modal('hide');
                        window.location.reload();
                    },
                    error : function(e){
                        console.log(e.status);
                        console.log(e.responseText);
                    }
                });

            })

            $('#deleteDoc').click(function(){
                $('#warnModal').modal('show');

                $('#confirmDeleteDoc').click(function(){
                    $.ajax({
                        type : "delete",
                        url : "http://monkeydoc.liadrinz.cn/rest/meta/"+did,
                        dataType : 'json',
                        success : function(data) {
                            $('#docModal').modal('hide');
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

function queryAllDoc()
{
    $.ajax({
        type : "GET",
        url : "http://monkeydoc.liadrinz.cn/rest/meta",
        dataType:'json',
        processData: false,
        success : function(data) {
            var table = $("#docTable");
            $("#docTable tr:not(:first)").empty(); 
            loadDoc(data,table);
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