# monitoring
Reference Links:-
----------------
https://www.youtube.com/watch?v=2wr9njNdywk
https://www.youtube.com/watch?v=gJZhdEJvZmc
https://github.com/ben-manes/caffeine
https://howtodoinjava.com/spring-boot/spring-boot-caffeine-cache/


Technologies used:-
---------------
Spring Boot 3
Spring Data JPA
H2(in memory database)
caffeine cache
Prometheus


What do you mean by application health ?
Different metrics to monitor are there for measuring application health
-> % of Memory Consumption
-> % of CPU consumption
In Java applications, health measurments can be done based on heap memory usage, garbage collection time etc

To get these data, we need to use actuator in spring boot application.






Prometheus -
Prometheus is an open source monitoring system. It consists of the following core components :
1. Data scraper
 A Data scraper that pulls metrics data over HTTP periodically at configured interval.
2. Time Series DB
 A time-series DB to store all the metrics data
3. A simple UI where you can visualize, query and monitor all the metrics


Grafana -
Grafana allows you to bring data from various data sources like Elasticsearch, Prometheus, Graphite, InfluxDB etc and  visualize them with beautiful graphs.
It also lets you set alert rules based on your metrics data. When an alert changes state, it can  notify you over email,slack or various other channels.


docker commands :

docker run -p 9090:9090 -v <replace with absoulte path of prometheus.yml file> prom/prometheus

docker run -d  --name=grafana -p 3000:3000 grafana/grafana


