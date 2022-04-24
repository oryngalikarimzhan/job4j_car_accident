<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <div class="card-header">
                        ACCIDENTS LIST
                    </div>
                    <div style="margin-top: 10px">
                        <button onclick="window.location.href='<c:url value='/create'/>'" style="width: 100px">
                            Create
                        </button>
                        <button onclick="window.location.href='<c:url value='/edit'/>'" style="width: 100px">
                            Edit
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