# Diceware password server

Scala diceware password server based on Finatra.

## Prerequisites

- jdk8
- sbt

## Build and Run

    $ sbt clean assembly
    $ java -jar server/target/scala-2.12/diceware-server.jar
    
### Docker

Through the `assembly` and `docker` plugin, it is possible create a Docker image that can be instantiated wherever docker is available.

    $ sbt clean assembly docker
    $ docker run --rm --name diceware-scala -d -p8888:8888 frlab/diceware:0.1 

## Test

The project has ScalaTest tests and is configured to provide test coverage reports with JaCoCo.

To run the automated tests, just execute:

    $ sbt test
    
### Code Coverage

Currently, the project is configured to run code coverage on all secondary projects and then aggregate the reports under the root project. The root project makes available the reports under `target/scala-2.12/jacoco/report/aggregate`. 

There are two report types, HTML which can be directly reviewed and XML that is consumed by tools such as SonarQube to provide a thorough analysis report.

To generate the aggregate report just run:

    $ sbt jacocoAggrate
    
For the JaCoCo to be consumed by SonarQube, their location must be defined in `sonar-project.properties` file under the key `sonar.coverage.jacoco.xmlReportPaths`. The entry should look like:

```properties
sonar.coverage.jacoco.xmlReportPaths=target/scala-2.12/jacoco/report/aggregate/jacoco.xml

```

## REST API

Default base URL: `http://localhost:8888`

### Generate password: `/generate` 

#### Optional parameters

- `words` (int): number of words to use in the password
- `mode` (flat|camel|snake): word concatenation mode 

**Example:** `http://localhost:8888?words=4&mode=flat`

### Display dictionary: `/wordlist`

#### Optional parameters

- `download`: download dictionary as file

## SonarQube

Since this project is Scala based, to run a SonarQube analysis, you will need to download [SonarScanner](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner/). A `sonar-project.properties` file is already committed in the project and just needs to be edited accordingly. To send a build for analysis all you have to do is execute from project root:
 
    $ sonar-scanner

## References
* http://world.std.com/~reinhold/diceware.html
* https://en.wikipedia.org/wiki/Diceware
* https://www.youtube.com/watch?v=Pe_3cFuSw1E
