<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
</head>
<body>
<div>
    <form action="/search.json">
        <input type="text" name="skey" id="skey" /><input type="submit" value="搜索">
    </form>
    <table>

    </table>
</div>
</body>
</html>


