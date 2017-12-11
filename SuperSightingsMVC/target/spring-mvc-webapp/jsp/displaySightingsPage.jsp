<%-- 
    Document   : displaySightingsPage
    Created on : Nov 1, 2017, 11:22:09 AM
    Author     : Hayden
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>HOrD -- Organizations</title>
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
                	<li role="presentation"><a href="${pageContext.request.contextPath}/displaySupersPage">Supers</a></li>
                        <li role="presentation" ><a href="${pageContext.request.contextPath}/displayOrgsPage">Organizations</a></li>
                       <li role="presentation" ><a href="${pageContext.request.contextPath}/displayLocsPage">Locations</a></li>
                       <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>
                <div class="container">
                    <div class="text-center col-centered">
                        <a href="${pageContext.request.contextPath}/addSighting">ADD A SIGHTING</a>
                    </div>
                    <table class="table table-hover col-centered  text-center">
                        <thead>
                        <th>Date</th>
                        <th>Location</th>
                        <th>Supers</th>
                        </thead>
                        <c:forEach var="sighting" items="${sightings}">
                            <tr>
                                <td>${sighting.date}</td>
                                <td>${sighting.location.name}</td>
                                <td><c:forEach var="s" items="${sighting.supers}"> ${s.name} | </c:forEach></td>
                                <td><a href="${pageContext.request.contextPath}/editSighting?sightingID=${sighting.ID}">Edit</a></td>
                                <td><a href="${pageContext.request.contextPath}/deleteSighting?sightingID=${sighting.ID}">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>


        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
