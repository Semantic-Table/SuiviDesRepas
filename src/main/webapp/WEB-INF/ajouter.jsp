<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 29/06/2022
  Time: 10:56
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ajouter</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<form action="AddRepas" method="post">
    <label for="date"></label>
    <input id="date" name="date" type="date" required>
    <label for="time"></label>
    <input id="time" name="time" type="time" required>
    <label for="repas"></label>
    <input id="repas" name="aliment" type="text" required>
    <button type="submit">envoyer</button>
</form>
</body>
</html>
