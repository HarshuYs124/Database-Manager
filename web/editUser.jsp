<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
        }
        input[type="text"], input[type="password"], input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        label {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit User Details</h2>
        <form action="UpdateUserServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" name="username" id="username" value="<%= request.getAttribute("username") %>" readonly /><br>

            <label for="password">Password:</label>
            <input type="password" name="password" id="password" value="<%= request.getAttribute("password") %>" /><br>

            <label for="id">ID:</label>
            <input type="text" name="id" id="id" value="<%= request.getAttribute("id") %>" /><br>

            <label for="product">Product:</label>
            <input type="text" name="product" id="product" value="<%= request.getAttribute("product") %>" /><br>

            <label for="cost">Cost:</label>
            <input type="text" name="cost" id="cost" value="<%= request.getAttribute("cost") %>" /><br>

            <label for="orders">Orders:</label>
            <input type="text" name="orders" id="orders" value="<%= request.getAttribute("orders") %>" /><br>

            <input type="submit" value="Update" />
        </form>

        <a href="show_users.jsp">Cancel</a>
    </div>
</body>
</html>