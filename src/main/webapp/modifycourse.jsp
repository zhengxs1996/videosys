<%--
  Created by IntelliJ IDEA.
  User: xiangsheng
  Date: 2019/8/3
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加讲师</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加课程</legend>
</fieldset>

<form class="layui-form" lay-filter="example" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">所属学科</label>
        <div class="layui-input-block">
            <select id="subjects" name="subjectId" lay-filter="subjects">
            </select>
        </div>
    </div>

    <input type="hidden" name="id">
    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block">
            <input type="text" name="courseTitle" lay-verify="courseTitle" placeholder="请输入标题" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">简介</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="courseDesc" lay-verify="courseDesc"></textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
layui.use(['form', 'layedit', 'laydate', 'upload', 'layer'], function () {
    var form = layui.form
        , layer = layui.layer
        , layedit = layui.layedit
        , laydate = layui.laydate;

    $.ajax({
        url: 'findAllSubject.do',
        dataType: 'json',
        type: 'get',
        async:false,
        success: function (data) {
            if (data.code == 1) {
                var courses = data.info;
                $(courses).each(function () {
                    $("#subjects").append("<option value=\"" + this.id + "\">" + this.subjectName + "</option>");
                });
                layui.form.render("select");
            } else {
                layer.msg(data.info);
            }
        },
        error: function () {
            layer.msg("ajax:error");
        }
    })

    //页面加载
    $.ajax({
        type:"post",
        url:"findCourseById.do",
        data: {"id":${param.id}},
        dataType:"json",
        async:false,
        success:function (data) {
            if (data.code == 1) {
                form.val('example',data.info);
            } else {
                alert(data.info);
            }
        },
        error:function () {
            alert("ajax:error");
        }
    });

    //自定义验证规则
    form.verify({
        courseTitle: function (value) {
            if (value.length <= 0) {
                return '标题不能为空';
            }
        }
        , courseDesc: function (value) {
            if (value.length <= 0) {
                return '简介不能为空';
            }
        }

    });

    //监听提交
    form.on('submit(demo1)', function (data) {
        // layer.alert(JSON.stringify(data.field), {
        //     title: '最终的提交信息'
        // });
        $.ajax({
            type: "post",
            url: "modifyCourse.do",
            data: data.field,
            dataType: "json",
            success: function (data) {
                if (data.code == 1) {
                    layer.alert(data.info, function () {
                        window.parent.location.reload();//刷新父页面
                        parent.layer.close(index);//关闭弹出层
                    });
                    // window.parent.location.reload();
                    // parent.layer.close(parent.layer.getFrameIndex(window.name));
                } else {
                    layer.msg(data.info);
                }
            },
            error: function (data) {
                alert("ajax:error");
            }
        });
        return false;
    });

});
</script>
</body>
</html>
