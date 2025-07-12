# Angular Todo App with Spring Boot (Full-Stack)

A simple Angular app to manage a todo list - built to practice Angular and Spring Boot full stack web dev fundamentals.

## Features

- Display todos fetched from a mock JSON server
- Mark todos as completed
- Add new todos
- Delete todos
- Basic error handling and user feedback

## Tech Stack

- Angular (standalone components)
- TypeScript
- SASS
- Spring Boot
- PostgreSQL

## Getting Started

### Prerequisites

- Node.js (v16+)
- npm
- Java Maven Spring Boot
- PostgreSQL
- Docker

## Local Dev Installation Guide

### Frontend Setup (Angular)

1. Clone the repo:

```bash
git clone https://github.com/asbaig/angular-todo-app.git
cd frontend
```

2. Install dependencies:

```bash
npm install
```

3. Run the Angular app:

```bash
npm start
```

4. Open your browser at `http://localhost:4200`

### Backend Setup (Spring Boot & PostgreSQL)

1. If you're using Linux you can install it with:

```
sudo apt update
sudo apt install postgresql postgresql-contrib
```

2. Start the PostgreSQL service

```
sudo service postgresql start
```

3. Login to PostgreSQL CLI as the default user

```
sudo -u postgres psql
```

4. Create a user and database

```
CREATE USER your_db_username WITH PASSWORD 'your_db_password';
CREATE DATABASE todosdb;
GRANT ALL PRIVILEGES ON DATABASE todosdb TO your_db_username;
\q
```

5. Create your own `.env` file by copying `.env.example`

```
cd backend
cp .env.example .env
```

6. Update it with these credentials

```
DB_URL=jdbc:postgresql://localhost:5432/todosdb
DB_USER=your_db_username
DB_PASS=your_db_password

JWT_SECRET=your_jwt_secret
```

7. Run Spring Boot

```
cd backend
mvn spring-boot:run
```

## Scripts

`http://localhost:3000`

- `npm start` - Start the Angular dev server
- `mvn spring-boot:run` - Run the Spring Boot backend locally

## Docker Setup

### Environment Variables

This project uses environment variables to manage sensitive information like database credentials and JWT secrets.

- Never commit your real `.env` files containing secrets
- Use the provided `.env.example` file in the root directory as a template with placeholder values.
- Create your own `.env` file by copying `.env.example`

```
cp .env.example .env
```

- Edit `.env` and update the variables to your environment:

```
DB_NAME=yourdb
DB_USER=youruser
DB_PASS=yourpass
JWT_SECRET=your_jwt_secret_here
```

- For local backend development (outside Docker), create a `.env` file inside the `backend` folder with your local variables:

```
DB_URL=jdbc:postgresql://localhost:5432/todosdb
DB_USER=your_db_username
DB_PASS=your_db_password
JWT_SECRET=your_local_jwt_secret
```

Make sure your IDE or run scripts load these variables when running the backend locally.

### Running with Docker

To run the entire app with Docker, ensure your `.env` file is in the project root with the correct environment variables, then run:

```
docker-compose --env-file .env up --build
```

This will:

- Start a Postgres container initialized with your database, user, and password
- Build and start the backend container with environment variables injected
- Build and start the frontend container serving your Angular app on port 4200

### Managing Your Database Volume

If you change database credentials or want to reset the database, remove the existing volume and rebuild:

```
docker-compose down -v
docker-compose --env-file .env up --build
```
