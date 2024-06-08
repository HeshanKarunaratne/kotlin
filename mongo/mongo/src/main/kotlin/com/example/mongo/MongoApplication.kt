package com.example.mongo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@SpringBootApplication
class MongoApplication


@Document
data class City(
    @Id val id: String?,
    val name: String,
    val country: String,
    val populationInMillions: Float,
)

data class CreateCityDto(
    val name: String,
    val country: String,
    val populationInMillions: Float,
) {
    init {
        require(populationInMillions > 0, { "Population must be greater than 0" })
    }
}

interface CityRepository : MongoRepository<City, String> {
    fun findByCountry(country: String): Iterable<City>

    @Query("{'populationInMillions': {'\$gt':?0}}")
    fun findBySize(minPopulation: Float): Iterable<City>
}

@RestController
@RequestMapping("cities")
class CityController(val cityRepository: CityRepository) {

    @GetMapping
    fun get(
        @RequestParam(required = false) country: String?,
        @RequestParam(required = false) minSize: Float?
    ): Iterable<City> {
        if (country != null && minSize != null) throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Not supported")
        if (country != null) return cityRepository.findByCountry(country)
        if (minSize != null) return cityRepository.findBySize(minSize)
        return cityRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): City = cityRepository.findById(id).orElseThrow {
        ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun create(@RequestBody request: CreateCityDto): City {
        return cityRepository.save(
            City(
                id = null,
                name = request.name,
                country = request.country,
                populationInMillions = request.populationInMillions,
            )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<MongoApplication>(*args)
}

