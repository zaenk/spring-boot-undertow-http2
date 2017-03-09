# Spring Boot HTTP/2 with Undertow

This repository demonstrates Undertow configuration for HTTP/2 for both outbound and inbound connections.

In the `build.gradle` JavaExec tasks are decorated to include the `alpn-boot` JAR on 
the bootstrap classpath.

*In theory* (see [`curl` example](#curl)) HTTP/2 should work between application after enabling protocol upgrade to it 
(a Tomcat 8.5 could do that without the ALPN bootstrap),
but browsers does not supports it on unencrypted channel.

The web server will listen on `8080` (http) and `8443` (https) ports,
the 'test' page is accessible on`/test` path.

## Prerequisites

- Java 8 (jdk1.8.0_111)

## Running the example

- Start applicaiton from the command line
```
$ gradlew bootRun
```
- **NOTICE:** this version of ALPN is compatible with **jdk1.8.0_111**. 
For compatibility list, check out [ALPN documentation](https://www.eclipse.org/jetty/documentation/9.4.x/alpn-chapter.html#alpn-versions).
Get the compatible JAR from any Maven repository, place it in the project root
and update `build.gradle` JavaExec task collection config.
- Check HTTP/2 on [http://localhost:8080/test](http://localhost:8080/test) and
[https://localhost:8443/test](https://localhost:8443/test)

### <a name="curl"></a>Test with `curl`

Well, `curl` test on Windows not produced the results I was hoping:
```
$ curl http://localhost:8080/test --http2 -s | grep inbound | head -1
        <p>inbound http/2: false</p>
$ curl https://localhost:8443/test --http2 -s -k | grep inbound | head -1
        <p>inbound http/2: true</p>
```

I thought `curl` will be able to use HTTP/2 without TLS, but seems like that is not the case.

## Credit
- [Spring Boot with HTTP/2 – Start a server and make REST calls as a client](https://vanwilgenburg.wordpress.com/2016/04/01/spring-boot-http2/)
- [HTTP2 and Spring Boot](http://hoogvliet.de/http2-and-spring-boot)
- [Detect connection protocol (HTTP/2, spdy) from javascript](http://stackoverflow.com/a/42421005)
