# Angular Todo App with Spring Boot (Full-Stack)

A simple Angular app to manage a todo list - built to practice Angular fundamentals.

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

## Installation

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

### Database Setup (PostgreSQL)

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

5. Rename the `.env.example` file to `.env` and update it inside `/backend` with these credentials

```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/todosdb
SPRING_DATASOURCE_USERNAME=your_db_username
SPRING_DATASOURCE_PASSWORD=your_db_password
```

6. Run Spring Boot

```
cd backend
mvn spring-boot:run
```

## Project Structure

```
/ (root)
├── backend/         # Spring Boot backend (API + DB)
│   └── .env         # PostgreSQL credentials
├── frontend/        # Angular frontend (UI)
└── README.md
```

## Scripts

`http://localhost:3000`

- `npm start` - Start the Angular dev server
- `mvn spring-boot:run`
