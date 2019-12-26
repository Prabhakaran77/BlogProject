<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Session" %>
<%@page import="org.hibernate.*" %>
<%@ page import="org.hibernate.Transaction" %>
<%@ page import="org.springframework.context.annotation.Configuration" %>
<%@ page import="com.fasterxml.classmate.AnnotationConfiguration" %>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>HOME</title>
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
        <c:forEach var="listValue" items="${lists}">
           <div class="row">

               <h1>${listValue.title}</h1>
               <p>${listValue.content}
               <a href="read/${listValue.id}">read more</a></p>

           </div>
        </c:forEach>
        </div>
</main>
</body>
</html>
