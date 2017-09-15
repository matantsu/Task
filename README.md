# Solution to programming task

This is a solution to the programming task given by Greenfield Tech. It's simply a clone of https://github.com/guss77/simplest-vertx-web-server.

To run:

```
mvn org.codehaus.mojo:exec-maven-plugin:exec -Dexec.executable=java \
	-Dexec.args="-cp %classpath io.vertx.core.Launcher run test.project1.Server"
```

To test:

```
curl -D- http://localhost:8080/ -d '{"text": "word"}'
```
