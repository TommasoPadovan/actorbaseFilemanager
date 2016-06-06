import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.{Path, StandardOpenOption, OpenOption}

/**
  * Created by kurt on 05/06/2016.
  */
object provaMaggica extends App{
  override def main(args: Array[String]) {
    val raf = new RandomAccessFile("d:\\data\\file.acb","rw")
    val init: Long = 4
    val offset: Long = 6

    val before = System.currentTimeMillis

    defragment(raf,init,offset)

    val after = System.currentTimeMillis

    println(after-before)


    /*
    val buffer = new Array[Byte] (len-10)
    raf.read(buffer,0,len)
    raf.write(buffer)



    val pointer = raf.getFilePointer
    printlnBene(pointer)*/

  }


  private def printlnBene (l : Long) = {
    if (l==2) println("do")
    else {
      println(l)
    }
  }

  def defragment(file: RandomAccessFile, init: Long, off: Long): Unit ={
    var remain: Long = file.length()-(init+off)
    val byteLength = 8192
    var aux = init
    while(remain > byteLength){
      val ba = new Array[Byte](byteLength)
      file.seek(aux+off)
      file.read(ba,0,byteLength)
      file.seek(aux)
      file.write(ba)
      aux = aux+ byteLength
      remain = remain - byteLength
    }
    if(remain != 0){
      val ba = new Array[Byte](remain.toInt)
      file.seek(aux+off)
      file.read(ba,0,remain.toInt)
      file.seek(aux)
      file.write(ba)
      aux = aux+ remain.toInt
      val f = file.getChannel
      f.truncate(aux)
    }
  }
}
