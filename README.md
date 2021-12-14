# stkrep &nbsp;[![CircleCI](https://circleci.com/gh/pine/stkrep/tree/main.svg?style=shield)](https://circleci.com/gh/pine/stkrep/tree/main) [![codecov](https://codecov.io/gh/pine/stkrep/branch/main/graph/badge.svg)](https://codecov.io/gh/pine/stkrep)

:bar_chart: Daily stocks reporter

## Requirements
- Java 17

## Libraries
- Spring Boot 2
- jsoup

## Development

### Deployment

```sh
$ heroku login
$ heroku apps:create [APP]
$ heroku config:set SPRING_PROFILES_ACTIVE=prod
$ heroku config:set TZ=Asia/Tokyo
$ heroku config:set 'JAVA_OPTS=-XX:+UseCompressedOops -XX:+UseStringDeduplication -Dlog4j2.formatMsgNoLookups=true'

# Setup Redis
$ heroku addons:create heroku-redis:hobby-dev

# Deploy JAR file
$ ./gradlew build
$ heroku plugins:install java
$ heroku deploy:jar --jar app/build/libs/app.jar --jdk 17
```

## License

MIT &copy; [Pine Mizune](https://profile.pine.moe/)
