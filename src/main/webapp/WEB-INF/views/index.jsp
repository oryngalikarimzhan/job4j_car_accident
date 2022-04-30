<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
              rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
        <title>accident</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="card" style="width: 100%">
                    <div class="card-header" style="display: flex; justify-content: space-between">
                        <div>
                            ACCIDENTS LIST
                        </div>
                        <div>
                            <c:out value="${username}"/>
                            <a href="<c:url value='/logout'/> ">Выйти</a>
                        </div>
                    </div>
                    <div style="margin-top: 10px">
                        <button onclick="window.location.href='<c:url value='/create'/>'" style="width: 100px">
                            Create
                        </button>
                    </div>

                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Text</th>
                                    <th scope="col">Address</th>
                                    <th scope="col">Type</th>
                                    <th scope="col">Rules</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${accidents}" var="accident">
                                    <tr>
                                        <td>
                                            <c:out value="${accident.id}"/>
                                        </td>
                                        <td>
                                            <c:out value="${accident.name}"/>
                                        </td>
                                        <td>
                                            <c:out value="${accident.text}"/>
                                        </td>
                                        <td>
                                            <c:out value="${accident.address}"/>
                                        </td>
                                        <td>
                                            <c:out value="${accident.type.name}"/>
                                        </td>
                                        <td>
                                            <c:forEach items="${accident.rules}" var="rule">
                                                <c:out value="${rule.name}"/>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <button onclick="window.location.href=
                                                    '<c:url value='/edit?id=${accident.id}'/>'"
                                                    style="width: 50px">
                                                Edit
                                            </button>
                                            <button onclick="location.href='<c:url value='/delete?id=${accident.id}'/>'"
                                                    style="width: 70px">
                                                Delete
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
                crossorigin="anonymous"></script>
    </body>
</html>