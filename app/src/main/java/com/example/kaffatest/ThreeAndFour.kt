package com.example.kaffatest

class ThreeAndFour(
    val rectangle1x1: Long,
    val rectangle1y1: Long,
    val rectangle1x2: Long,
    val rectangle1y2: Long,
    val rectangle2x1: Long,
    val rectangle2y1: Long,
    val rectangle2x2: Long,
    val rectangle2y2: Long
) {

    fun calculaArea(): Long {
        val hypotheticalRectangleX1 = maxOf(rectangle1x1, rectangle2x1)
        val hypotheticalRectangleY1 = maxOf(rectangle1y1, rectangle2y1)
        val hypotheticalRectangleX2 = minOf(rectangle1x2, rectangle2x2)
        val hypotheticalRectangleY2 = minOf(rectangle1y2, rectangle2y2)

        return (hypotheticalRectangleX2 - hypotheticalRectangleX1 +1 ) *
                (hypotheticalRectangleY2 - hypotheticalRectangleY1 +1)
    }
}