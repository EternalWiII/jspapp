<%-- 
    Document   : score
    Created on : 26 дек. 2023 г., 18:03:06
    Author     : dimay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mycompany.jspapp.Student"%>
<%@page import="com.mycompany.jspapp.Mark"%>

<!DOCTYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            h1 {
              color: #333;
              font-family: 'Arial', sans-serif;
              font-size: 2em;
              margin-bottom: 20px;
            }

            form {
              max-width: 400px; 
              margin: 0 auto;
            }

            form label {
              display: block;
              margin-bottom: 8px; 
              color: #555;
            }

            form input {
              width: 100%; 
              padding: 10px; 
              margin-bottom: 15px;
              box-sizing: border-box; 
            }
            
            .custom-table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            } 

            th {
                background-color: #f2f2f2;
                padding: 10px;
                text-align: left;
                border: 1px solid #ddd;
            }

            tr {
                border: 1px solid #ddd;
            }

            td {
                padding: 10px;
                text-align: left;
            }
        </style>
<body>
        <h1>Scores of <c:out value="${user.getId()}"/> <c:out value="${user.getName()}"/> <c:out value="${user.getSurname()}"/></h1>
        
        <div id="content">
            <table class="list">
                <tr>
                    <th>Discipline</th>
                    <th>Score</th>
                    <th>ECTS</th>
                </tr>
                <c:forEach var="disc" items="${scores}">
                    <tr>
                        <td><c:out value="${disc.getTitle()}"/></td>
                        <td><c:out value="${disc.getMark_num()}"/></td>
                        <td><c:out value="${disc.getMark_let()}"/></td>
                    </tr>
                </c:forEach>
            </table>
    </div>
    </body>