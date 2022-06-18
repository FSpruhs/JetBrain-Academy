package main.java.ParkingLot

class ParkingLot {

    private var parkingLots: MutableList<Spot>? = null
    private val notBeenCreated = "Sorry, a parking lot has not been created."

    private fun isParkingLotCreated():Boolean {
        return parkingLots != null
    }

    fun createParkingLot(numbers:Int) {
        parkingLots = MutableList(numbers) { Spot() }
        println("Created a parking lot with $numbers spots.")

    }

    private fun isParkingLotEmpty(): Boolean {
        for (spot in parkingLots!!) {
            if (spot.car != null) {
                return false
            }
        }
        return true
    }

    fun printParkingLots() {
        if (!isParkingLotCreated()) {
            println(notBeenCreated)
        } else if (isParkingLotEmpty()) {
            println("Parking lot is empty.")
        } else {
            for (index in parkingLots?.indices!!) {
                if (parkingLots!![index].car != null) println("${index + 1} ${parkingLots!![index]}")
            }
        }
    }

    private fun nextFreeParkingLot(): Int {
        for (index in parkingLots?.indices!!) {
            if (parkingLots!![index].car == null) {
                return index
            }
        }
        return -1 //if no free parkingLot
    }

    fun parkCar(carLicense: String, carColor: String) {
        if (!isParkingLotCreated()) {
            println(notBeenCreated)
        } else {
            val freeParkingLot = nextFreeParkingLot()
            if (freeParkingLot == -1) {
                println("Sorry, the parking lot is full.")
            } else {
                parkingLots?.get(freeParkingLot)?.car =  Car(carLicense, carColor)
                println("$carColor car parked in spot ${freeParkingLot + 1}.")
            }
        }
    }

    fun leave(parkingLotNumber: Int) {
        if (!isParkingLotCreated()) {
            println(notBeenCreated)
        } else {
            parkingLots?.get(parkingLotNumber - 1)?.car = null
            println("Spot $parkingLotNumber is free.")
        }
    }

    fun searchSpotByColor(input: String) {
        if (!isParkingLotCreated()) {
            println(notBeenCreated)
        } else {
            var output = ""
            for (index in parkingLots?.indices!!) {
                if ((parkingLots!![index].car?.color?.lowercase() == input.lowercase())) {
                    if (output != "") {
                        output += ", "
                    }
                    output += index + 1
                }
            }
            if (output == "") {
                println("No cars with color $input were found.")
            } else {
                println(output)
            }
        }
    }

    fun searchByReg(input: String) {
        if (!isParkingLotCreated()) {
            println(notBeenCreated)
        } else {
            var output = ""
            for (index in parkingLots?.indices!!) {
                if ((parkingLots!![index].car?.reg?.contains(input) == true)) {
                    if (output != "") {
                        output += ", "
                    }
                    output += index + 1
                }
            }
            if (output == "") {
                println("No cars with registration number $input were found.")
            } else {
                println(output)
            }
        }
    }

    fun searchRegByColor(input: String) {
        if (!isParkingLotCreated()) {
            println(notBeenCreated)
        } else {
            var output = ""
            for (index in parkingLots?.indices!!) {
                if ((parkingLots!![index].car?.color?.lowercase() == input.lowercase())) {
                    if (output != "") {
                        output += ", "
                    }
                    output += parkingLots!![index].car?.reg
                }
            }
            if (output == "") {
                println("No cars with color $input were found.")
            } else {
                println(output)
            }
        }
    }
}