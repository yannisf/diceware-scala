# Diceware password server

Scala diceware password server based on Finatra.

## Prerequisites

- jdk8
- sbt

## Build and Run

    $ sbt clean assembly
    $ java -jar server/target/scala-2.12/diceware-server.jar

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

## References
* http://world.std.com/~reinhold/diceware.html
* https://en.wikipedia.org/wiki/Diceware
* https://www.youtube.com/watch?v=Pe_3cFuSw1E
