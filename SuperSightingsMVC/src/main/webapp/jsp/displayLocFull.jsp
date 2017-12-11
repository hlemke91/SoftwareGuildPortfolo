<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
          <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">  
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>HOrD --${location.name} </title>
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
                <li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/displaySupersPage">Supers</a></li>
                <li role="presentation" ><a href="${pageContext.request.contextPath}/displayOrgsPage">Organizations</a></li>
                <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayLocsPage">Locations</a></li>
                <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
            </ul>    
        </div>
            
            <div class="container text-center">
                <h1>${location.name}<br></h1>
                <p>Address:${location.address}</p>
                <p>Latitude:${location.latitude}</p>
                <p>Longitude:${location.longitude}</p>
                <h3>Supers Sighted Here:</h3>
                <c:if test="${supersHere.size()<1}">
                    <p>None...yet.</p>
                </c:if>
                <c:forEach var="superGuy" items="${supersHere}">
                    <p>${superGuy.name}</p>
                </c:forEach>
            </div>
                    
                
            </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
