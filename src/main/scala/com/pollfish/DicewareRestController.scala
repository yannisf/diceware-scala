package com.pollfish

import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.QueryParam
import com.twitter.util.logging.Logging

class DicewareRestController extends Controller with Logging {

  get(s"/diceware") { request: PasswordParams =>
    DicewareService.generate(request.tokens, request.mode)
  }

}

private case class PasswordParams(@QueryParam tokens: Option[Int], @QueryParam mode: Option[String])