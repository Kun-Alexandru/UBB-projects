<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>

<body>
<div>
    <div style="height: 600px">
        <form action="login-servlet" method="post">
            <div>
                <label for="username">Username</label>
                <input type="text" name="username" class="form-control" id="username" aria-describedby="emailHelp" placeholder="Enter username">
            </div>

            <div>
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="Enter password">
            </div>

            <button type="submit" class="btn btn-primary">Login</button>
        </form>
    </div>
</div>
</body>
</html>
