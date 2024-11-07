Database configuration
1. Log in to the MySQL server using this command then add your password
```
mysql -u root -p
```
2. create the schema 
```
CREATE DATABASE skyrise;
```
3. create the user
```
CREATE USER 'skyrise_admin'@'localhost' IDENTIFIED BY 'skyrise_app_swe26';
GRANT ALL PRIVILEGES ON skyrise.* TO 'skyrise_admin'@'localhost';
FLUSH PRIVILEGES;
```
4. to make sure that the connection to database is right run the database test you can find it in the test folder