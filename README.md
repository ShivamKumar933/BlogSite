# BlogSite

## Overview
BlogSite is a web application built using Spring Boot that allows users to create, read, update, and delete blogs. It also features an AI-powered summarization feature using OpenAI API.

## Features
- Create, Read, Update, and Delete blogs
- Fetch all blogs or a specific blog by ID
- AI-powered text summarization using OpenAI API
- RESTful API endpoints

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- OpenAI API
- REST API

## Installation and Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/ShivamKumar933/BlogSite.git
   ```
2. Navigate to the project directory:
   ```sh
   cd BlogSite
   ```
3. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/blogsite
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Add OpenAI API Key in `application.properties`:
   ```properties
   openai.api.key=your-api-key
   ```
5. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
### Blog APIs
- **Create a Blog**
  ```http
  POST /blog/create
  ```

- **Get All Blogs**
  ```http
  GET /blog/getAll
  ```

- **Get a Blog by ID**
  ```http
  GET /blog/get?id={blogId}
  ```

- **Update a Blog**
  ```http
  PUT /blog/update?id={blogId}
  ```


- **Delete a Blog**
  ```http
  DELETE /blog/delete?id={blogId}
  ```

### Summarization API
- **Summarize a Blog**
  ```http
  POST /blog/summarize?id={blogId}
  ```


