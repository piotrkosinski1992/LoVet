import com.project.auth.domain.BaseEntity
import com.project.auth.usecase.exceptions.InvalidCredentials
import com.project.auth.usecase.gateway.BaseEntityQueryGateway
import com.project.auth.usecase.LoadBaseEntityUseCase
import spock.lang.Specification

class LoadBaseEntityUseCaseTest extends Specification {

  private def baseEntityQueryGateway = Mock(BaseEntityQueryGateway)
  private def loadBaseEntity = new LoadBaseEntityUseCase(baseEntityQueryGateway)

  def "should return BaseEntity based on it's username"() {
    given:
    def username = "username"
    def baseEntity = Mock(BaseEntity)
    baseEntityQueryGateway.findByUsername(username) >> Optional.of(baseEntity)

    when:
    def result = loadBaseEntity.findByUsername(username)

    then:
    result == baseEntity
  }

  def "should throw exception when there is no BaseEntity with given username"() {
    given:
    baseEntityQueryGateway.findByUsername(_ as String) >> Optional.empty()

    when:
    loadBaseEntity.findByUsername("unknownUsername")

    then:
    thrown(InvalidCredentials)
  }
}
