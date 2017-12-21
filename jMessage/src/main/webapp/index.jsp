<html>
<body>
    <h2>RESTful API for web messaging!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">JohnSell620.github.io</a>
    for more about me.
    
    <br>
    
	<form action="/webapi/LoginResource">
		<div class="container">
			<label><b>Username</b></label>
			<input type="text" placeholder="Enter Username" name="uname" required>
			
			<label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="psw" required>
			
			<button type="submit">Login</button>
			<input type="checkbox" checked="checked"> Remember me
		</div>
		
		<div class="container" style="background-color:#f1f1f1">
			<button type="button" class="cancelbtn">Cancel</button>
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>
	</form> 
</body>
</html>
