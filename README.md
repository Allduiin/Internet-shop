# Internet-shop  
# Target of project
To understand base functionality of web applications and relational database on MySQL example

# Project Description 
This project represents small online internet-shop which has functions like: 
Registration, Login, Add Product To Cart, Complete Order, etc. 
Users can also read a description of their fulfilled orders. 

# Technologies Used
Tomcat, Servlets, Web Filters, JSP, log4j, JDBC, SQL

# DBMS 
The project closely works with SQL queries and provides options to store data 
in comfortable tables, each table has its own purpose. 

### How to launch project
In order to launch the program you need to have a Tomcat and configure it the next way:

You need to add a "war exploded artifact" and add build configuration. 
Together with this, you need to check URL, in my case, it is: http://localhost:8080/ 
but it can vary 
HTTP port: 8080 
JMX port: 1099 
Click apply and then press the start button.  

Also you must use code from init_db.sql at your sql database if you haven't it you can download MySQL

By default, all registered users have the role of a regular user
if you would like to log in as an admin please press bottom "Add test data to DB" at the menu on views and use this authentification params:  
- Login: Admin  
- Password: 1
