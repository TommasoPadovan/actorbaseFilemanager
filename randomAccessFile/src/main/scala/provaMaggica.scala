import java.io.{FileOutputStream, ObjectOutputStream, RandomAccessFile}
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.{Path, StandardOpenOption, OpenOption}
import java.util.concurrent.ConcurrentHashMap

/**
  * Created by kurt on 05/06/2016.
  */
object provaMaggica extends App{
  override def main(args: Array[String]) {

    /*
      * val raf = new RandomAccessFile(s"d:\\data\\file1.acb","rw")
      * val init: Long = 4
      * val offset: Long = 6

      * val before = System.currentTimeMillis
      * defragment(raf,init,offset)
      * val after = System.currentTimeMillis
      * println(after-before)
      */

    /*val keyMap = new ConcurrentHashMap[String, Bounds]
    val ostream = new ObjectOutputStream(new FileOutputStream("d:\\data\\map.acb"))
    ostream.writeObject(keyMap)
    ostream.close()*/

    val map = new ConcurrentHashMap[String, Array[Byte]]
    map.put("dio","can".getBytes)
    println(new String(map.get("dio")))


    val fileMngr = new SingleFileManager("d:\\data\\map.acb","d:\\data\\value.acb")
    /*fileMngr.InsertEntry("ano","pene".getBytes)
    fileMngr.InsertEntry("candedio","bastardo".getBytes)
    fileMngr.InsertEntry("ladroschifoso","can".getBytes)
    fileMngr.InsertEntry("candelporco","dedioedetomare".getBytes)
    fileMngr.InsertEntry("zigzagun","op".getBytes)
    fileMngr.InsertEntry("askdjaskdj","DIOLAZZARIONE".getBytes)*/
    //fileMngr.UpdateEntry("askdjaskdj","CANAGLIAGESUCRISTO".getBytes)


    val porcodio = fileMngr.ReadMap()
    println (new String(porcodio.get("candedio")))

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
