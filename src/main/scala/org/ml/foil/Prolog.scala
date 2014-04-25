package org.ml.foil

import scala.util.parsing.combinator.JavaTokenParsers

/**
 * Represents an N-ary prolog predicate literal
 *
 * Created by m4b on 4/24/14.
 */
class Prolog (val predicate: String, val variables: List[String]) {
  require (!variables.isEmpty)

  def apply(i: Int) = if (i == 0) predicate else variables(i-1)

  def len = variables.length

  override def toString = predicate + "(" + variables.mkString(",") + ")"

}

object Prolog extends JavaTokenParsers {

  private def literal: Parser[Prolog] = predicate~variables ^^ (x => new Prolog(x._1, x._2))

  private def predicate: Parser[String] = ident

  private def variables: Parser[List[String]] = "("~>repsep(ident, ",")<~")"

  def parse(s: String): Prolog = {
    parseAll(literal, s).get
  }

}
