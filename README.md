# stkrep &nbsp;[![CircleCI](https://circleci.com/gh/pine/stkrep/tree/main.svg?style=shield)](https://circleci.com/gh/pine/stkrep/tree/main) [![codecov](https://codecov.io/gh/pine/stkrep/branch/main/graph/badge.svg)](https://codecov.io/gh/pine/stkrep)

:bar_chart: Daily stocks reporter

## Requirements
- Java 17

## Libraries
- Spring Boot 2
- jsoup

## Development
### Create Jasypt password
Use `pwgen` to generate a strong password.

```shell
$ brew install pwgen
$ pwgen -s 64
```

### Encrypt credentials with Jasypt
To encrypt plain text with Jasypt, please use the command below.

(Replace `~/opt/jasypt` according to your environment.)

```sh
$ ~/opt/jasypt/bin/encrypt.sh \
    algorithm=PBEWITHHMACSHA512ANDAES_256 \
    ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator \
    password=<password> \
    input=<input>
```

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
$ fly secrets set JASYPT_ENCRYPTOR_PASSWORD=<password>
$ fly secrets set SLACK_WEBHOOK_URI=<webhook_uri>
$ fly deploy
```

## License

MIT &copy; [Pine Mizune](https://profile.pine.moe/)
