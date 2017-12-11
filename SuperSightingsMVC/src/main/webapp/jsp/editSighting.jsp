<%-- 
    Document   : displayLocsPage
    Created on : Nov 1, 2017, 11:21:36 AM
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
            <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocsPage">Locations</a></li>
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
        </ul>    
    </div>
<div class="container">
    <div class ="col-centered">
        <h1>Edit Sighting</h1>
         <div class="form-group">
            <form action="updateSighting"class="form-control" method="post">
                
                Date:<input type="date"class="form-control" value="${sighting.date}" name="date"><br>
                Location:Previously was ${sighting.location.name}<select class="form-control" name="locID">
                    <c:forEach var="loc" items="${locs}">
                        <option value="${loc.ID}">${loc.name}</option>
                    </c:forEach>
                </select>
                Supers: Previously had: <c:forEach var="s" items="${sighting.supers}">${s.name}</c:forEach>(hold control for multiple)<select multiple="multiple"class="form-control" name="superID">
                    <c:forEach var="s" items="${supers}">
                        <option value="${s.ID}">${s.name}</option>
                    </c:forEach></select>
                    <input type="text" class="hidden" value="${sighting.ID}" name="sightID">
                           
                <button type="submit">Submit</button>
            </form>
        </div>
    </div>
        
</div>


    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
