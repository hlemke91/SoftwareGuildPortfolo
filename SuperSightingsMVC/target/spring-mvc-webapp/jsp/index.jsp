<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>HOrD Home</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <img src ="${pageContext.request.contextPath}/resources/Herobadge.png" width="35%"/>
                </div>
                <div class="col-md-9 text-justify">
                    <h1>Hero Organization Database</h1>
                </div>
                <hr/>
            </div>
            <hr/>
        </div>
        <div class="navbar">
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/displaySupersPage">Supers</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrgsPage">Organizations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocsPage">Locations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
            </ul>    
        </div>
        <div class="container text-center" text-alignment=center>
            <img src ="${pageContext.request.contextPath}/resources/Herobadge.png" width="35%"/>
            <h2>Welcome to the Hero Organization Database</h2>
            <p>
                By helping Hero Organization track super sightings, you assist in ranking our heros (and villains!) in order to more efficiently assign supers to respond to appropriate disasters.
            <form action="addSighting" method="get">
                <input type="submit" class="button btn-danger" value="REPORT A SIGHTING" 
                       name="Submit" id="frm1_submit" />
            </form>
        </p>
    </div>
    <table class="table table-hover">
        <thead>
        <th>Date</th>
        <th>Location</th>
        <th>Supers</th>
    </thead>
    <c:forEach var="sighting" items="${topfive}">
        <tr>
            <td>${sighting.date}</td>
            <td>${sighting.location.name}</td>
            <td><c:forEach var="s" items="${sighting.supers}"> ${s.name} |</c:forEach></td>
            </tr>
    </c:forEach>
</table>
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

