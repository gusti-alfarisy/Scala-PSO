/**
  * Created by Gusti Ahmad Fanshuri on 12/10/2016.
  */
object Driver {

  def main(args: Array[String]) = {
    val pso = new PSO()
    val gBest = pso.run()

    println(s"gBest x1 = ${gBest.positions(0)} | x2 = ${gBest.positions(1)}")
  }

}
