// Content type 
tests["Content-Type is present"] = postman.getResponseHeader("Content-Type");

// Response time
tests["Response time is less than 200ms"] = responseTime < 200;

// Keys are present
tests["ID is present"] = responseBody.has("id");
tests["Profile Name is present"] = responseBody.has("profileName");
tests["First Name is present"] = responseBody.has("firstName");
tests["Last Name is present"] = responseBody.has("lastName");
tests["Created is present"] = responseBody.has("created");
tests["User is present"] = responseBody.has("user");

// Message ID value check
var jsonData = JSON.parse(responseBody);
tests["Profile ID should be "+ jsonData.id] = jsonData.id === 1;

var schema = {
  "$id": "http://example.com/example.json",
  "type": "object",
  "definitions": {},
  "$schema": "http://json-schema.org/draft-07/schema#",
  "properties": {
    "id": {
      "$id": "/properties/id",
      "type": "integer",
      "title": "The Id Schema ",
      "default": 0,
      "examples": [
        1
      ]
    },
    "profileName": {
      "$id": "/properties/profileName",
      "type": "string",
      "title": "The Profilename Schema ",
      "default": "",
      "examples": [
        "pat7"
      ]
    },
    "firstName": {
      "$id": "/properties/firstName",
      "type": "string",
      "title": "The Firstname Schema ",
      "default": "",
      "examples": [
        "pat"
      ]
    },
    "lastName": {
      "$id": "/properties/lastName",
      "type": "string",
      "title": "The Lastname Schema ",
      "default": "",
      "examples": [
        "pats"
      ]
    },
    "created": {
      "$id": "/properties/created",
      "type": "integer",
      "title": "The Created Schema ",
      "default": 0,
      "examples": [
        1530820265000
      ]
    },
    "user": {
      "$id": "/properties/user",
      "type": "integer",
      "title": "The User Schema ",
      "default": 0,
      "examples": [
        1
      ]
    }
  }
};

tests["Valid schema"] = tv4.validate(jsonData, schema);
console.log("Validation failed: ", tv4.error);
