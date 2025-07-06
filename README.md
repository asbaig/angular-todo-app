# Angular Todo App

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
 - json-server (mock-backend)

## Getting Started

### Prerequisites

 - Node.js (v16+)
 - npm

### Installation

1. Clone the repo:
```bash
git clone https://github.com/asbaig/angular-todo-app.git
cd angular-todo-app
```

2. Install dependencies:
```bash
npm install
```

3. Start the mock JSON server:
```bash
npm run json-server
```

4. Run the Angular app:
```bash
npm start
```

5. Open your browser at `http://localhost:4200`

## Project Structure
 - `src/app/components` - Angular components
 - `src/app/services` - Angular services
 - `src/app/models` - TypeScript interfaces/models
 - `db.json` - Mock backend data

## Scripts
 - `npm run json-server` - Start the mock backend on `http://localhost:3000`
 - `npm start` - Start the Angular dev server

## Notes
 - This project uses Angular’s standalone components and provideHttpClient() for HTTP services.
 - Error handling is implemented to notify users of API failures.
 - SASS is used for styling components.