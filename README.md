# Notes Server Spec

## Authentication

Base URL = https://notesdb-server.herokuapp.com

- Authenticated Route : false

## Register User
Request :
- Method : POST
- Endpoint : `/v1/users/register`
- Body :
```json
{
    "name":"John",
    "email":"qwerty@gmail.com",
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
- Body :
```json
{
    "email":"qwerty@gmail.com",
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

Response :
```json
{
    "success": "true",
    "message": "Delete Added Successfully!"
}
```
