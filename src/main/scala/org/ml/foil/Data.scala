package org.ml.foil

import scala.io.Source
import java.util
import scala.collection.JavaConversions._
import scala.collection.mutable.Map

/**
 * Class for getting a list of prolog clauses into a "Data" format, for use by our learner

 * Created by m4b on 4/29/14.
 */
class Data private (val clauses: java.util.List[Prolog]){

  val vars = getConstants.toArray
  val relations = getRelations.toArray
  val tupleMap = getTupleMap

  val numVars = vars.size
  val numRelations = relations.size

  private def getTupleMap = {
    // TODO: make string and List[List[String]] separate types, Relation and  RelationArguments/Tuples/RelationLiterals, respectively
    val tupleMap = Map.empty[String, List[List[String]]]
    for (clause <- this.clauses){
      if (tupleMap.contains(clause.predicate)){
        tupleMap += clause.predicate -> (clause.variables :: tupleMap(clause.predicate))
      }else{
        tupleMap += clause.predicate -> List(clause.variables)
      }
    }
    tupleMap
  }

  /**
   * yea, we loop over this twice (thrice)...
   * @return
   */
  private def getRelations = {
    var set = Set.empty[String]
    for (clause <- this.clauses){
      set += clause.predicate
    }
    set
  }

  private def getConstants = {
    var set = Set.empty[String]
    for (clause <- this.clauses if !clause.variables.isEmpty){
      clause.variables.foreach(s => set += s)
    }
    set
  }

}

object Data {

  def test = apply("/kinship.data")

  def apply(path: String): Data = {
    val l = new util.ArrayList[Prolog]()
    val res = Source.fromURL(getClass.getResource(path))
    for (line <- res.getLines() if !line.isEmpty) l add Prolog.parse(line)
    new Data(l)
  }

  /**
   * this is just for unit testing
   * @param unit the unit test
   * @return the data based on the unit test
   */
  def apply(unit: List[String]): Data = {
    val l = new util.ArrayList[Prolog]()
    for (line <- unit)
      if (!line.isEmpty)
        l add Prolog.parse(line)
    new Data(l)
  }

}