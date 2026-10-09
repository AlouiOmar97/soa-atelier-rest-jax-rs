# Atelier REST 1

This project is a Java JAX-RS web service for managing educational data related to students and study options.

## Technologies

- Java 17
- Maven
- JAX-RS / Jersey
- Servlet-based deployment on a WAR package

## Run the project

```bash
mvn clean package
```

Then deploy the generated WAR in a servlet container such as Tomcat, or run it in an IDE configured for Java web applications.

The REST API base URL is:

```text
http://localhost:8080/atelier-rest-1/rest
```

If the application is deployed under a different context path, replace `atelier-rest-1` with your deployment name.

## API endpoints

### Students

- GET `/rest/etudiants` — list all students
- GET `/rest/etudiants/{id}` — get a student by identifier
- POST `/rest/etudiants` — add a student
- PUT `/rest/etudiants/{id}` — update a student
- DELETE `/rest/etudiants/{id}` — delete a student
- GET `/rest/etudiants/option?codeOption={code}` — list students by option code (returns XML)

### Options

- GET `/rest/options` — list all options or filter by `domaine`
- GET `/rest/options/{id}` — get an option by ID
- POST `/rest/options` — add an option
- PUT `/rest/options/{id}` — update an option
- DELETE `/rest/options/{id}` — delete an option

## Example payloads

### Student

```json
{
  "identifiant": "E123",
  "nom": "Ben Ali",
  "prenom": "Sara",
  "anneeEtude": 2,
  "email": "sara.benali@example.com"
}
```

### Option

```json
{
  "codeOption": 101,
  "libelle": "Data Science",
  "domaine": "Informatique",
  "responsable": "Dr. Youssef",
  "credits": 6,
  "semestre": 4,
  "capacite": 30
}
```

## Notes

- Students are exposed as JSON by default.
- The endpoint `/rest/etudiants/option` returns XML output.
- The application registers all JAX-RS resources in `RestActivator` and maps them under `/rest`.
