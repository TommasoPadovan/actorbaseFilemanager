import java.io.RandomAccessFile

/**
  * Created by kurt on 06/06/2016.
  */
class SpoolerRemove extends RemoveStrategy {

  /**
    * 
    * @param file
    * @param init
    * @param off
    */
  override def remove(file: RandomAccessFile, init: Long, off: Long): Unit = {
    var remain: Long = file.length-(init+off)
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