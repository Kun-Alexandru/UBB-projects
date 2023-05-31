<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="registerScript.js"></script>
</head>

<body>
<div>
    <div style="height: 600px">
        <form action="register-servlet" method="post">
            <div>
                <label for="username">Username</label>
                <input type="text" name="username" class="form-control" id="username" aria-describedby="emailHelp" placeholder="Enter username">
            </div>

            <div>
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="Enter password">
            </div>

            <div>
                <label for="repeatPassword">Confirm Password</label>
                <input type="password" name="repeatPassword" class="form-control" id="repeatPassword" placeholder="Repeat password">
            </div>

            <button type="submit" id="register-button">Register</button>
        </form>
    </div>
</div>
</body>
</html>
