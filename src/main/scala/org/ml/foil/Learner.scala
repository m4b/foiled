package org.ml.foil

/**
 *  Class embodying a first order inductive learner.
 *
 *  <p>
 *
 *  The FOIL algorithm is as follows:
 *
 *  Input List of examples
 *  Output Rule in first-order predicate logic
 *  FOIL(Examples)
 *
 *      Let Pos be the positive examples
        Let Pred be the predicate to be learned
        Until Pos is empty do:
            Let Neg be the negative examples
            Set Body to empty
            Call LearnClauseBody
            Add Pred ← Body to the rule
            Remove from Pos all examples which satisfy Body
    Procedure LearnClauseBody
        Until Neg is empty do:
            Choose a literal L
            Conjoin L to Body
            Remove from Neg examples that do not satisfy L
 *
 *  </p>
 *
 * Created by m4b on 4/27/14.
 */
class Learner {

  private val target: Prolog = null

  private def learnClauseBody() {}

  private def entropy(pos: Int, neg: Int): Double = {
    - Math.log(pos / (pos + neg))
  }

  private def gain(literal: Prolog){
    //entropy(setP, setN) * (entropy(
  }

}
