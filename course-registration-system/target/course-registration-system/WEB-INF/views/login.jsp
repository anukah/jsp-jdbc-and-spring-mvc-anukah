<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Login</title></head>
<body>
<h2>Login</h2>
<form method="post" action="login">
    Email: <input type="text" name="email" required /><br/>
    Password: <input type="password" name="password" required /><br/>
    <button type="submit">Login</button>
</form>
<p style="color:red;"><c:if test="${not empty error}">${error}</c:if></p>
</body>
</html>