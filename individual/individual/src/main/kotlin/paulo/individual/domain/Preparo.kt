package paulo.individual.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotBlank

@Entity
class Preparo(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    @field:NotBlank var preparo: String
) {
}