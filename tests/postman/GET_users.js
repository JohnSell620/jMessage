// Content type 
tests["Content-Type is present"] = postman.getResponseHeader("Content-Type");

// Response time
tests["Response time is less than 200ms"] = responseTime < 200;

// Keys are present
tests["ID is present"] = responseBody.has("id");
tests["Username is present"] = responseBody.has("username");
tests["Password is present"] = responseBody.has("password");
tests["Profile ID is present"] = responseBody.has("pId");
tests["Profile is present"] = responseBody.has("profile");


