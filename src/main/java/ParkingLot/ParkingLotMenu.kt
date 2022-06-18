package main.java.ParkingLot

class ParkingLotMenu(parkingLot: ParkingLot) {

    val parkingLot: ParkingLot = parkingLot

    fun startParkingLotMenu() {
        var input = MutableList(3) {""}
        loop@ while (input[0] != "exit") {
            input = readln().split(" ") as MutableList<String>
            when(input[0]) {
                "exit" -> break@loop
                "status" -> parkingLot.printParkingLots()
                "park" -> parkingLot.parkCar(input[1], input[2])
                "create" -> parkingLot.createParkingLot(input[1].toInt())
                "leave" -> parkingLot.leave(input[1].toInt())
                "spot_by_color" -> parkingLot.searchSpotByColor(input[1])
                "spot_by_reg" -> parkingLot.searchByReg(input[1])
                "reg_by_color" -> parkingLot.searchRegByColor(input[1])
            }
        }
    }
}