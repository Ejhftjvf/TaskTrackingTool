Here are the backend-specific details for the Task Manager project.

⚙️ Task Manager Backend
This is the complete Spring Boot backend for the Task Manager application. It's a robust RESTful API that handles all business logic, data persistence, and security.

✨ Core Features
Secure RESTful API: Built with Spring Security to handle authentication and protect endpoints using Basic Authentication.

Role-Based Access Control (RBAC): Differentiates between Admin and User roles, serving different data based on the authenticated user's permissions.

Full CRUD Operations: Provides endpoints for creating, reading, updating, and deleting users and tasks.

Data Persistence: Uses Spring Data to seamlessly connect with a database (like MongoDB) for all data storage.

🛠️ Tech Stack
Java 17

Spring Boot 3: For building a robust and secure REST API.

Spring Security: For handling authentication and user roles.

Spring Data JPA / MongoDB: For painless database interaction.

Maven: For managing project dependencies.

🏁 How to Run
You'll need Java (17+) and Maven installed.

Clone the repository:

Bash

git clone <your-repo-url>
Navigate into the project directory:

Bash

cd TaskManager-App
Run the application using Maven:

Bash

mvn spring-boot:run
Your backend server is now live and listening on http://localhost:8080.

🔌 API Endpoints (The Engine Room)
<details>
<summary><strong>Click to view API Endpoints</strong></summary>

User Endpoints (/user)
GET /: Get all users.

GET /{username}: Get a single user by their username.

POST /: Create a new user.

DELETE /{username}: Delete a user.

Task Endpoints (/task)
GET /: Get all tasks (returns all for Admins, only assigned tasks for Users).

GET /{id}: Get a single task by its ID.

POST /: Create a new task.

POST /{taskId}/{username}: Assign an existing task to a user.

DELETE /{id}/{username}: Finish/unassign a task for a specific user.

</details>
