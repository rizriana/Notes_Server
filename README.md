# Notes Server Spec

## Authentication

BASE_URL = https://notesdb-server.herokuapp.com

Request:
- Authorization : Bearer Token

## Register User
Request :
- Method : POST
- Endpoint : `/v1/users/register`
- Body :
```json
{
    "name":"string",
    "email":"string",
    "password":"string"
}
```

Response :
```json
{
    "success": boolean,
    "message": "string"
}
```

## Login User
Request :
- Method : POST
- Endpoint : `/v1/users/login`
- Body :
```json
{
    "email":"string",
    "password":"string"
}
```

Response :
```json
{
    "success": boolean,
    "message": "string"
}
```

## Get all notes
Request :
- Method : GET
- Endpoint : `/v1/notes`

Response :
```json
{
        "id": "string",
        "noteTitle": "string",
        "description": "string",
        "date": "date"
    }
```

## Create Notes
Request :
- Method : POST
- Endpoint : `/v1/notes/create`
- Body :
```json
{
    "id":"string",
    "noteTitle":"string",
    "description":"string",
    "date":"date"
}
```

Response :
```json
{
    "success": boolean,
    "message": "string"
}
```

## Update Notes
Request :
- Method : POST
- Endpoint : `/v1/notes/update`
- Body :
```json
{
    "id":"string",
    "noteTitle":"string",
    "description":"string",
    "date":"date"
}
```

Response :
```json
{
    "success": boolean,
    "message": "string"
}
```

## Delete Notes
Request :
- Method : DELETE
- Endpoint : `/v1/notes/delete?id={id}`

Response :
```json
{
    "success": boolean,
    "message": "string"
}
```
