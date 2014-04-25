package org.ml.foil

import scala.io.Source

object Main extends App {

  for (line <- Source.fromURL(getClass.getResource("/kinship.data")).getLines())
    if (!line.isEmpty)
      println(Prolog.parse(line))

  //println(FOL.parse("hello(x, y)"))

}