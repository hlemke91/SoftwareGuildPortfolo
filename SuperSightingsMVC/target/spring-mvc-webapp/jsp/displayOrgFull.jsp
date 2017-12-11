<%-- 
    Document   : displaySupersPage
    Created on : Oct 26, 2017, 3:38:22 PM
    Author     : Hayden
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>HOrD -- Supers</title>
    </head>
    <body>
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
                	<li role="presentation"><a href="${pageContext.request.contextPath}/">Home</a></li>
                	<li role="presentation" ><a href="${pageContext.request.contextPath}/displaySupersPage">Supers</a></li>
                        <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayOrgsPage">Organizations</a></li>
                       <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocsPage">Locations</a></li>
                       <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>
                <div class="container"
                <div class="text-center">
                     <div class="text-center"><a href="${pageContext.request.contextPath}/addOrg">CREATE A NEW ORGANIZATION</a></div>
                </div>
                <div class="container col-centered text-center">
                    <h1>${org.name}</h1>
                    <h3>${org.hq.name}</h3>
                    <p>${org.description}</p>
                    <h2>Roster</h2>
                    <c:forEach var="superhero" items="${org.roster}">
                        <p>${superhero.name}</p>
                    </c:forEach>
                </div>
    </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
