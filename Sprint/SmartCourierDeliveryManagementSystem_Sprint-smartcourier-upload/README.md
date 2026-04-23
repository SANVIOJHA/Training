# SmartCourier Delivery Management System

SmartCourier is a full-stack courier and parcel delivery platform built with a React frontend and Spring Boot microservices backend.

## Stack

- React + Vite frontend in `smartcourier-frontend`
- Spring Cloud Gateway with JWT authentication
- Eureka service discovery
- Spring Cloud Config Server
- RabbitMQ for async delivery events
- Zipkin for distributed tracing
- Swagger/OpenAPI for API exploration
- SonarQube support for quality checks

## Services

| Service | Port |
|---|---:|
| React Frontend | 5173 |
| API Gateway | 8080 |
| Auth Service | 8081 |
| Delivery Service | 8082 |
| Tracking Service | 8083 |
| Admin Service | 8084 |
| Config Server | 8888 |
| Eureka Server | 8761 |
| Zipkin | 9411 |
| SonarQube | 9000 |

## Case Study Coverage

- Customer signup/login
- Landing page service catalog
- Delivery creation wizard with sender, receiver, package, and review data
- Customer delivery dashboard
- Tracking timeline by tracking number
- Parcel document upload
- Delivery proof lookup
- Admin dashboard
- Delivery monitoring and exception resolution
- Reports and analytics
- User and hub management

## Infrastructure

Start local infrastructure with Docker:

```bash
docker compose -f docker-compose.infra.yml up -d
```

This brings up:

- MySQL on `localhost:3306`
- RabbitMQ on `localhost:5672` and `localhost:15672`
- Zipkin on `localhost:9411`
- SonarQube on `localhost:9000`

Default database credentials used by the services:

- Username: `root`
- Password: `tiger`

## Backend Run Order

1. `eureka-server`
2. `config-server`
3. `auth-service`
4. `delivery-service`
5. `tracking-service`
6. `admin-service`
7. `api-gateway`

Compile check for each service:

```bash
./mvnw -q -DskipTests compile
```

Run tests for each service:

```bash
./mvnw -q test
```

## Frontend

Frontend location:

```bash
smartcourier-frontend
```

Run locally:

```bash
cd smartcourier-frontend
npm install
npm run dev
```

The Vite dev server proxies `/gateway/*` calls to `http://localhost:8080`.

## API Entry Points

Gateway base URL:

```text
http://localhost:8080/gateway
```

Important routes:

- `/gateway/services`
- `/gateway/auth/signup`
- `/gateway/auth/login`
- `/gateway/deliveries`
- `/gateway/tracking/{trackingNumber}`
- `/gateway/tracking/documents/upload`
- `/gateway/admin/dashboard`
- `/gateway/admin/deliveries`
- `/gateway/admin/reports`
- `/gateway/admin/users`
- `/gateway/admin/hubs`

## Swagger

- [Auth Service](http://localhost:8081/swagger-ui/index.html)
- [Delivery Service](http://localhost:8082/swagger-ui/index.html)
- [Tracking Service](http://localhost:8083/swagger-ui/index.html)
- [Admin Service](http://localhost:8084/swagger-ui/index.html)

## SonarQube

After SonarQube is running on `http://localhost:9000`, run analysis from a module root or the repository root using the included `sonar-project.properties`.

Examples:

```bash
sonar-scanner
```

```bash
./mvnw sonar:sonar
```
