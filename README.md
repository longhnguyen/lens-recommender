# Lens Recommender API

A Spring Boot REST API that recommends camera lenses based on camera model, mount compatibility, and intended photography purpose.

---

## Features

- Lens recommendations based on:
    - Camera model & brand
    - Mount compatibility
    - Use case (e.g. portrait, landscape, wildlife)
- Rule-based scoring engine
- PostgreSQL database with Flyway migrations
- Dockerized setup for easy local development

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker & Docker Compose

---

## Getting Started

### Prerequisites

- Docker
- Docker Compose

---

### Run the application

From the project's root directory:
```
docker compose up --build
```
API will be available at:
```
http://localhost:8080
```

---

## API Endpoints
Returns recommended lenses based on input criteria.
### Request sample
```
{
  "cameraBrand": "CANON",
  "cameraModel": "EOS R6 Mark II",
  "purpose": "PORTRAIT"
}
```
### Response sample
```
[
    {
        "name": "Canon RF 85mm f/1.2L USM",
        "focalLengthScore": 10,
        "apertureScore": 10,
        "usabilityScore": 6,
        "totalScore": 26
    },
    ...
]
```

---

## How it works
1. Resolve camera (by model or alias)
2. Filter compatible lenses
3. Score each lens using rule engine:
    - Focal length
    - Aperture
    - Usability
4. Sort and return top matches

---

## Database
- PostgreSQL
- Managed via Flyway migrations:
```
src/main/resources/db/migration
```

---

## Docker setup
Services:
- ```app``` → Spring Boot API
- ```db``` → PostgreSQL

To reset database:
```
docker compose down -v
docker compose up --build
```