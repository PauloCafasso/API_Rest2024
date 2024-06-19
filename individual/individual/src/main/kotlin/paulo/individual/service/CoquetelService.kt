package paulo.individual.service

import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import paulo.individual.domain.Coquetel
import paulo.individual.dtos.CoquetelDtoInput
import paulo.individual.dtos.CoquetelDtoResponse
import paulo.individual.repository.CopoRepository
import paulo.individual.repository.CoquetelRepository
import paulo.individual.repository.GuarnicaoRepository
import paulo.individual.repository.PreparoRepository
import java.lang.Exception

@Service
class CoquetelService(
    private val mapper: ModelMapper,
    private val repository: CoquetelRepository,
    private val copoRepository: CopoRepository,
    private val guarnicaoRepository: GuarnicaoRepository,
    private val preparoRepository: PreparoRepository
) {

    /*fun insertCoquetel(coquetel:CoquetelDtoInput):Coquetel{

        if(repository.existsByNome(coquetel.nome)){
            throw ResponseStatusException(HttpStatusCode.valueOf(400))
        }

        val novoPreparo = preparoRepository.findByPreparo(coquetel.preparo.preparo)

        val novoCoquetel = Coquetel(
            id = null,
            nome = coquetel.nome,
            descricao = coquetel.descricao,
            lista_ingredientes = coquetel.lista_ingredientes,
            preparo = coquetel.preparo,
            guarnicao = coquetel.guarnicao,
            copo = coquetel.copo
        )

        repository.save(novoCoquetel)

        return novoCoquetel

    }*/

    fun getListaCoquetel():List<CoquetelDtoResponse>{

        val lista = repository.findAll()
        val listaDto : MutableList<CoquetelDtoResponse> = mutableListOf()

        lista.forEach { it ->
            val coquetelDtoResponse = mapper.map(it, CoquetelDtoResponse::class.java)
            listaDto.add(coquetelDtoResponse)
        }

        if(listaDto.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))

        return listaDto
    }

    fun getCoquetelByNome(nome:String):CoquetelDtoResponse{

     val coquetel = repository.findByNome(nome)
     val coquetelDto = mapper.map(coquetel, CoquetelDtoResponse::class.java)

     if(coquetelDto != null)   return coquetelDto
     else throw ResponseStatusException(HttpStatusCode.valueOf(404))

    }

    fun ListaCoquetelByCopo(nome:String):List<CoquetelDtoResponse>{

        if(repository.existsByCopoNome(nome)){
            val coqueteisEncontrados = repository.findAllByCopoNome(nome)
            val listaDto:MutableList<CoquetelDtoResponse> = mutableListOf()

            coqueteisEncontrados.forEach { coquetel ->
                val coquetelDtoResponse = mapper.map(coquetel, CoquetelDtoResponse::class.java)
                listaDto.add(coquetelDtoResponse)
            }

            validarListas(listaDto)

            return listaDto

        }

        throw ResponseStatusException(HttpStatusCode.valueOf(404))
    }

    /*fun attCoquetelById(coquetelInput: CoquetelDtoInput, id: Long):Coquetel{


        validarCoquetel(coquetelInput)

        val antigo = repository.findById(id).orElseThrow()

            antigo.nome = coquetelInput.nome
            antigo.descricao = coquetelInput.descricao
            antigo.lista_ingredientes = coquetelInput.lista_ingredientes
            antigo.preparo = PreparoDtoInput
            antigo.copo = copoRepository.findByNome(coquetelInput.copo.nome)
            antigo.guarnicao = guarnicaoRepository.findByTipoGuarnicao(coquetelInput.guarnicao.tipoGuarnicao)

        repository.save(antigo)

        return antigo
    } */

    fun insertCoquetel(coquetel: CoquetelDtoInput): CoquetelDtoResponse{
        if(repository.existsByNome(coquetel.nome)){
            throw ResponseStatusException(HttpStatusCode.valueOf(404))
        }

        val novoCoquetel = Coquetel(
            id = null,
            nome = coquetel.nome,
            descricao = coquetel.descricao,
            lista_ingredientes = coquetel.lista_ingredientes,
            preparo = coquetel.preparo,
            guarnicao = coquetel.guarnicao,
            copo = coquetel.copo
        )

        return try{
            val coquetelSalvo = repository.save(novoCoquetel)
            CoquetelDtoResponse(
                nome = coquetelSalvo.nome,
                descricao = coquetelSalvo.descricao
            )
        }catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar Coquetel: ${ex.message}")
        }
    }

    fun attCoquetel(id: Long, coquetel: CoquetelDtoInput): CoquetelDtoResponse {
        val coquetelExistente = repository.findById(id).orElseThrow {ResponseStatusException(HttpStatus.NOT_FOUND, "Coquetel não encontrado")}

        coquetelExistente.nome = coquetel.nome
        coquetelExistente.descricao = coquetel.descricao
        coquetelExistente.lista_ingredientes = coquetel.lista_ingredientes
        coquetelExistente.preparo = coquetel.preparo
        coquetelExistente.guarnicao = coquetel.guarnicao
        coquetelExistente.copo = coquetel.copo

        return try {
            val coquetelAtualizado = repository.save(coquetelExistente)
            CoquetelDtoResponse(
                nome = coquetelAtualizado.nome,
                descricao = coquetelAtualizado.descricao
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar coquetel: ${ex.message}")
        }

    }

    fun getCoquetelById(id:Long):Coquetel{

            return repository.findById(id).orElseThrow()

    }

    fun delCoquetelById(id:Long):Boolean{

        if(repository.existsById(id)){
            repository.deleteById(id)
            return true
        }
        throw ResponseStatusException(HttpStatusCode.valueOf(404))

    }

    fun validarCoquetel(coquetel: CoquetelDtoInput){
        if(coquetel.copo == null) throw ResponseStatusException(HttpStatusCode.valueOf(400))
        if(coquetel.descricao == null) throw ResponseStatusException(HttpStatusCode.valueOf(400))
        if(coquetel.guarnicao == null) throw ResponseStatusException(HttpStatusCode.valueOf(400))
        if(coquetel.preparo == null) throw ResponseStatusException(HttpStatusCode.valueOf(400))
        if(coquetel.nome == null) throw ResponseStatusException(HttpStatusCode.valueOf(400))
        if(coquetel.lista_ingredientes == null) throw ResponseStatusException(HttpStatusCode.valueOf(400))
    }

    fun validarListas(lista:List<*>){
        if(lista.isEmpty()) throw ResponseStatusException(HttpStatusCode.valueOf(204))
    }
}