package de.morhenn.placesApi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class PlacesApiApplication

fun main(args: Array<String>) {
	runApplication<PlacesApiApplication>(*args)
}

@RestController
class PlaceResource(val service: PlaceService) {

	@GetMapping("places")
	fun index(): List<Place> {
		return service.getPlaces()
	}

	@PostMapping("places")
	fun post(@RequestBody place: Place){
		service.post(place)
	}

	@DeleteMapping("places")
	fun delete(@RequestBody place: Place){
		service.delete(place)
	}
}

@Service
class PlaceService(val db: PlaceRepository) {

	fun getPlaces(): List<Place> {
		println("getPlaces was called")
		return db.getPlaces()
	}

	fun post(place: Place){
		println("Place: $place")
		db.save(place)
	}

	fun delete(place: Place){
		println("Deleting place: $place")
		db.delete(place)
	}
}


interface PlaceRepository : CrudRepository<Place, String> {

	@Query("select * from PLACES")
	fun getPlaces(): List<Place>
}

@Table("PLACES")
data class Place(
	@Id val id: String?,
	val name: String,
	val lat: Double,
	val lng: Double,
	val alt: Double,
	val heading: Double,
	val description: String,
	val author: String,
	val ardata: String
) {
	override fun toString(): String {
		return "Place(id=$id, name='$name', lat=$lat, lng=$lng, alt=$alt, heading=$heading, description='$description', author='$author', ardata='$ardata')"
	}
}