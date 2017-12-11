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
                	<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySupersPage">Supers</a></li>
                        <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrgsPage">Organizations</a></li>
                       <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocsPage">Locations</a></li>
                       <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                </ul>    
            </div>
                <div class="text-center">
                    <a href="${pageContext.request.contextPath}/addSuper">ADD A NEW SUPER</a>
                </div>
                <div class="container col-centered">
                <table class="table table-hover">
                    <thead>
                    <th>Name</th>
                    <th>Description</th>
                    </thead>
                       <c:forEach var="superGuy" items="${superList}">
                       <tr>
                           <td><a href="displaySuperFull?superID=${superGuy.ID}"><c:out value="${superGuy.name}"></c:out></a></td>
                           <td>${superGuy.description}</td>                       
                           <td><a href="${pageContext.request.contextPath}/editSuper?superID=${superGuy.ID}">Edit</a></td>
                           <td><a href="${pageContext.request.contextPath}/deleteSuper?superID=${superGuy.ID}">Delete</a></td>
                       </tr>
                       </c:forEach>
                </table>
                </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
