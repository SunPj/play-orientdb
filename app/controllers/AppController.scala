package controllers

import javax.inject._

import play.api._
import play.api.libs.json.Json
import play.api.mvc._
import services.{MyDao, OriendDbPool}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class AppController @Inject()(dao: MyDao) extends Controller {

  def query = Action {
    Ok(Json.obj("status" -> "success", "result" -> dao.query))
  }

}
