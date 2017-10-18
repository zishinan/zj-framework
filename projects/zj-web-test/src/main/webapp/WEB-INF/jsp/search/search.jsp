<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="funcs" uri="/WEB-INF/funcs.tld" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit"/>
    <meta name="Keywords" content=""/>
    <meta name="Description" content=""/>
    <title>超级搜索</title>
    <link rel="stylesheet" type="text/css" href="${funcs:getResourcePath()}/css/bootstrap-table.min.css">
    <jsp:include page="../common/meta.jsp" flush="true"/>

    <script type="text/javascript" src="${funcs:getResourcePath()}/js/bootstrap-table.min.js"></script>
    <script type="text/javascript" src="${funcs:getResourcePath()}/js/bootstrap-table-mobile.min.js"></script>
    <script type="text/javascript" src="${funcs:getResourcePath()}/js/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" src="${funcs:getResourcePath()}/js/bootstrap-table-demo.js"></script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="text-center">
                        <h2>
                            <span class="text-navy">欢迎使用超级搜索</span>
                        </h2>
                    </div>
                    <br>
                    <div class="search-form">
                        <form action="index.html" method="get">
                            <div class="input-group">
                                <input type="text" placeholder="请输入搜索内容。 注意：由于该搜索过于强大，请勿输入违反法律规定的内容！" name="search" class="form-control input-lg">
                                <div class="input-group-btn">
                                    <button class="btn btn-lg btn-primary" type="submit">
                                        搜索
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="search-result">
                        <table data-toggle="table" data-card-view="true" data-mobile-responsive="true">
                            <thead>
                            <tr>
                                <th data-field="name">名称</th>
                                <th data-field="url">下载连接</th>
                                <th data-field="license">赞</th>
                                <th data-field="description">怼</th>
                                <th data-field="url">举报</th>
                                <th data-field="url">标记</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>百度一下，你就完了</td>
                                <td>www.baidu.com</td>
                                <td>zan</td>
                                <td>dui</td>
                                <td>tousu</td>
                                <td>yikan</td>
                            </tr>
                            <tr>
                                <td>百度一下，你就完了</td>
                                <td>www.baidu.com</td>
                                <td>zan</td>
                                <td>dui</td>
                                <td>tousu</td>
                                <td>yikan</td>
                            </tr>
                            <tr>
                                <td>百度一下，你就完了</td>
                                <td>www.baidu.com</td>
                                <td>zan</td>
                                <td>dui</td>
                                <td>tousu</td>
                                <td>yikan</td>
                            </tr>
                            <tr>
                                <td>百度一下，你就完了</td>
                                <td>www.baidu.com</td>
                                <td>zan</td>
                                <td>dui</td>
                                <td>tousu</td>
                                <td>yikan</td>
                            </tr>
                        </table>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="text-center">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-lg">获取更多精彩内容</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>


