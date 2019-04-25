package com.pollfish

import com.pollfish.masterdata.spec.v1.ThriftDicewareService.Generate
import com.pollfish.masterdata.spec.v1.{ThriftDicewareResponse, ThriftDicewareService}
import com.twitter.finagle.Service
import com.twitter.finatra.thrift.Controller
import com.twitter.util.Future

class DicewareThriftController extends Controller with ThriftDicewareService.ServicePerEndpoint {
  override def generate: Service[Generate.Args, ThriftDicewareResponse] = {
    handle(Generate) { args: Generate.Args =>
      val numberOfTokens = args.numberOfTokens
      val mode = args.mode

      val result = DicewareService.generate(Option(numberOfTokens), Option(mode))
      val response = ThriftDicewareResponse(result.password,
        Option(result.numberOfTokens.toByte),
        Option(result.concatMode))
      Future(response)
      }
  }
}
