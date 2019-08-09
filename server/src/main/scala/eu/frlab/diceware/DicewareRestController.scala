package eu.frlab.diceware

import com.twitter.finagle.http.{MediaType, Request}
import com.twitter.finatra.http.Controller
import com.twitter.finatra.request.QueryParam
import com.twitter.util.logging.Logging

class DicewareRestController extends Controller with Logging {

  private val DefaultNumberOfWords = Defaults.Instance.numberOfWords
  private val DefaultConcatMode = Defaults.Instance.concatMode

  get("/generate") { request: PasswordParams =>
    val numberOfWords = request.words.getOrElse(DefaultNumberOfWords)
    val concatMode = request.mode.getOrElse(DefaultConcatMode)
    logger.info("Received request to generate password")
    DicewareService.generate(numberOfWords, concatMode)
  }

  get("/wordlist") { request: Request =>
    val wordlistResponse = response.ok.file("/wordlist.txt")
    if (request.containsParam("download")) {
      wordlistResponse
        .contentType(MediaType.OctetStream)
        .header("Content-Disposition", "attachment; filename=wordlist.txt")
    } else {
      wordlistResponse
    }
  }

  get("/wordlist.json") { request: Request =>
    DicewareService.wordList()
  }

}

private case class PasswordParams(@QueryParam words: Option[Int],
                                  @QueryParam mode: Option[String])
