<%-- 
    Document   : displaySuperFull
    Created on : Nov 4, 2017, 5:08:43 PM
    Author     : Hayden
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title><c:out value="${superPerson.name}"></c:out>--Info</title>
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
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySupersPage">Supers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrgsPage">Organizations</a></li>
                       <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocsPage">Locations</a></li>
                       <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>
                <div class="container text-center col-centered">
                    <div class="text-center">
                        <a href="${pageContext.request.contextPath}/addSuper">ADD A NEW SUPER</a>
                </div>
                
                <h1>${superhero.name}</h1>
                <p>${superhero.description}</p>
                <h3>Powers</h3>
                <p>${superhero.superPower}</p>
                <table class="table table-bordered col-md-8 text-center col-centered">
                    <thead>
                    <th>Locations Sighted</th>
                    <th>Member of</th>
                    </thead>
                    <tr>
                        <td><c:forEach var="loc" items="${locs}">${loc.name}<br></c:forEach></td>
                        <td><c:forEach var="org" items="${orgs}">${org.name}<br></c:forEach></td>
                    </tr>
                </table>
                </div>
                


        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>