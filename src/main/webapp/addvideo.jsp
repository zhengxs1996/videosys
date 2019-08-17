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
    <title>layui</title>
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
    <legend>添加视频课程</legend>
</fieldset>

<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-block">
            <input type="text" name="title" lay-verify="title" placeholder="请输入标题" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">简介</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入内容" class="layui-textarea" name="detail" type=""></textarea>
        </div>
    </div>

    <%--<div class="layui-inline">--%>
    <%--<label class="layui-form-label">时长</label>--%>
    <%--<div class="layui-input-inline">--%>
    <%--<input type="text" class="layui-input" id="test-limit3" placeholder="HH:mm:ss" name="time" autocomplete="off">--%>
    <%--</div>--%>
    <%--</div><br><br>--%>

    <div class="layui-form-item">
        <label class="layui-form-label">时长</label>
        <div class="layui-input-block">
            <input type="text" name="time" lay-verify="video_url" placeholder="" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">主讲人</label>
        <div class="layui-input-block">
            <select id="speakers" name="spearkerId" lay-filter="speaker">
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">关联课程表</label>
        <div class="layui-input-block">
            <select id="courses" name="subjectId" lay-filter="course">
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">视频播放地址</label>
        <div class="layui-input-block">
            <input type="text" name="videoUrl" lay-verify="videoUrl" placeholder="" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">封面地址</label>
        <div class="layui-input-block">
            <input type="text" name="imageUrl" lay-verify="imageUrl" placeholder="" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">播放次数</label>
        <div class="layui-input-block">
            <input type="text" name="playNum" lay-verify="playNum" placeholder="" autocomplete="off"
                   class="layui-input">
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
        url: 'findAllSpeaker.do',
        dataType: 'json',
        type: 'get',
        success: function (data) {
            if (data.code == 1) {
                var speakers = data.info;
                $(speakers).each(function () {
                    $("#speakers").append("<option value=\"" + this.id + "\">" + this.speakerName + "</option>");
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

    $.ajax({
        url: 'findAllCourse.do',
        dataType: 'json',
        type: 'get',
        success: function (data) {
            if (data.code == 1) {
                var courses = data.info;
                $(courses).each(function () {
                    $("#courses").append("<option value=\"" + this.id + "\">" + this.courseTitle + "</option>");
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

    //创建一个编辑器
    var editIndex = layedit.build('LAY_demo_editor');

    //自定义验证规则
    form.verify({
        title: function (value) {
            if (value.length <= 0) {
                return '标题不能为空';
            }
        }
        , pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
        , content: function (value) {
            layedit.sync(editIndex);
        }
        , videoUrl: function (value) {
            if (value.length <= 0) {
                return '视频播放地址不能为空';
            }
        }
        , imageUrl: function (value) {
            if (value.length <= 0) {
                return '封面地址不能为空';
            }
        }
        , playNum: function (value) {
            if (value.length <= 0) {
                return '播放次数不能为空';
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
            url: "addVideo.do",
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
