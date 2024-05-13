package com.example.cebuwanderingsagain
class Place(val name: String, val num: Int, val image: Int)

class Places {

    companion object{
        private val listOfCustomers = mutableListOf<Place>()

        fun addCustomer(customer: Place){
            listOfCustomers.add(customer)
        }
        fun getCustomer(): List<Place>{
            return listOfCustomers.toList()
        }

        fun getCustomerNames(): List<String>{
            return listOfCustomers.map{it.name}.toList()
        }
    }
}


