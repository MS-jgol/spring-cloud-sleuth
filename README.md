# spring-cloud-sleuth
Open Telemetry based Spring Cloud Sleuth support in Application Insights

## Run the test app without Application Insights Java agent

$java -jar insights-0.0.1-SNAPSHOT.jar


### Produce the logs
Run : $ curl http://localhost:8080/echo

Produces the outputs in logs (the bolded part of the log is expected according to Spring Cloud Sleuth specs - trace ID and span ID are shown):

1. 2021-08-05 15:01:34.028  INFO [insights,**4369444393cf95497877ed70be4c071a,31a7f92a5b098719**] 11544 --- [nio-8080-exec-2] c.e.insights.controller.EchoController   : Supported name default-name
1. 2021-08-05 15:10:42.129  INFO [insights,**14c1ec00bd3a2296803c2be070390483,945475233f8b0b36**] 11544 --- [nio-8080-exec-6] c.e.insights.controller.EchoController   : Supported name default-name

## Run the test app with Application Insights Java agent

$java -javaagent:applicationinsights-agent-3.1.1-BETA-SNAPSHOT.jar -jar insights-0.0.1-SNAPSHOT.jar


### Produce the logs - with APPLICATIONINSIGHTS_PREVIEW_OTEL_API_SUPPORT set to false

Run : $ curl http://localhost:8080/echo

Produces the following output in logs where trace ID and span ID are now missing:

1. 2021-08-05 15:33:19.213  INFO [insights,,] 36384 --- [nio-8080-exec-1] c.e.insights.controller.EchoController   : Supported name default-name

### Produce the logs - with APPLICATIONINSIGHTS_PREVIEW_OTEL_API_SUPPORT set to true

Run : $ curl http://localhost:8080/echo

Produces the following output in logs where trace ID and span ID are now missing:

1. 2021-08-05 15:43:08.664  INFO [insights,,] 26688 --- [nio-8080-exec-2] c.e.insights.controller.EchoController   : Supported name default-name

### Expected results

1. Whether or not OTel API support is enabled, we should still see the traceID and SpanID produced by Spring Cloud Sleuth
2. These traceIDs and SpanIDs should appear in Application Insights, as produced by Spring Cloud Sleuth
