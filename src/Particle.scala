/**
  * Created by gusti on 01/08/2016.
  */
class Particle(var positions: Array[Double]){
  var velocity = new Array[Double](positions.length)
//  Objective value
  var fitnessVal: Double = 0;

  def setToZero() = {
    positions transform {x=> 0}
    velocity transform {x=> 0}
    fitnessVal = 0
  }

  override def clone(): Particle = {
    var clone = new Particle(positions)
    clone.fitnessVal = fitnessVal
    clone.velocity = velocity
    clone
  }

  def evaluate(f: Array[Double] => Double) = {fitnessVal = f(positions)};

}
