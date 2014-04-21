package org.ml.foil

import scala.io.Source

object Main extends App {

  for (line <- Source.fromURL(getClass.getResource("/kinship.data")).getLines())
    println(line)

}