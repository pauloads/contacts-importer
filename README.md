# Contacts App

### Run the app 
To run this app you will need Docker and docker-compose on your system. Just execute

`docker-compose up`

All endpoints are protected by JWT, in order to access them you will need to register an user and then login, to do that use the following endpoints:

- `[POST] localhost:8080/users`: Register a new user. You must provide a valid email and password.
```
 {
    "email": "freddie.mercury@koombea.com",
    "password": "pwd#123"
}
//by the way, this is the default user of the app (it will be created on application start)
```

- `[POST] localhost:8080/login`: Using valid email and password, generates a JWT token, which the user can use to get access to the endpoints:
```
{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTY0NDgwMzQ0OSwianRpIjoiY2U0MWU1MTAtYTBkYy00YjQ5LTkxMjMtNGNlODNkOTc2ODUxIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6MSwibmJmIjoxNjQ0ODAzNDQ5LCJleHAiOjE2NDQ4ODk4NDl9.7Xvt0X45yhftMSsVpKeNtepDx_B6idanpnc7yCtiWL9Q"
}
```

- `[POST] localhost:8080/upload`: upload a cvs file and map the fields
```
curl --location --request POST 'localhost:8080/upload' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcmVkZGllLm1lcmN1cnlAa29vbWJlYS5jb20iLCJleHAiOjE2NjAxNDQ3ODN9.gF6O7Y1P4z3bCWe0a0fkaqp3P4EzUV3B22avNiwGs50' \
--form 'file=@"/C:/contacts-importer/csv_files/valid-data.csv"' \
--form 'name="name"' \
--form 'dateOfBirth="dateOfBirth"' \
--form 'phone="phone"' \
--form 'address="address"' \
--form 'creditCardNumber="creditCardNumber"' \
--form 'email="email"'
```

- `[GET] localhost:8080/contacts?page={pagenumber}`: list successfully imported contacts



- `[GET] localhost:8080/contacts/invalid?page={pagenumber}`: list contacts imported with errors


- `[GET] localhost:8080/upload?page={pagenumber}`: log of uploaded files their respective status

### Sample files
- `valid-data.csv`: A valid CSV file which will import all contacts without any errors.
- `invalid-data.csv`: CSV file with invalid fields, import cannot be completed and upload status will be `ERROR`.

This files are located in the `csv_files` folder

### Debits
- background job
- implement refresh token;
- improve test coverage
- implement integration tests
- more csv files for different scenarios
- implement HATEOAS to increase maturity model