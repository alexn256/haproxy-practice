# haproxy-practice
This repo show how to use [HAProxy](http://www.haproxy.org/) as TCP/HTTP load balancer. As a strategy will be used
roundrobin strategy.
## Requirements
[Docker](https://www.docker.com/) should be installed in your machine.

## How to run?
To run this example run the command:
```bash
docker-compose up --build
```
## How to use?
Make sure all docker images was built and run without errors. After successful build and start of the project, type in the browser this: [URL](https://http://localhost:80/). Reload the page several times to make sure that the roundrobin strategy works. Server response should be different after each call cyclically *Response from: Server 1* , *Response from: Server 2*, *Response from: Server 3*, *Response from: Server 1* ... etc.