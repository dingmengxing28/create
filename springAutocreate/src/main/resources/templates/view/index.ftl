<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/css/select2.min.css" rel="stylesheet"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/js/select2.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div class="container" style="padding-top: 150px;">


    <div class="form-horizontal">

        <div class="form-group">
            <label for="historys" class="col-sm-offset-1 col-sm-2 control-label">历史记录</label>
            <div class="col-sm-6">
                <select id="historys" style="width: 100%;" onchange="gradeChange()">
                    <option value="---请选择---" selected="selected">---请选择---</option>
                </select>
            </div>
        </div>


        <div class="form-group">
            <label for="className" class="col-sm-offset-1 col-sm-2 control-label">类名<span
                        style="color: red">*</span></label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="className" placeholder="请输入类名">
            </div>
        </div>

        <div class="form-group">
            <label for="authorName" class="col-sm-offset-1 col-sm-2 control-label">作者名<span style="color: red">*</span></label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="authorName" placeholder="请输入作者名">
            </div>
        </div>

        <div class="form-group">
            <label for="note" class="col-sm-offset-1 col-sm-2 control-label">描述<span style="color: red">*</span></label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="note" placeholder="请输入描述">
            </div>
        </div>

        <div class="form-group">
            <label for="submitTypes" class="col-sm-offset-1 col-sm-2 control-label">提交类型<span
                        style="color: red">*</span></label>
            <div class="col-sm-6">
                <select id="submitTypes" style="width: 100%;">
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="centNames" class="col-sm-offset-1 col-sm-2 control-label">中心<span
                        style="color: red">*</span></label>
            <div class="col-sm-6">
                <select id="centNames" style="width: 100%;">
                </select>
            </div>
        </div>

        <div class="form-group">
            <label for="fileTypes" class="col-sm-offset-1 col-sm-2 control-label">文件类型<span
                        style="color: red">*</span></label>
            <div class="col-sm-6">
                <select id="fileTypes" style="width: 100%;">
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-3">
                <button id="del" style="width: 100%;" class="btn btn-warning">删除历史记录</button>
            </div>

            <div class="col-sm-3">
                <button id="save" style="width: 100%;" class="btn btn-info">保存</button>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-6">
                <button id="create" style="width: 100%;" class="btn btn-danger">生成</button>
            </div>
        </div>
    </div>


</div>

