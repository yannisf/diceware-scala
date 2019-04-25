#@namespace scala com.pollfish.masterdata.spec.v1

struct ThriftDicewareResponse {
    1: required string password
    2: optional byte numberOfTokens
    3: optional string concatMode
}

service ThriftDicewareService {

    ThriftDicewareResponse generate(
        1: byte numberOfTokens
        2: string mode
    )

}
