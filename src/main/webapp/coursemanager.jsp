<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>课程管理</title>
    <link rel="stylesheet" href="layui/css/layui.css" />
    <script type="text/javascript" src="layui/layui.js" ></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
</head>
<body>

<button type="button" class="layui-btn" onclick="add()">
    <i class="layui-icon">&#xe608;</i> 添加
</button>

<table class="layui-hide" id="demo" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
        var laydate = layui.laydate //日期
            ,laypage = layui.laypage //分页
            ,layer = layui.layer //弹层
            ,table = layui.table //表格
            ,element = layui.element //元素操作

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,height: 688
            ,url: 'courseList.do' //数据接口
            ,title: '课程表'
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'id', title: '序号', width:100, fixed: 'left'}
                ,{field: 'courseTitle', title: '标题', width:100}
                ,{field: 'courseDesc', title: '简介', width:200}
                ,{fixed: 'right',title: '操作', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'del'){
                layer.confirm('真的删除么？', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        type:"post",
                        url:"deleteCourse.do",
                        data: {"id":data.id},
                        dataType:"json",
                        success:function (data) {
                            layer.msg(data.info);
                        },
                        error:function () {
                            alert("ajax:error");
                        }
                    });
                });
            } else if(layEvent === 'edit'){
                layer.open({
                    type: 2,
                    title: '编辑',
                    fix: false,
                    maxmin: true,
                    shadeClose: true,
                    shade: 0.8,
                    area: ['1000px', '700px'],
                    content: 'modifycourse.jsp?id=' + data.id,
                    end: function () {
                        location.reload();
                    }
                });
            }
        });

    })
</script>

<script type="text/javascript">
    function add() {
        layer.open({
            type: 2,
            title: '添加课程',
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.8,
            area: ['1000px', '700px'],
            content: 'addcourse.jsp',
            end: function () {
                location.reload();
            }
        });
    }
</script>
</body>
</html>
