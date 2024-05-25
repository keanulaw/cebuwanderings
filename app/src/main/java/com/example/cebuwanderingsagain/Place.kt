package com.example.cebuwanderingsagain

class Place(val name: String, val num: Int, val image: Int)

class Places {
    companion object {
        private val mountainPlaces = mutableListOf<Place>()
        private val restaurantPlaces = mutableListOf<Place>()

        fun addMountainPlace(place: Place) {
            mountainPlaces.add(place)
        }

        fun getMountainPlaces(): List<Place> {
            return mountainPlaces.toList()
        }

        fun getMountainPlaceNames(): List<String> {
            return mountainPlaces.map { it.name }
        }

        fun addRestaurantPlace(place: Place) {
            restaurantPlaces.add(place)
        }

        fun getRestaurantPlaces(): List<Place> {
            return restaurantPlaces.toList()
        }

        fun getRestaurantPlaceNames(): List<String> {
            return restaurantPlaces.map { it.name }
        }
    }
}
