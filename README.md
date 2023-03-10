# spring-to-do-list-demo
To Do List CRUD API Demo
data-jpa, postgresql, lombok, springdoc-openapi dependencies are used in this project.
There are 5 tables which are Task, User, Task Category, Task Comment and Task Attachment. All tables will have an object at initilazion.
All tables have some relations among them. For example, Task Entity keeps all saved comments and attachments, you can get them from a Task Contreller method.  
You can run this project on your localhost with some configiruation on application.properties, you can test API endpoints on Postman or on http://localhost:{yourHost}/swagger-ui/index.html with swagger.
Also you can find import models which you can use on Postman on swagger site. 
