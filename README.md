# Notes Server Spec

Base URL = https://notesdb-server.herokuapp.com

## Authentication

All API must use this authentication

Request:
- Header :
    - X-Api-Key : "your secret api key"

## Register User
Request :
- Method : POST
- Endpoint : `/v1/users/register`
- Header :
    - Content-Type: application/json
- Body :
```json
{
    "name":"Ujang Burka",
    "email":"jangburka@gmail.com",
    "password":"qwert"
}
```

Response :
```json
{
    "success": "true",
    "message": "{jwt token}"
}
```

## Login User
Request :
- Method : POST
- Endpoint : `/v1/users/login`
- Header :
    - Content-Type: application/json
- Body :
```json
{
    "email":"jangburka@gmail.com",
    "password":"qwerty"
}
```

Response :
```json
{
    "success": "true",
    "message": "{jwt token}"
}
```

## Get all notes
Request :
- Method : GET
- Endpoint : `/v1/notes`
- Header :
    - Content-Type: application/json
    - Authorization : Bearer Token

Response :
```json
{
    "id":"1",
    "noteTitle":"First Note",
    "description":"This is description",
    "date":"121221"
}
```

## Create Notes
Request :
- Method : POST
- Endpoint : `/v1/notes/create`
- Header :
    - Content-Type: application/json
    - Authorization : Bearer Token
- Body :
```json
{
    "id":"1",
    "noteTitle":"First Note",
    "description":"This is description",
    "date":"121221"
}
```

Response :
```json
{
    "success": "true",
    "message": "Note Added Successfully!"
}
```

## Update Notes
Request :
- Method : POST
- Endpoint : `/v1/notes/update`
- Header :
    - Content-Type: application/json
    - Authorization : Bearer Token
- Body :
```json
{
    "id":"1",
    "noteTitle":"First Note",
    "description":"This is description",
    "date":"121221"
}
```

Response :
```json
{
    "success": "true",
    "message": "Update Added Successfully!"
}
```

## Delete Notes
Request :
- Method : DELETE
- Endpoint : `/v1/notes/delete?id={id}`
- Header :
    - Content-Type: application/json
    - Authorization : Bearer Token
    - Query Param :
        - id : string

Response :
```json
{
    "success": "true",
    "message": "Delete Added Successfully!"
}
```
