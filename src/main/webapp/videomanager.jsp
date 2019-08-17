<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>视频管理</title>
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
</head>
<body>



<form class="layui-form" onsubmit="return false;">
    <div class="demoTable" style="margin-top: 15px;margin-left: 40px">
        搜索：
        <div class="layui-inline">
            <input class="layui-input" name="keyWord" id="keyWord" autocomplete="off">
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">讲师选择框</label>
            <div class="layui-input-inline">
                <select id="speakerId" name="speakerId" lay-verify="required" lay-search="">
                    <option value="">直接选择或搜索选择</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">课程选择框</label>
            <div class="layui-input-inline">
                <select id="courseId" name="courseId" lay-verify="required" lay-search="">
                    <option value="">直接选择或搜索选择</option>
                </select>
            </div>
        </div>
        <button class="layui-btn" data-type="reload">搜索</button>
    </div>
</form>

<table class="layui-hide" id="demo" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['form', 'laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function () {
        var laydate = layui.laydate //日期
            , laypage = layui.laypage //分页
            , layer = layui.layer //弹层
            , table = layui.table //表格
            , element = layui.element; //元素操作

        var countNum;

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            , height: 688
            , url: 'videoList.do' //数据接口
            , title: '用户表'
            , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']//自定义分页布局
                , limits: [2, 5, 10, 15]
            }
            , toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: '序号', width: 100, fixed: 'left'}
                , {field: 'title', title: '名称', width: 200}
                , {field: 'detail', title: '介绍', width: 300, sort: true}
                , {field: 'speaker_name', title: '讲师', width: 100, sort: true}
                , {field: 'time', title: '时长(秒)', width: 100, sort: true}
                , {field: 'play_num', title: '播放次数', width: 100, sort: true}
                , {fixed: 'right', title: '操作', width: 165, align: 'center', toolbar: '#barDemo'}
            ]]
            , id: 'contenttable'
            , done: function (res) {
                // countNum = res.count;
            }

        });

        var $ = layui.jquery, active = {
            getCheckData: function () {//获取选中数据
                var checkStatus = table.checkStatus('contenttable')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
            , getCheckLength: function () {//获取选中数目
                var checkStatus = table.checkStatus('contenttable')
                    , data = checkStatus.data;
                layer.msg('选中了：' + data.length + ' 个');
            }
            , isAll: function () {//验证是否全选
                var checkStatus = table.checkStatus('contenttable');
                layer.msg(checkStatus.isAll ? '全选' : '未全选')
            }
            , parseTable: function () {
                table.init('parse-table-demo', {
                    limit: 3
                });
            }
            , add: function () {
                table.addRow('test')
            }
            , delete: function () {
                layer.confirm('确认删除吗？', function (index) {
                    table.deleteRow('test')
                    layer.close(index);
                });
            }
            , reload: function () {
                var keyWord = $("#keyWord").val();
                var speakerId = $("#speakerId option:selected").val();
                var courseId = $("#courseId option:selected").val();
                table.reload('contenttable', {
                    method: 'post',
                    where: {keyWord: keyWord, speakerId:speakerId, courseId: courseId}
                });
            }
        };
        $('i').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $.ajax({
            url: 'findAllSpeaker.do',
            dataType: 'json',
            type: 'get',
            success: function (data) {
                if (data.code == 1) {
                    var speakers = data.info;
                    $(speakers).each(function () {
                        $("#speakerId").append("<option value=\"" + this.id + "\">" + this.speakerName + "</option>");
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
                        $("#courseId").append("<option value=\"" + this.id + "\">" + this.courseTitle + "</option>");
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

        //监听头工具栏事件
        table.on('toolbar(test)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id)
                , data = checkStatus.data; //获取选中的数据
            switch (obj.event) {
                case 'add':
                    // layer.msg('添加');
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.open({
                            type: 2,
                            title: '添加',
                            fix: false,
                            maxmin: true,
                            shadeClose: true,
                            shade: 0.8,
                            area: ['1000px', '700px'],
                            content: 'addvideo.jsp',
                            end: function () {
                                location.reload();
                            }
                        });
                    });
                    break;
                case 'update':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else if (data.length > 1) {
                        layer.msg('只能同时编辑一个');
                    } else {
                        // layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
                        layer.open({
                            type: 2,
                            title: '编辑',
                            fix: false,
                            maxmin: true,
                            shadeClose: true,
                            shade: 0.8,
                            area: ['1000px', '700px'],
                            content: 'modifyvideo.jsp?id=' + checkStatus.data[0].id,
                            end: function () {
                                location.reload();
                            }
                        });
                    }
                    break;
                case 'delete':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else {
                        var checkStatus = table.checkStatus('contenttable'),
                            data = checkStatus.data;
                        var id = [];
                        for (var i in data) {
                            id.push(data[i].id);
                        }
                        layer.confirm('确认删除" ' + id.length + ' "行数据么？', function (index) {
                            //向服务端发送删除指令
                            $.ajax({
                                type: "post",
                                url: "deleteAll.do",
                                data: {id: id},
                                dataType: "json",
                                success: function (data) {
                                    layer.msg(data.info);
                                    // table.reload()
                                    $(".layui-laypage-btn").click();
                                    //获取当前页
                                    var recodePage = $(".layui-laypage-skip .layui-input").val();
                                    //获取当前页条数
                                    var recodeLimit = $(".layui-laypage-limits").find("option:selected").val();
                                    // 总条数
                                    var recodeCount = $(".layui-laypage-count").text();
                                    recodeCount = recodeCount.replace(/[^0-9]/ig, "");
                                    // 总页数
                                    var pageCount = Math.ceil(recodeCount / recodeLimit);
                                    // alert("当前页：" + recodePage);
                                    // alert("当前页默认显示条数：" + recodeLimit);
                                    // alert("总条数：" + recodeCount);
                                    // alert("总页数：" + pageCount);
                                    // alert("最后一页删除后剩下的条数：" + ((recodeCount- id.length) % recodeLimit));
                                    // alert("删除的条数：" + id.length);
                                    if (recodePage == pageCount && (recodeCount - id.length) % recodeLimit == 0) {
                                        table.reload('contenttable', {page: {curr: recodePage - 1}});
                                    }
                                },
                                error: function () {
                                    alert("ajax:error");
                                }
                            });
                        });
                    }
                    break;
            }
            ;
        });

        //监听行工具事件
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                , layEvent = obj.event; //获得 lay-event 对应的值
            if (layEvent === 'detail') {
                var description = "";
                for (var i in data) {
                    description += i + " = " + data[i] + "\n";
                }
                layer.msg(description);
            } else if (layEvent === 'del') {
                layer.confirm('真的删除么？', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        type: "post",
                        url: "deleteVideo.do",
                        data: {"id": data.id},
                        dataType: "json",
                        success: function (data) {
                            layer.msg(data.info);
                            //获取当前页
                            var recodePage = $(".layui-laypage-skip .layui-input").val();
                            //获取当前页条数
                            var recodeLimit = $(".layui-laypage-limits").find("option:selected").val();
                            // 总条数
                            var recodeCount = $(".layui-laypage-count").text();
                            recodeCount = recodeCount.replace(/[^0-9]/ig, "");
                            // 总页数
                            var pageCount = Math.ceil(recodeCount / recodeLimit);
                            if (recodePage == pageCount && (recodeCount - 1) % recodeLimit == 0) {
                                table.reload('contenttable', {page: {curr: recodePage - 1}});
                            }
                        },
                        error: function () {
                            alert("ajax:error");
                        }
                    });
                });
            } else if (layEvent === 'edit') {
                layer.open({
                    type: 2,
                    title: '编辑',
                    fix: false,
                    maxmin: true,
                    shadeClose: true,
                    shade: 0.8,
                    area: ['1000px', '700px'],
                    content: 'modifyvideo.jsp?id=' + data.id,
                    end: function () {
                        location.reload();
                    }
                });
            }
        });

    })
</script>
</body>
</html>
