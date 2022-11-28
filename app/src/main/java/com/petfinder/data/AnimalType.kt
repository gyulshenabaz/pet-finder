
package com.petfinder.data

enum class AnimalType(val type: String) {
    Dog("Dog"),
    Cat("Cat"),
    SmallAndFurry("Small & Furry"),
    Bird("Bird"),
    ScalesFinsAndOther("Scales, Fins & Other");

    val singleWordRepresentation: String
        get() = when (this) {
            SmallAndFurry -> type.split(" ").last()
            else -> type.split(" ").first().removeSuffix(",")
        }

    companion object {
        fun fromString(animalType: String): AnimalType = valueOf(animalType)
    }
}
