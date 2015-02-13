<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2/12/2015
  Time: 12:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
  </head>
  <body>
  <h2>BOOKS!</h2>
    <form id="addingBook" action="">
        <input type="text" id="name" value="" placeholder="Name">
        <input type="text" id="author" value="" placeholder="Author">
        <input type="text" id="isbn" value="" placeholder="ISBN">
        <input type="button" onclick="addBook();" value="Add Book">
        <input type="text" id="bookId" value="" placeholder="ID Book">
        <input type="button"  onclick="getBook();" value="Get Book">
    </form>
  <script type="text/javascript" src="resources/js/script.js"></script>

  </body>
</html>
