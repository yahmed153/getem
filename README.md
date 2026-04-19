Git em! API
===========

Ok, well this has been a fun little project that I could spend MUCH more time on, but I won't.
But I WILL tell you the additional things I would have done as I make it fully production ready
in my opinion. Hopefully I've done enough to give you an idea the type of developer than I am.

To Do:
* Pagination - Right now we just grab the first page of results (default 30). Pagination should be pretty easy to do.
* Dig In For Specifics In API - Right now we get alot of fields we don't need. With some more research I'd see if there is something to call (for repos especially) where we can specify the fields we want instead of boiling the ocean for two fields.
* Retry with Exponential Backoff and Jitter - Maybe using built in stuff the WebFlux
* More varied status codes - For now I'm just handling NOT_FOUND then blame GitHub, not good long term. If we successfully get the user base info but fail on repos, 206 Partial Content with no caching might be a good option to simply failing.
* Virtual Threads - Haven't used these yet and Project Reactor maybe unnecessary, but I'm familiar with it already
* Docker - Should be easy to make a Dockerfile for this project but didn't seem to add much for this
* Perf Testing - Obviously...nuff said
* More Unit Tests - I tend to write too many tests then delete some but stuck. I've also used TDD but with AI that less necessary. Lately I've been embracing fast prototyping then add tests to make sure it does everything I want. Technically coverage is pretty high for the small number of tests I wrote although coverage statistics are just numbers.
* Better Version Handling - Didn't spend much time on build.gradle and to avoid the current vulnerability in Jackson tools 3.1.0 just threw version (3.1.2) in gradle.properties.
* Validate username - I'm leaning on Spring and @Cacheable a bit here. Its possible someone could try to cause problems that I haven't considered yet with this. These libraries are pretty good a protecting but probably would spend some time throwing lots of different crap at it as part of perf testing.
* Understand @Cacheable More - Haven't actually used this annotation before because I wasn't allowed to or wasn't using Spring so my usage is pretty naive right now, but I assumed you would judge if I wrote my own "CachedService" wrapper.
* Spring Actuator - Use this library or something that tells us if the Redis cache is healthy so we don't spam GitHub. Perhaps we have a poor person's backup that doesn't depend on Redis just in case.



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

### FYI...you already know this but...

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

