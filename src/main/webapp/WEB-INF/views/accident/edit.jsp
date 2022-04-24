<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<form  action="<c:url value='/update'/>" method='POST'>
    <table>
        <tr>
            <td>id:</td>
            <td><input type='text' name='id' value="${accident.id}"></td>
            <td>name:</td>
            <td><input type='text' name='name'></td>
            <td>text:</td>
            <td><input type='text' name='text'></td>
            <td>address:</td>
            <td><input type='text' name='address'></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>