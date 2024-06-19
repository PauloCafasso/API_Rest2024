package paulo.individual.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank
import paulo.individual.dtos.CopoDtoInput
import paulo.individual.dtos.GuarnicaoDtoInput
import paulo.individual.dtos.PreparoDtoInput

@Entity
class Coquetel(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = 0,
    @field:NotBlank @field:Max(45) var nome:String,
    @field:NotBlank @field:Max(200) var descricao:String,
    @field:NotBlank @field:Max(300) var lista_ingredientes:String,
    @ManyToOne var preparo: PreparoDtoInput,
    @ManyToOne var guarnicao: GuarnicaoDtoInput,
    @ManyToOne var copo: CopoDtoInput

){
    constructor(
        paramNome: String,
        paramDescricao: String,
        paramLista_ingredientes: String,
        paramPreparo: PreparoDtoInput,
        paramGuarnicao: GuarnicaoDtoInput,
        paramCopo: CopoDtoInput
    ):this(
        id = null,
        nome = paramNome,
        descricao = paramDescricao,
        lista_ingredientes = paramLista_ingredientes,
        preparo = paramPreparo,
        guarnicao = paramGuarnicao,
        copo = paramCopo
    )
}