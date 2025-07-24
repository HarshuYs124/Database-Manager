<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Products</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
             background-image: url("https://th.bing.com/th/id/OIP._rRyPI15gMtu36B2q8cAugHaF7?w=248&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7");
            background-repeat: no-repeat;
            background-size: 100%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #5cb85c;
            color: white;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .action-buttons input {
            margin-right: 5px; /* Add spacing between buttons */
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>All Users</h2>

        <table>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>ID</th>
                <th>Product</th>
                <th>Orders</th>
                <th>Cost</th>
                <th>Actions</th>
                
            </tr>  
            <%
                // Get the ResultSet from the request attribute
                ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");

                if (resultSet != null) {
                    boolean hasRecords = false; // Flag to check if any records exist

                    try {
                        // Loop through the ResultSet to display each record's data
                        while (resultSet.next()) {
                            hasRecords = true;
            %>
            <tr>
                <td><%= resultSet.getString("username") %></td>
                <td><%= resultSet.getString("password") %></td>
                <td><%= resultSet.getString("id") %></td>
                <td><%= resultSet.getString("product") %></td>
                <td><%= resultSet.getString("cost") %></td>
                <td><%= resultSet.getString("orders") %></td>
                <td class="action-buttons">
                    <!-- Edit Record Form -->
                    <form action="EditProductServlet" method="post" style="display:inline;">
                        <input type="hidden" name="username" value="<%= resultSet.getString("username") %>">
                        <input type="hidden" name="password" value="<%= resultSet.getString("password") %>">
                        <input type="hidden" name="id" value="<%= resultSet.getString("id") %>">
                        <input type="hidden" name="product" value="<%= resultSet.getString("product") %>">
                        <input type="hidden" name="cost" value="<%= resultSet.getString("cost") %>">
                        <input type="hidden" name="orders" value="<%= resultSet.getString("orders") %>">
                        <input type="submit" value="Edit">
                    </form>

                    <!-- Delete Record Form -->
                    <form action="DeleteProductServlet" method="post" style="display:inline;">
                        <input type="hidden" name="username" value="<%= resultSet.getString("username") %>">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
            <%
                        }
                    } catch (SQLException e) {
                        // Display error if something goes wrong
                        out.println("<tr><td colspan='7'>Error retrieving records: " + e.getMessage() + "</td></tr>");
                    }

                    if (!hasRecords) { // If no records were found
            %>
            <tr>
                <td colspan="7">No records found.</td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="7">Error: ResultSet is null.</td>
            </tr>
            <%
                }
            %>
        </table>

        <a href="dashboard.html" style="margin-top: 20px; display: block; text-align: center;">Back to Dashboard</a>
    </div>
</body>
</html>
