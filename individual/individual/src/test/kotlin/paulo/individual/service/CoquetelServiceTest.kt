package paulo.individual.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.modelmapper.ModelMapper
import org.springframework.web.server.ResponseStatusException
import paulo.individual.domain.Copo
import paulo.individual.domain.Coquetel
import paulo.individual.domain.Guarnicao
import paulo.individual.domain.Preparo
import paulo.individual.dtos.CopoDtoInput
import paulo.individual.dtos.CoquetelDtoInput
import paulo.individual.dtos.GuarnicaoDtoInput
import paulo.individual.dtos.PreparoDtoInput
import paulo.individual.repository.CoquetelRepository

class CoquetelServiceTest {

    lateinit var coquetelRepository: CoquetelRepository
    lateinit var coquetelService: CoquetelService
    lateinit var mapper: ModelMapper

    @BeforeEach
    fun iniciar(){
        coquetelRepository = mock(CoquetelRepository::class.java)
        coquetelService = mock(CoquetelService::class.java)
        mapper = mock(ModelMapper::class.java)
    }

    @Test
    fun insertCoquetel() {
        val coquetelIntput = CoquetelDtoInput(
            nome="chevette",
            descricao = "aaaaaaaaa",
            lista_ingredientes = "corote, gelo saborizado",
            preparo = PreparoDtoInput("Montado"),
            guarnicao =  GuarnicaoDtoInput("Rodela de limão"),
            copo= CopoDtoInput("Copo Baixo"),
        )

        `when`(coquetelService.insertCoquetel(coquetelIntput)).thenThrow(
            ResponseStatusException::class.java
        )
        assertThrows<ResponseStatusException>{coquetelService.insertCoquetel(coquetelIntput)}
    }

    @Test
    fun getListaCoquetel() {
        val listaEsperada = coquetelRepository.findAll()

        `when`(coquetelRepository.findAll()).thenReturn(listaEsperada)

        val resultado = coquetelService.getListaCoquetel()

        assertEquals(listaEsperada.size, resultado.size)
        assertEquals(listaEsperada,resultado)
    }

    @Test
    fun getCoquetelByNome() {
        val nomeParam = "ishdbjcns"
        `when`(coquetelService.getCoquetelByNome(nomeParam)).thenThrow(
            ResponseStatusException::class.java
        )
        assertThrows<ResponseStatusException>{coquetelService.getCoquetelByNome(nomeParam)}
    }

    @Test
    fun listaCoquetelByCopo() {
        val nomeParam = "sdfgfc"
        `when`(coquetelService.ListaCoquetelByCopo(nomeParam)).thenThrow(ResponseStatusException::class.java)
        assertThrows<ResponseStatusException> {coquetelService.ListaCoquetelByCopo(nomeParam) }
    }

    @Test
    fun attCoquetelById() {

        val idParam:Long = 3

        val coquetelParam = CoquetelDtoInput(
            nome= " caipiroska",
            descricao = "Versão do classico Brasileiro com base de Vodka, limão e açúcar",
            lista_ingredientes = "Vodka, Meio limão , Açúcar",
            preparo = PreparoDtoInput("Montado"),
            guarnicao = GuarnicaoDtoInput("Rodela de limão"),
            copo= CopoDtoInput("Copo Baixo")
        )

        val coquetelEsperado = Coquetel(
            id = 3,
            nome = " caipiroska",
            descricao = "Versão do classico Brasileiro com base de Vodka, limão e açúcar",
            lista_ingredientes =  "Vodka, Meio limão , Açúcar",
            preparo = Preparo(3, "Montado"),
            guarnicao = Guarnicao(3,"Rodela de limão"),
            copo = Copo(3, "Copo Baixo", "Copo para coquetéis médios ou degustação de destilados tanto puros como sobre gelo"
            )
        )
        `when`(coquetelService.attCoquetelById(coquetelParam, idParam)).thenReturn(coquetelEsperado)

        val resultado = coquetelService.attCoquetelById(coquetelParam, idParam)

        assertEquals(coquetelEsperado,resultado)
    }


    @Test
    fun getCoquetelById() {

        val idParam:Long = 0

        `when`(coquetelService.getCoquetelById(idParam)).thenThrow(ResponseStatusException::class.java)
        assertThrows<ResponseStatusException> {coquetelService.getCoquetelById(idParam) }

    }

    @Test
    fun delCoquetelById() {
        val idParam:Long = 0

        `when`(coquetelService.delCoquetelById(idParam)).thenThrow(ResponseStatusException::class.java)

        assertThrows<ResponseStatusException> {coquetelService.delCoquetelById(idParam) }

    }

    @Test
    fun validarCoquetel() {
        val coquetelParam = CoquetelDtoInput(
            nome= " ",
            descricao = "",
            lista_ingredientes = "",
            preparo = PreparoDtoInput("Montado"),
            guarnicao = GuarnicaoDtoInput("Rodela de limão"),
            copo= CopoDtoInput("Copo Baixo")
        )

        `when`(coquetelService.validarCoquetel(coquetelParam)).thenThrow(ResponseStatusException::class.java)

        assertThrows<ResponseStatusException> { coquetelService.validarCoquetel(coquetelParam)  }


    }

    @Test
    fun validarListas() {
        val listaParam = emptyList<Coquetel>()

        `when`(coquetelService.validarListas(listaParam)).thenThrow(ResponseStatusException::class.java)

        assertThrows<ResponseStatusException> { coquetelService.validarListas(listaParam) }
    }
}