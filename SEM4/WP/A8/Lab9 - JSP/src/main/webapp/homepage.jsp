<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Homepage</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="scriptHomepage.js"></script>
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
    <a class="navbar-brand"> Welcome, <%=username%> </a>
    <%
        }
    %>

    <script>
        var sessionUserId = "<%=userId%>";
    </script>

    <%-- Logout button --%>
    <form action="logout-servlet" method="post">
        <button type="submit" class="btn btn-outline-secondary" name="logout" value="Logout">Logout</button>
    </form>

</nav>

<%-- All topics --%>
<div>
    <div>
        <h1>Topics</h1>
        <table id="topics-table" class="table table-striped"></table>
    </div>
</div>

<%-- Add a new topic --%>
<div>
    <div>
        <button type="button" id="add-topic-button" class="btn btn-secondary">Create a new topic</button>
    </div>
</div>

<div>
    <div>
        <form id="add-topic-form">
            <div>
                <label for="name">Name</label>
                <input type="text" name="name" class="form-control" id="name" placeholder="Enter name">
            </div>
            <button type="button" id="add-topic-button-submit" class="btn btn-info">Add</button>
        </form>
    </div>
</div>

</body>
</html>
