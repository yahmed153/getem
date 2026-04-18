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

## SWAGGER API

Web UI
`http://localhost:8080/swagger-ui/index.html`

API Docs
`http://localhost:8080/v3/api-docs `