package services

import javax.inject.{Inject, Singleton}

import com.orientechnologies.orient.core.db.OPartitionedDatabasePoolFactory
import play.api.inject.ApplicationLifecycle

import scala.concurrent.Future


/**
  * @author kraken
  */
@Singleton
class OriendDbPool @Inject()(lifecycle: ApplicationLifecycle) {
    val poolFactory = new OPartitionedDatabasePoolFactory()
    poolFactory.setMaxPoolSize(40)

    val pool = poolFactory.get("remote:localhost/test", "test", "test")

    lifecycle.addStopHook { () =>
        Future.successful{
            pool.close()
            poolFactory.close()
        }
    }

    def connection() = pool.acquire()
}