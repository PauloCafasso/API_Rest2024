package paulo.individual.dtos

import paulo.individual.domain.Copo
import paulo.individual.domain.Guarnicao
import paulo.individual.domain.Preparo

data class CoquetelDtoInput(
    var nome:String,
    var descricao:String,
    var lista_ingredientes:String,
    var preparo: PreparoDtoInput,
    var guarnicao: GuarnicaoDtoInput,
    var copo: CopoDtoInput
)
