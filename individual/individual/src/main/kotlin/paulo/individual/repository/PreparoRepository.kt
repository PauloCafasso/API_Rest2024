package paulo.individual.repository

import org.springframework.data.jpa.repository.JpaRepository
import paulo.individual.domain.Preparo

interface PreparoRepository: JpaRepository<Preparo, Long> {
    fun findByPreparo(tipo_preparo:String):Preparo
}