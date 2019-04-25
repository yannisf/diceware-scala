package com.pollfish

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.thrift.ThriftServer
import com.twitter.finatra.thrift.routing.ThriftRouter

object DicewareServerMain extends DicewareServer

class DicewareServer extends HttpServer with ThriftServer {

  override def configureHttp(router: HttpRouter): Unit = {
    router.add[DicewareRestController]
  }

  override def configureThrift(router: ThriftRouter): Unit = {
    router
      .add[DicewareThriftController]
  }

}

