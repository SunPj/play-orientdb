package services

import javax.inject.Inject

import com.orientechnologies.orient.core.record.impl.ODocument
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery
import collection.JavaConverters._

/**
  * @author kraken
  */
class MyDao @Inject()(pool: OriendDbPool){

    def query: Option[String] = {
        val conn = pool.connection()

        try {
            val res = conn.query[java.util.List[ODocument]](new OSQLSynchQuery[ODocument]("select * from User limit 1000;")).asScala.toList

            if (res.isEmpty) None else Some(res.head.field("name"))
        } finally {
            conn.close()
        }
    }
}