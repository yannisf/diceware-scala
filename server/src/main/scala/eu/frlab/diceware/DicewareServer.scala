package eu.frlab.diceware

import com.twitter.finagle.http.filter.Cors
import com.twitter.finagle.http.filter.Cors.HttpFilter
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.logging.Logging

object DicewareServerMain extends DicewareServer

class DicewareServer extends HttpServer with Logging {

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .filter(new HttpFilter(Cors.UnsafePermissivePolicy))
      .add[DicewareRestController]
  }

}
