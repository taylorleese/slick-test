import scala.slick.driver.MySQLDriver.simple._
import Database.threadLocalSession
import scala.slick.lifted.BaseTypeMapper
import scala.slick.lifted.MappedTypeMapper.base
import CustomTypes._

object Applications extends Table[TestApplication]("applications") {
  def appId = column[AppId]("id", O.PrimaryKey, O.AutoInc)
  def clientId = column[ClientId]("client_id")
  override def * = {
    (appId ~ clientId) <>(TestApplication, TestApplication.unapply _)
  }
}

object CustomTypes {
  implicit val AppIdTypeMapper: BaseTypeMapper[AppId] = base[AppId, Long](_.value, new AppId(_))
  implicit val ClientIdTypeMapper: BaseTypeMapper[ClientId] = base[ClientId, Long](_.value, new ClientId(_))
}

class AppId(override val value: Long) extends AnyVal with LongValue
class ClientId(override val value: Long) extends AnyVal with LongValue

trait LongValue extends Any with Serializable {
  def value: Long
  override def toString: String = value.toString
}

case class TestApplication(appId: AppId, clientId: ClientId)

object Test {
  Database.forURL("ignore-for-now").withSession {
    Query(Applications).filter(_.appId === new AppId(1)).firstOption
  }
}
