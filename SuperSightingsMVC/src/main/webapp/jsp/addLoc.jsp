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
            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayLocsPage">Locations</a></li>
            <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
        </ul>    
    </div>
<div class="container">
    <div class ="col-centered">
        <h1>Create Location</h1>
        <div class="form-group">
        <form action="createLoc" method="get">
            Name:<input type="text" class="form-control" name="name"><br>
            Address<input type="text"class="form-control" name="address"><br>
            Latitude:<input type="number"class="form-control" value=0.0 step="0.01"  name="latitude"><br>
            Longitude:<input type="number"class="form-control" value = 0.0 step="0.01"  name="longitude"><br>
            <button type="submit">Submit</button>
        </div>
        </form>
    </div>
        
</div>


    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
