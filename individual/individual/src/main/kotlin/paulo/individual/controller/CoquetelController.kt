package paulo.individual.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
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
    fun addCoquetel(@RequestBody coquetel: CoquetelDtoInput): ResponseEntity<Coquetel> {
        return ResponseEntity.status(201).body(service.insertCoquetel(coquetel))
    }

    @PutMapping
    fun attCoquetel(@RequestBody coquetel: CoquetelDtoInput, @RequestParam id:Long): ResponseEntity<Coquetel>{
        return ResponseEntity.status(200).body(service.attCoquetelById(coquetel,id))
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