import java.io.RandomAccessFile

/**
  * Created by kurt on 06/06/2016.
  */
trait RemoveStrategy {
  def remove(file: RandomAccessFile, init: Long, off: Long): Unit
}
