# foo-flux-service

Reactive Service

Test requests
```
sheshenya@mbp-sheshenya ~ $ http -v  :8080/foo/hello
GET /foo/hello HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:8080
User-Agent: HTTPie/1.0.2

HTTP/1.1 200 OK
Content-Length: 19
Content-Type: text/plain;charset=UTF-8

Hi from Foo service

```

Stream Test request 
```
sheshenya@mbp-sheshenya ~ $ http -v --stream  :8080/foo/events
GET /foo/events HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: localhost:8080
User-Agent: HTTPie/1.0.2



HTTP/1.1 200 OK
Content-Type: text/event-stream;charset=UTF-8
transfer-encoding: chunked

data:16:18:30.644{"status":"OKay..."}0

data:16:18:32.635{"status":"OKay..."}1

data:16:18:34.638{"status":"OKay..."}2

data:16:18:36.635{"status":"OKay..."}3
```