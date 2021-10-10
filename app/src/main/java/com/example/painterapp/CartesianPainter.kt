package com.example.painterapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CartesianPainter(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    constructor(context: Context) : this(context, null)

    var backgroundPaint = Paint()
    val axesPaint = Paint()
    val graphPaint = Paint()
    var graph = 1

    var xMin = 0.0
    var xMax = 0.0
    var yMin = 0.0
    var yMax = 0.0

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            backgroundPaint.color = 0xffffffd8.toInt()
            drawPaint(backgroundPaint)

            val converter = CoordinatesConverter(width, height, xMin, xMax, yMin, yMax)
            drawAxes(this, converter)
        }
    }

    private fun drawAxes(canvas: Canvas?, converter: CoordinatesConverter) {

        val widthLine = converter.xCartToScreen(0.0)
        val heightLine = converter.yCartToScreen(0.0)
        canvas?.drawLine(widthLine.toFloat(), 0F, widthLine.toFloat(), height.toFloat(), axesPaint)
        canvas?.drawLine(0F, heightLine.toFloat(), width.toFloat(), heightLine.toFloat(), axesPaint)
    }
}

