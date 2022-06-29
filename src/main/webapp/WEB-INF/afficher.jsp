<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 29/06/2022
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Afficher</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<table>

    <tr>
        <td>DATE :</td>
        <td>HEURE : </td>
        <td>ALIMENTS :</td>
    </tr>
<c:forEach items="${repas}" var="repa">
    <tr>
    <td><c:out value="${repa.date}"/></td><td><c:out value="${repa.time}" /></td>

        <td>
            <c:forEach items="${aliments}" var="aliment">
                <c:if test="${repa.ID_repas == aliment.ID_repas}">
                    <c:out value="${aliment.nom}" />,
                </c:if>
            </c:forEach>
        </td>
    </tr>
</c:forEach>
</table>
</body>
</html>
