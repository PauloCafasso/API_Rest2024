package paulo.individual.repository

import org.springframework.data.jpa.repository.JpaRepository
import paulo.individual.domain.Guarnicao

interface GuarnicaoRepository:JpaRepository<Guarnicao, Long> {
    fun findByTipoGuarnicao(tipoGuarnicao:String):Guarnicao
}