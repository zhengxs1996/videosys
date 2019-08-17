<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" href="layui/css/layui.css" />
    <script type="text/javascript" src="layui/layui.js" ></script>
    <script type="text/javascript" src="js/jquery-1.12.2.min.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">视频管理系统</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${sessionScope.loginUser.username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="exit.do">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul id="menu" class="layui-nav layui-nav-tree"  lay-filter="test">
                <!--<li class="layui-nav-item layui-nav-itemed">-->
                    <!--<a class="" href="javascript:;">所有商品</a>-->
                    <!--<dl class="layui-nav-child">-->
                        <!--<dd><a href="javascript:;">列表一</a></dd>-->
                        <!--<dd><a href="javascript:;">列表二</a></dd>-->
                        <!--<dd><a href="javascript:;">列表三</a></dd>-->
                        <!--<dd><a href="">超链接</a></dd>-->
                    <!--</dl>-->
                <!--</li>-->
                <!--<li class="layui-nav-item">-->
                    <!--<a href="javascript:;">解决方案</a>-->
                    <!--<dl class="layui-nav-child">-->
                        <!--<dd><a href="javascript:;">列表一</a></dd>-->
                        <!--<dd><a href="javascript:;">列表二</a></dd>-->
                        <!--<dd><a href="">超链接</a></dd>-->
                    <!--</dl>-->
                <!--</li>-->
                <!--<li class="layui-nav-item"><a href="">云市场</a></li>-->
                <!--<li class="layui-nav-item"><a href="">发布商品</a></li>-->
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe name="myframe" width="100%" height="100%" frameborder="0"></iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;
        //动态菜单信息
        $.ajax({
            type:"get",
            url:"menu.do",
            dataType:"json",
            success:function(data){
                $(data).each(function(){
                    var html = '<li class="layui-nav-item">';
                    html += '<a class="" href="javascript:;">' + this.pname + '</a>';
                    html += '<dl class="layui-nav-child">';

                    var cmenu = this.cmenu;
                    $(cmenu).each(function(){
                        html += '  <dd><a href="' + this.url + '" target="myframe">' + this.cname + '</a></dd>';
                    })

                    html += '</dl>';
                    html += '</li>';
                    $("#menu").append($(html));

                });
                // 重新渲染
                element.render();
            }
        })
    });
</script>
</body>
</html>