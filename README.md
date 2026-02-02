# School Sports & Clubs Management System

Full-stack system for managing school sports, clubs, houses, and events in Botswana.

## Folder structure

```
/backend    Spring Boot API
/frontend   React + Vite client
/docker-compose.yml  PostgreSQL database
```

## Backend setup

1. Start PostgreSQL:

```
docker compose up -d
```

2. Run the Spring Boot API:

```
cd backend
mvn spring-boot:run
```

API docs:

```
http://localhost:8080/swagger-ui
```

### Default credentials

```
Email: admin@school.local
Password: Admin123!
```

## Frontend setup

```
cd frontend
npm install
npm run dev
```

Open:

```
http://localhost:5173
```

## Notes

- Access tokens are short-lived; the frontend attempts refresh automatically.
- Update the JWT secret in `backend/src/main/resources/application.yml` before production.
