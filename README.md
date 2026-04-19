Git em! API
===========

The Gitem API takes a Git username and makes to Github API calls.

1. Get User - https://api.github.com/users/{userId}
2. Get User Repos - https://api.github.com/users/{userId}/repos

The results of these two REST calls is combined and put into the GitemUser API format:

```json
{
  "user_name": "octocat",
  "display_name": "The Octocat",
  "avatar": "https://avatars.githubusercontent.com/u/583231?v=4",
  "geo_location": "San Francisco",
  "email": null,
  "url": "https://api.github.com/users/octocat",
  "created_at": "Tue, 25 Jan 2011 18:44:36 GMT",
  "repos": [
    {
      "name": "boysenberry-repo-1",
      "url": "https://api.github.com/repos/octocat/boysenberry-repo-1"
    }
  ]
}
```

## Build

This project use typical Gradle build commands:

Build `./gradlew build`
Test `./gradlew test`

## Run locally

A `docker-compose.yaml` file has been provided in the `docker` directory of this project.
This provides Redis container for local testing.
I prefer docker compose to simple docker build/run because you can have multiple dependencies set up for
local runs. Cache is set to only 5 seconds by default so it can be seen in effect quickly.

`docker compose up -d`

Then you should be able to run the Spring boot services with:

`./gradlew bootRun`

The API can be exercised through SWAGGER UI below or with direct HTTP call:

Health (returns 'OK'): http://localhost:8080/health

Get User: http://localhost:8080/users/octocat
The resource `user` takes the `username` at the end of the path.

## SWAGGER API

Web UI
http://localhost:8080/swagger-ui/index.html

API Docs
http://localhost:8080/v3/api-docs