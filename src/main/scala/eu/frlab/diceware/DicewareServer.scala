package eu.frlab.diceware

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

object DicewareServer extends HttpServer {

  def configureHttp(router: HttpRouter): Unit = {
    router.add[DicewareRestController]
  }

}

