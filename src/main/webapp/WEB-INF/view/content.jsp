<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Post</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/WEB-INF/view/css/index.css">
    <style>
        <%@ include file="css/index.css"%>
    </style>
</head>
<body>

<header>
    <jsp:include page="js/header.jsp" />
</header>
<jsp:include page="js/blog.jsp" />
<main>
    <div class="container">
        <div class="row">
                <h1>${post.title}</h1>
                <p>${post.content}</p>
        </div>
    </div>
    <div class="container">
        <a href="delete/${post.id}">Delete</a>
        <a href="edit/${post.id}">Edit</a>
    </div>
</main>
</body>
</html>
