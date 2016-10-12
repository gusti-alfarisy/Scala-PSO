

/**
  * Created by gusti on 01/08/2016.
  */
class PSO(val dimensionLen: Int = 2,
          val swarmSize: Int = 10,
          val totIterations: Int = 1000,
          val c1: Double = 1.4962,
          val c2: Double = 1.4962,
          val w: Double = 0.7968,
          val maxRandValue: Int = 100) {

  var swarm = new Array[Particle](swarmSize)
  var pBest = new Array[Particle](swarmSize)
  var gBest = new Particle(Array())

  private val randomGenerator = new scala.util.Random
  initSwarm();

  def initSwarm(): Unit = {

    swarm.transform(x => {
      val tempPositions = for(i <- 0 to dimensionLen-1; v = randomGenerator.nextDouble()*maxRandValue) yield v;
      new Particle(tempPositions.toArray)
    })

    pBest.transform(x => {
      val temp = for(i <- 0 to dimensionLen-1) yield 0.0;
      new Particle(temp.toArray)
    })

    val temp = for(i <- 0 to dimensionLen-1) yield 0.0;
    gBest = new Particle(temp.toArray)
  }

  def run(): Particle = {
    for(i <- 0 to totIterations-1){
      for((p, (pB, iPB) ) <- (swarm zip pBest.view.zipWithIndex)){
        for(j <- p.positions.indices; v = p.velocity(j); pBPos = pB.positions(j); gBPos = gBest.positions(j)){
          p.velocity(j) = w * v
          + c1*randomGenerator.nextDouble()*(pBPos  - v)
          + c2*randomGenerator.nextDouble()*(gBPos - v)
          p.positions(j)+=p.velocity(j)
        }

        p.evaluate(fitnessFunc)

        if(p.fitnessVal > pB.fitnessVal){
          pBest(iPB) = p.clone()
          if(p.fitnessVal > gBest.fitnessVal) gBest = p.clone()
        }
      }

    }

    return gBest
  }

  implicit def fitnessFunc(pos: Array[Double]) : Double = {
    //Write your own fitness function here
    Function.Function.sphere(pos(0), pos(1));
  }



}
