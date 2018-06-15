package queue

import java.util.concurrent.{ArrayBlockingQueue, ConcurrentLinkedQueue, ExecutorService, Executors}

/**
  * Created by zhaolei on 2018/6/12
  */
case class Basket() {
//  private val basket: ConcurrentLinkedQueue[String] = new ConcurrentLinkedQueue[String]
  private val basket: ArrayBlockingQueue[String] = new ArrayBlockingQueue[String](3)

  def produce(): Unit = basket.put("an apple")

  def consume(): String = basket.take()

  def getAppleNumber: Int = basket.size()
//  def produce(): Unit = basket.add("an apple")

//  def consume(): String = basket.take()

//  def getAppleNumber: Int = basket.size()
}

object TestBasket extends App {
  private val basket                   = new Basket
  private val service: ExecutorService = Executors.newCachedThreadPool()
  private val producer                 = new Producer1
  private val consumer                 = new Consumer1

  service.submit(producer)
  service.submit(consumer)

  Thread.sleep(10000)
  service.shutdownNow()

  class Producer1 extends Runnable {
    override def run(): Unit = {
      while (true) {
        System.err.println(s"A生产者准备生产: time = ${System.currentTimeMillis()}")
        basket.produce()
        System.err.println(s"B生产者生产完毕: time = ${System.currentTimeMillis()}, 数量 = ${basket.getAppleNumber}")
        Thread.sleep(300)
      }
    }
  }

  class Consumer1 extends Runnable {
    override def run(): Unit = {
      while (true) {
        System.err.println(s"1消费者准备消费: time = ${System.currentTimeMillis()}")
        basket.consume()
        System.err.println(s"2消费者消费完毕: time = ${System.currentTimeMillis()}, 数量 = ${basket.getAppleNumber}")
        Thread.sleep(1000)
      }
    }
  }

}
