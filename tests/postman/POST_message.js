// Content type 
tests["Content-Type is present"] = postman.getResponseHeader("Content-Type");

// Response time
tests["Response time is less than 200ms"] = responseTime < 200;

// Keys are present
tests["ID is present"] = responseBody.has("id");
tests["Message is present"] = responseBody.has("message");
tests["Author is present"] = responseBody.has("author");
tests["Created is present"] = responseBody.has("created");
tests["Links are present"] = responseBody.has("links");

// Message ID value check
var jsonData = JSON.parse(responseBody);
tests["Profile ID should be "+ jsonData.profileId] = jsonData.profileId === 1;
tests["Message ID should be "+ jsonData.id] = jsonData.id === 6;

// JSON schema test
// Schema produced with jsonschema.net tool
var schema = {
  "$id": "http://example.com/example.json",
  "type": [
    "object",
    "null"
  ],
  "definitions": {},
  "$schema": "http://json-schema.org/draft-07/schema#",
  "properties": {
    "id": {
      "$id": "/properties/id",
      "type": [
        "integer",
        "null"
      ],
      "title": "The Id Schema ",
      "default": 0,
      "examples": [
        1
      ]
    },
    "message": {
      "$id": "/properties/message",
      "type": [
        "string",
        "null"
      ],
      "title": "The Message Schema ",
      "default": "",
      "examples": [
        "Venmo vice shaman, pour-over chia you probably haven't heard of them hashtag blog."
      ]
    },
    "author": {
      "$id": "/properties/author",
      "type": [
        "string",
        "null"
      ],
      "title": "The Author Schema ",
      "default": "",
      "examples": [
        "john5"
      ]
    },
    "profileId": {
      "$id": "/properties/profileId",
      "type": [
        "integer",
        "null"
      ],
      "title": "The Profileid Schema ",
      "default": 0,
      "examples": [
        2
      ]
    },
    "created": {
      "$id": "/properties/created",
      "type": [
        "integer",
        "null"
      ],
      "title": "The Created Schema ",
      "default": 0,
      "examples": [
        1530821705000
      ]
    },
    "links": {
      "$id": "/properties/links",
      "type": [
        "object",
        "null"
      ],
      "properties": {
        "1": {
          "$id": "/properties/links/properties/1",
          "type": [
            "object",
            "null"
          ],
          "properties": {
            "id": {
              "$id": "/properties/links/properties/1/properties/id",
              "type": [
                "integer",
                "null"
              ],
              "title": "The Id Schema ",
              "default": 0,
              "examples": [
                0
              ]
            },
            "link": {
              "$id": "/properties/links/properties/1/properties/link",
              "type": [
                "string",
                "null"
              ],
              "title": "The Link Schema ",
              "default": "",
              "examples": [
                "http://localhost:8080/jMessage/webapi/messages/1/comments/comments"
              ]
            },
            "rel": {
              "$id": "/properties/links/properties/1/properties/rel",
              "type": [
                "string",
                "null"
              ],
              "title": "The Rel Schema ",
              "default": "",
              "examples": [
                "comments"
              ]
            },
            "messageId": {
              "$id": "/properties/links/properties/1/properties/messageId",
              "type": [
                "integer",
                "null"
              ],
              "title": "The Messageid Schema ",
              "default": 0,
              "examples": [
                1
              ]
            }
          }
        },
        "3": {
          "$id": "/properties/links/properties/3",
          "type": [
            "object",
            "null"
          ],
          "properties": {
            "id": {
              "$id": "/properties/links/properties/3/properties/id",
              "type": [
                "integer",
                "null"
              ],
              "title": "The Id Schema ",
              "default": 0,
              "examples": [
                3
              ]
            },
            "link": {
              "$id": "/properties/links/properties/3/properties/link",
              "type": [
                "string",
                "null"
              ],
              "title": "The Link Schema ",
              "default": "",
              "examples": [
                "http://github.com/JohnSell620/jMessage2"
              ]
            },
            "rel": {
              "$id": "/properties/links/properties/3/properties/rel",
              "type": [
                "string",
                "null"
              ],
              "title": "The Rel Schema ",
              "default": "",
              "examples": [
                "JohnSell620 jMessage2 Github 2"
              ]
            },
            "messageId": {
              "$id": "/properties/links/properties/3/properties/messageId",
              "type": [
                "integer",
                "null"
              ],
              "title": "The Messageid Schema ",
              "default": 0,
              "examples": [
                1
              ]
            }
          }
        }
      }
    }
  }
};
tests["Valid schema"] = tv4.validate(jsonData, schema);
console.log("Validation failed: ", tv4.error);
