## Development


### Deployment

```sh
$ heroku login
$ heroku apps:create [APP]
$ heroku config:set SPRING_PROFILES_ACTIVE=prod
$ heroku config:set TZ=Asia/Tokyo
$ heroku config:set 'JAVA_OPTS=-XX:+UseCompressedOops -XX:+UseStringDeduplication'

# Setup Redis
$ heroku addons:create heroku-redis:hobby-dev

# Deploy JAR file
$ ./gradlew build
$ heroku plugins:install java
$ heroku deploy:jar --jar app/build/libs/app.jar --jdk 17
```

## References
- [GCP 入門 (18) - Google Sheets API](https://note.com/npaka/n/nd522e980d995)


## License
MIT &copy; [Pine Mizune](https://profile.pine.moe/)
