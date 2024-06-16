package paulo.individual

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class IndividualApplication{
	@Bean
	fun modelMapper(): ModelMapper {
		val modelMapper = ModelMapper()
		modelMapper.configuration
			.setFieldMatchingEnabled(true)
			.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
		return modelMapper
	}
}

fun main(args: Array<String>) {
	runApplication<IndividualApplication>(*args)
}
