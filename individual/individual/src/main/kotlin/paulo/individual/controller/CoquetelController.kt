package paulo.individual.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import paulo.individual.domain.Coquetel
import paulo.individual.dtos.CoquetelDtoInput
import paulo.individual.dtos.CoquetelDtoResponse
import paulo.individual.service.CoquetelService

@RestController
@RequestMapping("/coquetel")
class CoquetelController(
    val service: CoquetelService
) {
    @PostMapping
    fun addCoquetel(@RequestBody coquetel: CoquetelDtoInput): ResponseEntity<CoquetelDtoResponse> {
        return ResponseEntity.status(201).body(service.insertCoquetel(coquetel))
    }

    @PutMapping("/{id}")
    fun attCoquetel(
        @PathVariable id: Long,
        @RequestBody coquetel: CoquetelDtoInput
    ): ResponseEntity<CoquetelDtoResponse>{
        try {
            val coquetelDtoResponse = service.attCoquetel(id, coquetel)
            return ResponseEntity.status(200).body(coquetelDtoResponse)
        } catch (ex: ResponseStatusException) {
            return ResponseEntity.status(404).build()
        }
    }

    @GetMapping
    fun getAllCoquetel():ResponseEntity<List<CoquetelDtoResponse>>{
        return ResponseEntity.status(200).body(service.getListaCoquetel())
    }

    @GetMapping("/nome")
    fun getCoquetelByNome(@RequestParam nome:String):ResponseEntity<CoquetelDtoResponse>{
        return ResponseEntity.status(200).body(service.getCoquetelByNome(nome))
    }

    @GetMapping("/filtro-copo")
    fun getAllCoquetelByCopo(@RequestParam nome:String):ResponseEntity<List<CoquetelDtoResponse>>{
        return ResponseEntity.status(200).body(service.ListaCoquetelByCopo(nome))
    }

    @DeleteMapping
    fun deleteCoquetel(@RequestParam id:Long):ResponseEntity<String>{
        val coquetel = service.getCoquetelById(id)
        service.delCoquetelById(id)
        return ResponseEntity.status(200).body("Coquetel ${coquetel.nome} removido com sucesso")
    }



}