# kafka getting started

_download and bootstrap kafka (unix)_

```bash
bash ./download-and-start-kafka.sh
netstat -aln|grep 2181
netstat -aln|grep 9092
```

<!--

_prepare maven sample project_

```bash
mvn -N io.takari:maven:wrapper -Dmaven=3.6.0
./mvnw archetype:generate -DarchetypeGroupId=org.apache.kafka \
                          -DarchetypeArtifactId=streams-quickstart-java \
                          -DarchetypeVersion=2.1.0 \
                          -DgroupId=com.github.daggerok \
                          -DartifactId=kafka-streams-examples \
                          -Dversion=1.0.0-SNAPSHOT \
                          -Dpackage=con.github.daggerok.kafka
./mvnw -f kafka-streams-examples/pom.xml exec:java -Dexec.mainClass=con.github.daggerok.kafka.Pipe
```

-->

_flow_

```bash
./gradlew clean
bash ./download-and-start-kafka.sh

./gradlew -DmainClass=com.github.daggerok.ConsumerV1Kt
mv -f ./build/install/kafka-java-example ./build/consumer
bash ./build/consumer/bin/kafka-java-example &

./gradlew -DmainClass=com.github.daggerok.ProducerV1Kt
mv -f ./build/install/kafka-java-example ./build/producer
bash ./build/producer/bin/kafka-java-example one and two and three and four...

killall -9 java
```
