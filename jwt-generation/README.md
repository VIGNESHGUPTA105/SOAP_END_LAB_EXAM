# JWT Generation API (Spring Boot / STS)

A small Maven Spring Boot project for the JWT-generation task in the supplied sheet.

## Requirements
- JDK 17 or later
- Spring Tool Suite (STS 4)
- Internet connection for Maven to download dependencies

## Import into STS
1. Extract `jwt-generation-sts.zip`.
2. Open STS.
3. Select **File → Import → Maven → Existing Maven Projects**.
4. Browse to the extracted `jwt-generation` folder.
5. Select `pom.xml`, then click **Finish**.
6. Wait for Maven dependencies to finish downloading.
7. Right-click the project → **Run As → Spring Boot App**.

## Run from a terminal
From the project folder:

```bash
mvn spring-boot:run
```

## Swagger UI
Open:

`http://localhost:8080/swagger-ui.html`

Expand **POST /api/jwt/generate**, click **Try it out**, and submit:

```json
{
  "username": "rohith",
  "role": "USER"
}
```

The API returns a signed JWT, token type, and expiry time. The token contains:
- `sub`: username
- `role`: role claim
- `iat`: issued-at time
- `exp`: expiration time (one hour after issue)

## Test with curl

```bash
curl -X POST http://localhost:8080/api/jwt/generate \
  -H "Content-Type: application/json" \
  -d "{\"username\":\"rohith\",\"role\":\"USER\"}"
```

## Important security note
This is a classroom demonstration of token generation. The endpoint accepts the username and role supplied in the JSON body; it does **not** authenticate the caller or verify that the caller is allowed to request that role. In a real microservice, protect this endpoint with your authentication system and derive the username/role from the authenticated principal, not from untrusted client input.

The fallback signing secret in `application.properties` is for local practice only. For a real deployment, set a private Base64-encoded secret in the `JWT_SECRET` environment variable, keep it outside source control, and rotate it securely.
