package paulo.individual.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class Coquetel (
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = 0,
    @field:NotBlank @field:Max(45) var nome:String,
    @field:NotBlank @field:Max(200) var descricao:String,
    @field:NotBlank @field:Max(300) var lista_ingredientes:String,
    @ManyToOne var preparo: Preparo,
    @ManyToOne var guarnicao: Guarnicao,
    @ManyToOne var copo: Copo

){
}