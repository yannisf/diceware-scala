package com.pollfish

import com.pollfish.masterdata.spec.v1.{ThriftDicewareResponse, ThriftDicewareService, ThriftDicewareService$FinagleService}
import com.twitter.finagle.Thrift
import com.twitter.util.{Await, Future}


object DicewareThriftClient extends App {

//  val c = new ThriftDicewareService$FinagleService()

  private val endpoint: ThriftDicewareService.MethodPerEndpoint = Thrift.client.build[ThriftDicewareService.MethodPerEndpoint]("localhost:9999")
  for ( i <- 1 to 10) {

  }
  private val response: Future[ThriftDicewareResponse] = endpoint.generate(5, "camel")
  println(Await.result(response).password)

}
