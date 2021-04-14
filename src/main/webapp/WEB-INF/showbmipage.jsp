<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         List of BMI Entries
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        This is a list of all BMI entries in the database so far:

        <table>
            <thread><th>Id</th><th>BMI</th><th>Højde</th><th>Vægt</th></thread>
            <tr><td></td><td></td><td></td><td></td></tr>
        </table>

    </jsp:body>
</t:genericpage>
