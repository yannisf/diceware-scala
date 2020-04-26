package eu.frlab.diceware

import com.twitter.finagle.http.{MediaType, Request}
import com.twitter.finatra.http.Controller
import com.twitter.finatra.http.annotations.QueryParam
import com.twitter.util.logging.Logging
import javax.inject.Inject


class DicewareRestController @Inject() (dicewareService: DicewareService) extends Controller with Logging {

  private val DefaultNumberOfWords = Defaults.Instance.numberOfWords
  private val DefaultConcatMode = Defaults.Instance.concatMode

  options("/:*") { _: Request =>
    response.ok
  }

  get("/:*") { request: Request =>
    response.ok.fileOrIndex(request.params("*"), "index.html")
  }

  get("/generate") { request: PasswordParams =>
    val numberOfWords = request.words.getOrElse(DefaultNumberOfWords)
    val concatMode = request.mode.getOrElse(DefaultConcatMode)
    val requireDigit = request.digit.getOrElse(false)
    val requireSpecialChar = request.special.getOrElse(false)
    val numberOfPasswords = request.number.getOrElse(1)
    logger.info("Requested password generation")
    dicewareService.generate(numberOfWords, concatMode, requireDigit, requireSpecialChar, numberOfPasswords)
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

  get("/modes") { request: Request =>
    dicewareService.modes()
  }

  get("/wordlist.json") { request: Request =>
    dicewareService.wordList()
  }

}

private case class PasswordParams(@QueryParam words: Option[Int],
                                  @QueryParam mode: Option[String],
                                  @QueryParam digit: Option[Boolean],
                                  @QueryParam special: Option[Boolean],
                                  @QueryParam number: Option[Int])
