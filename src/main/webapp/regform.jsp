<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.mycompany.jspapp.Student"%>

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

            .custom-table th {
                background-color: #f2f2f2;
                padding: 10px;
                text-align: left;
                border: 1px solid #ddd;
            }

            .custom-table tr {
                border: 1px solid #ddd;
            }

            .custom-table td {
                padding: 10px;
                text-align: left;
            }
        </style>
    </head>
    
    
        <h1>Servlet</h1>
        <form method = "post" action="StudentAdd">
        <table>
        <tbody>
            <tr>
                <td><label for="name">Name</td>
                <td><input id="name" type="text" name="name"></td>
            </tr>
            <tr>
                <td><label for="surname">Surname</td>
                <td><input id="surname" type="text" name="surname"></td>
            </tr>
            <tr>
                <td><label for="email">Email</td>
                <td><input id="email" type="email" name="email"></td>
            </tr>
            <tr>
                <td><label for="group">Group</td>
                <td><input id="group" type="text" name="group"></td>
            </tr>
            <tr>
                <td><label for="faculty">Faculty</td>
                <td><input id="faculty" type="text" name="faculty"></td>
            </tr>
        </tbody>
        </table>
        <input type="submit" name="send" value="Send">
        </form>

        <% List<Student> students = (List<Student>)application.getAttribute("students"); %>
        <c:if test="${students.size() > 0}">
        <table class = "custom-table">
            <tr>
                <th> Name </th>
                <th> Surname </th>
                <th> Email </th>
                <th> Group </th>
                <th> Faculty </th>
            </tr>
            
            <c:forEach var = "student" items="${students}">
                <tr>
                    <td> <c:out value="${student.getName()}" /> </td>
                    <td> <c:out value="${student.getSurname()}" /> </td>
                    <td> <c:out value="${student.getEmail()}" /> </td>
                    <td> <c:out value="${student.getGroup()}" /> </td>
                    <td> <c:out value="${student.getFaculty()}" /> </td>
                    <td>
                        <a href="/jspapp/MarksCheck?id2=${student.getId()}">scores</a>
                    </td>
                </tr>
            </c:forEach>    
        </table>
        </c:if>
    
    

