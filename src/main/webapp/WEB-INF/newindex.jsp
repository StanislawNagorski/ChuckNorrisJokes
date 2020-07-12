<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 12.07.2020
  Time: 09:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Mamy wszystkie istotne dowcipy</title>
</head>
<body>
<header>
    <img alt=" image from Chuck Norris Jokes Api" src="resources/img/chucknorris_logo.png" width="360">
    <h2> Mamy wszystkie dowcipy świata</h2>
    <h4>wszystkie istotne</h4>
</header>
<main>
    <h4> Czy wiesz że:</h4>
    <p>

        <c:out value="${requestScope.randomJoke}"/>
    </p>
    <h4> Lista categori dowcipów: </h4>
    <p>
        <c:forEach var="category" items="${requestScope.categoriesList}">
            <a href="JokeFromCategoryServlet/${category}"> = ${category} = </a>
        </c:forEach>
    </p>
</main>
</body>
</html>