package com.example.painterapp

class CoordinatesConverter(
    var realWidth: Int,
    var realHeight: Int,
    var xMin: Double,
    var xMax: Double,
    var yMin: Double,
    var yMax: Double
) {
    // Из декартовых в экранные координаты по х
    fun xCartToScreen(x: Double): Int {
        return (realWidth.toDouble() / (xMax - xMin) * (x - xMin)).toInt()
    }

    // Из декартовых в экранные координаты по у
    fun yCartToScreen(y: Double): Int {
        return (realHeight.toDouble() / (yMax - yMin) * (yMax - y)).toInt()
    }
}