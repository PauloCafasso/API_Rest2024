package paulo.individual.repository

import org.springframework.data.jpa.repository.JpaRepository
import paulo.individual.domain.Copo
import paulo.individual.domain.Coquetel

interface CoquetelRepository:JpaRepository<Coquetel, Long>  {
    fun findByNome(nome:String):Coquetel
    fun existsByNome(nome:String):Boolean
    fun findAllByCopoNome(nome: String):List<Coquetel>
    fun existsByCopoNome(nome:String):Boolean
}