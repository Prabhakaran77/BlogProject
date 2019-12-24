<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Edit Post</title>
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
    <main>
        <div class="container">
            <div class="row" id="create">
                <h2>${heading}</h2>
            </div>
            <form:form action="${formAction}" modelAttribute="post" method="POST" >
                <div class="form-group">
                    <label for="title">Title</label>
                    <form:input path="title" class="form-control" name="title" id="title" />
                </div>
                <div class="form-group">
                    <label for="post-content">Content</label>
                    <form:textarea path="content" name="post-content" class="form-control" id="post-content" rows="10"></form:textarea>
                </div><br>
                <button type="submit" class="btn">Save</button>
            </form:form>
        </div>
    </main>
    <jsp:include page="js/blog.jsp" />
</body>
</html>