<script type="text/javascript">

    // 历史记录选择
    $("#historys").select2({
        ajax: {
            url: '/listKeys',
            dataType: 'json',
            delay: 200,
            data: function (params) {
                var query = {
                    key: params.term
                }
                return query;
            },
            processResults: function (data) {
                var results = [];
                var def = {};
                def.id = "---请选择---";
                results.push(def);
                for (var i = 0; i < data.length; i++) {
                    var datas = {};
                    datas.id = data[i];
                    results.push(datas);
                }
                return {
                    results: results
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) {
            return markup;
        },
        placeholder: "---请选择---",
        templateResult: formatRepo,
        templateSelection: formatRepoSelection
    });

    function formatRepo(repo) {
        if (repo.loading) {
            return;
        }
        var markup = "<div>" + repo.id + "</div>";
        return markup;
    }

    function formatRepoSelection(repo) {
        return repo.id;
    }

    // 改变历史记录选择
    function gradeChange() {
        var value = $("#historys option:selected").val();
        if (value != null && value != "") {
            if (value == "---请选择---") {
                reset();
                $("#fileTypes").val("全部").trigger("change");
                $("#centNames").val("产品中心-product").trigger("change");
                $("#submitTypes").val("BaseQueryService").trigger("change");
            } else {
                // 发送请求
                $.ajax({
                    type: "GET",
                    url: "/listHistory",
                    data: {"key": value},
                    contentType: 'application/json;charset=utf-8', //设置请求头信息
                    success: function (data) {
                        if (data) {
                            var obj = JSON.parse(data)
                            $("#className").val(obj.className);
                            $("#authorName").val(obj.authorName);
                            $("#note").val(obj.note);
                            $("#fileTypes").val(obj.fileType).trigger("change");
                            $("#submitTypes").val(obj.submitType).trigger("change");
                            $("#centNames").val(obj.centName).trigger("change");
                        } else {
                            alert("失败!");
                        }
                    },
                    error: function (data) {
                        alert("失败!");
                    }
                });
            }
        }
    }


    // 文件类型选择
    var fileTypeData = [
        {id: '全部', text: '全部'}, {id: 'dataApiCreate', text: 'dataApiCreate'},
        {id: 'dataRequestCreate', text: 'dataRequestCreate'}, {id: 'dataResponseCreate', text: 'dataResponseCreate'},
        {id: 'serviceApiCreate', text: 'serviceApiCreate'}, {id: 'serviceModelImplCreate', text: 'serviceModelImplCreate'},
        {id: 'dubboCreate', text: 'dubboCreate'}
    ];
    $("#fileTypes").select2({
        data: fileTypeData,
        placeholder: "---请选择---"
    });

    // 提交类型选择
    var submitTypeData = [
        {id: 'BaseQueryService', text: 'BaseQueryService'}, {id: 'TwoPhaseService', text: 'TwoPhaseService'}
    ];
    $("#submitTypes").select2({
        data: submitTypeData,
        placeholder: "---请选择---"
    });

    // 中心名称选择
    var centNameData = [{id: '产品中心-product', text: '产品中心-product'}, {id: '认证中心-authentic', text: '认证中心-authentic'},
        {id: '授权中心-authorized', text: '授权中心-authorized'}, {id: '支付中心-payment', text: '支付中心-payment'},
        {id: '合约中心-contract', text: '合约中心-contract'}, {id: '用户中心-customertic', text: '用户中心-customertic'},
        {id: '收费中心-fee', text: '收费中心-fee'}, {id: '限额中心-limit', text: '限额中心-limit'},
        {id: '营销中心-marketing', text: '营销中心-marketing'}, {id: '消息中心-message', text: '消息中心-message'},
        {id: '订单中心-order', text: '订单中心-order'}, {id: '参数中心-ParamCenter', text: '参数中心-ParamCenter'}
    ];
    $("#centNames").select2({
        data: centNameData,
        placeholder: "---请选择---"
    });

    var className = "";
    var note = "";
    var authorName = "";

    // 点击生成
    $("#create").click(function () {
        if (check()) {
            var fileType = $("#fileTypes").val();
            var centName = $("#centNames").val();
            var submitType = $("#submitTypes").val();
            var model = {};
            model.className = className;
            model.authorName = authorName;
            model.note = note;
            model.centName = centName;
            model.fileType = fileType;
            model.submitType = submitType;
            if (confirm("确定要生成吗?")) {
                // 发送请求
                $.ajax({
                    type: "POST",
                    url: "/autocreteFile",
                    data: JSON.stringify(model),//将对象序列化成JSON字符串
                    contentType: 'application/json;charset=utf-8', //设置请求头信息
                    success: function (data) {
                        if (data) {
                            alert("成功!");
                        } else {
                            alert("失败!");
                        }
                    },
                    error: function (data) {
                        alert("失败!");
                    }
                });
            }
        }
    });

    // 点击保存
    $("#save").click(function () {
        if (check()) {
            var fileType = $("#fileTypes").val();
            var centName = $("#centNames").val();
            var submitType = $("#submitTypes").val();
            var model = {};
            model.className = className;
            model.authorName = authorName;
            model.note = note;
            model.centName = centName;
            model.fileType = fileType;
            model.submitType = submitType;
            if (confirm("确定要保存该条记录吗?")) {
                // 发送请求
                $.ajax({
                    type: "POST",
                    url: "/saveHistory",
                    data: JSON.stringify(model),//将对象序列化成JSON字符串
                    contentType: 'application/json;charset=utf-8', //设置请求头信息
                    success: function (data) {
                        if (data) {
                            alert("成功!");
                        } else {
                            alert("失败!");
                        }
                    },
                    error: function (data) {
                        alert("失败!");
                    }
                });
            }
        }
    });

    // 点击删除
    $("#del").click(function () {
        var value = $("#historys option:selected").val();
        if (value != null && value != "" && value != "---请选择---") {
            if (confirm("确定要删除该条历史记录吗?")) {
                // 发送请求
                $.ajax({
                    type: "GET",
                    url: "/delHistory",
                    data: {"key": value},
                    contentType: 'application/json;charset=utf-8', //设置请求头信息
                    success: function (data) {
                        if (data) {
                            alert("删除成功!");
                            reset();
                            $("#historys").val("---请选择---").trigger("change");
                            $("#fileTypes").val("全部").trigger("change");
                            $("#centNames").val("产品中心-product").trigger("change");
                            $("#submitTypes").val("BaseQueryService").trigger("change");
                        } else {
                            alert("删除失败!");
                        }
                    },
                    error: function (data) {
                        alert("删除失败!");
                    }
                });
            }
        } else {
            alert("请选择要删除的历史记录!");
        }
    });

    // 校验
    function check() {
        // 获取类名
        className = $("#className").val();
        if (className == null || className == "") {
            $("#className").focus();
            alert("请输入类名!");
            return false;
        }

        // 获取作者名
        authorName = $("#authorName").val();
        if (authorName == null || authorName == "") {
            $("#authorName").focus();
            alert("请输入作者名!");
            return false;
        }

        // 获取描述
        note = $("#note").val();
        if (note == null || note == "") {
            $("#note").focus();
            alert("请输入描述!");
            return false;
        }
        return true;
    }

    // 重置
    function reset() {
        $("#className").val("");
        $("#authorName").val("");
        $("#note").val("");
    }
</script>
</body>
</html>


