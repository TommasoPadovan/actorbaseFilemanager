import java.io.RandomAccessFile
import java.util

/**
  * Created by kurt on 06/06/2016.
  */

/**
  * il file delle key ha come prima riga il path del gifafilemagico delle value
  * @param path
  */
class SingleFileManager (path : String) extends FileManager {

  var removeStrategy : RemoveStrategy = new SpoolerRemove

  /**
    * Inserts an entry in the files
    *
    * @param key   The key of the entry.
    * @param value The value of the entry
    */
  override def InsertEntry(key: String, value: Array[Byte]): Unit = ???

  /**
    * Updates the value of the entry with the given key in the file.
    *
    * @param key   The key of the entry.
    * @param value The value of the entry
    */
  override def UpdateEntry(key: String, value: Array[Byte]): Unit = ???

  /**
    * Removes the entry with the given key from the file.
    *
    * @param key The key of the entry.
    */
  override def RemoveEntry(key: String): Unit = {

  }

  /**
    * Reads an entire map.
    *
    * @return The map to read.
    */
  override def ReadMap(): util.HashMap[String, Array[Byte]] = ???

  /**
    * Writes an entire map in the file.
    *
    * @param map The map to write.
    */
  override def WriteMap(map: util.HashMap[String, Array[Byte]]): Unit = ???


  def InsertValue(file: RandomAccessFile, value: Array[Byte]): Long = {
    val init=file.length
    file.seek(init)
    file.write(value)
    init
  }


  def InsertKey(key: String, from: Integer): Unit = {

  }

  def RemoveKey(key: String): Unit ={

  }

  private def RemoveValue(file: RandomAccessFile, init: Long, off: Long): Unit = {
    removeStrategy.remove(file, init, off)
  }
}
