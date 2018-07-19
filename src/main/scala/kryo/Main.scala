package kryo

import java.io.{FileInputStream, FileOutputStream}

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.{Input, Output}
import com.twitter.chill.ScalaKryoInstantiator

/**
  * Created by zhaolei on 2018/7/4
  */
object Main extends App {

  test2()

  def test(): Unit = {
    val instantiator = new ScalaKryoInstantiator
    val kryo = instantiator.newKryo()
    kryo.register(classOf[KryoTestClass])
    val output = new Output(new FileOutputStream("file.bin"))
    val clazz  = KryoTestClass(1, "zhaolei")
    kryo.writeObject(output, clazz)
    System.err.println(clazz.toString)
    output.close()

    val input                   = new Input(new FileInputStream("file.bin"))
    val clazzNew: KryoTestClass = kryo.readObject(input, classOf[KryoTestClass])
    System.err.println(clazzNew.toString)
    input.close()
  }

  def test2() :Unit = {
    val kryo = new Kryo()
    kryo.register(classOf[KryoTestClassNone])
    val output = new Output(new FileOutputStream("file.bin"))
    val clazz  = KryoTestClassNone()
    kryo.writeObject(output, clazz)
    System.err.println(clazz.toString)
    output.close()

    val input                   = new Input(new FileInputStream("file.bin"))
    val clazzNew: KryoTestClassNone = kryo.readObject(input, classOf[KryoTestClassNone])
    System.err.println(clazzNew.toString)
    input.close()
  }

}

case class KryoTestClass(id: Int, value: String) {}

case class KryoTestClassNone()
