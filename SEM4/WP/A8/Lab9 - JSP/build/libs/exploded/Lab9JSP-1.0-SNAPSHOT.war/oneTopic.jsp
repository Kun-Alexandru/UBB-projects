<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Topic Details</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="scriptOneTopic.js"></script>

</head>

<body>

<%
    Integer userId = (Integer) session.getAttribute("userId");
    String username = (String) session.getAttribute("username");

    if(userId == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    else {
%>
<nav>
    <a> Welcome,, <%=username%> </a>
    <%
        }
    %>

    <script>
        var sessionUserId = "<%=userId%>";
    </script>

    <form action="logout-servlet" method="post">
        <button type="submit" name="logout" value="Logout">Logout</button>
    </form>

</nav>

<div>
    <div>
        <h1>Comments</h1>
    </div>
</div>

<div>
    <div>
        <div id="comments-section"></div>
    </div>
</div>

<br>
<br>

<div>
    <div>
        <button type="button" id="add-comment-button">Add a new comment</button>
        <button type="button" onclick="location.href='homepage.jsp'">Go to Homepage</button>
    </div>
</div>

<br>

<div>
    <div>
        <form id="add-comment-form">
            <div>
                <label for="description">Description</label>
                <input type="text" name="description" id="description" placeholder="Enter description">
            </div>

            <button type="button" id="add-comment-button-submit">Add</button>
        </form>
    </div>
</div>

</body>
</html>
