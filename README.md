# stkrep &nbsp;[![CircleCI](https://circleci.com/gh/pine/stkrep/tree/main.svg?style=shield)](https://circleci.com/gh/pine/stkrep/tree/main) [![codecov](https://codecov.io/gh/pine/stkrep/branch/main/graph/badge.svg)](https://codecov.io/gh/pine/stkrep)

:bar_chart: Daily stocks reporter

## Requirements
- Java 17

## Libraries
- Spring Boot 2
- jsoup

## Development
### Run with Docker

```
$ docker build -t app .
$ docker run -it app
```

### Deployment
The following commands are examples of using [flyctl](https://fly.io/docs/hands-on/install-flyctl/) to deploy this application to [Fly.io](https://fly.io/).

```sh
$ fly auth login
$ fly launch
$ fly deploy
```

## License

MIT &copy; [Pine Mizune](https://profile.pine.moe/)
