# Blog Web App
This is a blog web application developed using Spring Boot and Spring Data JPA. The application uses MySQL as the database and Hibernate as the ORM. Spring Security is used for authentication and authorization of users and JWT authentication is implemented to ensure secure communication. Java Mail is used for email functionality in the application.

### Getting Started
To run the application, you will need to have the following installed on your system :
- Java 11 or higher
- MySQL server

**To get started with the project, follow these steps :**
- Clone the repository to your local machine using the command:
_git clone https://github.com/sayedxali/Blog_
-Navigate to the project directory:
_cd blog_
- Configure the database settings in _src/main/resources/application.properties_.
- Run the application using the command :
_./mvnw spring-boot:run_
- The application can be accessed at http://localhost:8080.

### Features
The blog web application includes the following features :

- User authentication and authorization using Spring Security
- JWT authentication for secure communication
- CRUD operations for managing posts
- User registration and account activation using email verification
- User profile management
- API Endpoints

The following API endpoints are available:

### Future Improvements
Some possible improvements for the application are:

- Implementing search functionality for posts
- Adding comment functionality to posts
- Activate user account using activation code sent to email

### Endpoint	Description
| Method | Method URL |
| ---------------|----------------|
| POST  | /api/user Create a new user account  |
| GET  | /api/v1/auth/login	Authenticate user and generate JWT token  |
| GET  | /api/post	Get a list of all posts (you can also provide the pagination and sorting in the url!) |
| GET  | /api/post/{postId}	Get details of a specific post  |
| POST | /api/post	Create a new post |
| PUT  | /api/post/{postId}	Update an existing post |
| DELETE | /api/post/{postId}	Delete a post |
and figure out the rest by yourself 😉
