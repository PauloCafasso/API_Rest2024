package paulo.individual.repository

import org.springframework.data.jpa.repository.JpaRepository
import paulo.individual.domain.Copo

interface CopoRepository: JpaRepository<Copo, Long> {
    fun findByNome(nome:String):Copo
}