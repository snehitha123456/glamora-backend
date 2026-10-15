# Glamora API

Java 17 Spring Boot API for the Glamora storefront. The service uses MySQL and reads all production credentials from environment variables.

## Render deployment

Create a Render Blueprint from this repository. Render uses `render.yaml` and the Dockerfile in this repository root. The Docker build runs:

```sh
mvn -B -DskipTests clean package
```

The container start command is defined by the Dockerfile and runs:

```sh
java -jar /app/app.jar
```

Provide `SPRING_DATASOURCE_URL` as `jdbc:mysql://HOST:PORT/DATABASE?sslMode=REQUIRED`, plus `SPRING_DATASOURCE_USERNAME` and `SPRING_DATASOURCE_PASSWORD`, in Render's private environment settings. The Blueprint generates `APP_AUTH_SECRET` and `APP_ADMIN_API_KEY` and sets `CORS_ALLOWED_ORIGINS` to the current Vercel site. Never commit credentials.

## API

- `GET /api/products` and `GET /api/products/{id}` are public catalogue reads.
- `POST /api/auth/register` and `POST /api/auth/login` return signed bearer tokens.
- `GET /api/auth/me`, `POST /api/orders`, and `GET /api/orders/my` require a bearer token.
- Product `POST`, `PUT`, and `DELETE` requests require the `X-Admin-Key` header with the Render `APP_ADMIN_API_KEY` value.
