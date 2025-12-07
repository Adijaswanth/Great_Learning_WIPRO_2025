# API Gateway (Wipro-style) - README

This project is a simple **API Gateway** implemented as a Spring Boot application (MVC-based) that forwards requests to downstream microservices.
It includes a minimal static frontend (in `src/main/resources/static`) to demo login/register and calling protected endpoints through the gateway.

## Defaults used
- Gateway runs on: `http://localhost:8080/`
- Routes (configurable in `application.yml`):
  - user-service -> http://localhost:8081
  - song-service -> http://localhost:8082
  - playlist-service -> http://localhost:8083
  - admin-service -> http://localhost:8084
  - notification-service -> http://localhost:8090
- JWT secret: `VerySecretKeyForDev12345` (change in application.yml)
- Public endpoints: `/api/users/login`, `/api/users/register`, static frontend and swagger

## How to run
1. Make sure your downstream microservices are running and reachable (user/song/playlist/admin/notification).
2. Import this as a Maven project in Eclipse (File > Import > Existing Maven Projects) or use command line:
   ```bash
   mvn -U clean package
   mvn spring-boot:run
   ```
3. Open the frontend at `http://localhost:8080/` or `http://localhost:8080/static/index.html`

## Notes
- The gateway simply forwards paths under `/api/users/**`, `/api/songs/**`, `/api/playlists/**`, `/api/admin/**`, `/api/notify/**` to configured downstream base URLs.
- For protected endpoints, include `Authorization: Bearer <token>` header; the JwtFilter validates the token before forwarding.
- The gateway forwards request bodies and headers (including Authorization) to downstream services.
- This implementation is intentionally simple to be easy to run in Eclipse and understand; for production-grade API Gateway use Spring Cloud Gateway (reactive) or Kong/NGINX with proper resilience (circuit-breaker, retries), centralized configs, and TLS.

## Changing configuration
Edit `src/main/resources/application.yml` to change downstream URLs or jwt secret/expiry.

## Troubleshooting
- If you see `401 Missing or invalid Authorization header` when loading protected pages, either login through `/static/user-login.html` or `/static/admin-login.html` which POST to /api/users/login (forwarded to user-service).
- If you prefer to host frontend in another microservice, copy `src/main/resources/static/*` into that service's resources and update fetch endpoints to gateway or direct services.
