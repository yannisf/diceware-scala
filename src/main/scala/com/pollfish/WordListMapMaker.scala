package com.pollfish

import java.io.InputStream

import scala.io.Source

object WordListMapMaker {

  private val Filename = "/wordlist.txt"

  def apply(): Map[String, String] = {
    val inputStream: InputStream = getClass.getResourceAsStream(Filename)
    Source.fromInputStream(inputStream).getLines
      .map(_.split("""\s+"""))
      .map(a => a(0).trim -> a(1).trim)
      .toMap
  }

}
