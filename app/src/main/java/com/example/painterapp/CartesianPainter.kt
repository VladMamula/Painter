package com.example.painterapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

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
            if (graph == 1)
                drawGraph1(this, converter)
            else drawGraph2(this, converter)

        }
    }

    // Отрисовка осей
    private fun drawAxes(canvas: Canvas?, converter: CoordinatesConverter) {
        val widthLine = converter.xCartToScreen(0.0)
        val heightLine = converter.yCartToScreen(0.0)
        canvas?.drawLine(widthLine.toFloat(), 0F, widthLine.toFloat(), height.toFloat(), axesPaint)
        canvas?.drawLine(0F, heightLine.toFloat(), width.toFloat(), heightLine.toFloat(), axesPaint)
    }

    // Отрисовка первого графика
    private fun drawGraph1(canvas: Canvas, converter: CoordinatesConverter) {
        val points: MutableList<Pair<Double, Double>> = mutableListOf()
        var x = xMin
        val h = 0.1
        while (x <= xMax) {
            val y = 4 * x - 7 * sin(x)
            points.add(Pair(x, y))
            x = ((x + h) * 10.0).roundToInt() / 10.0
        }
        for (i in 0 until points.size-1) {
            val p1X = converter.xCartToScreen(points[i].first)
            val p1Y = converter.yCartToScreen(points[i].second)
            val p2X = converter.xCartToScreen(points[i + 1].first)
            val p2Y = converter.yCartToScreen(points[i + 1].second)
            canvas.drawLine(
                p1X.toFloat(),
                p1Y.toFloat(),
                p2X.toFloat(),
                p2Y.toFloat(),
                graphPaint
            )
        }
    }

    //Отрисовка второго графика
    private fun drawGraph2(canvas: Canvas, converter: CoordinatesConverter) {
        val points1: MutableList<Pair<Double, Double>> = mutableListOf()
        val points2: MutableList<Pair<Double, Double>> = mutableListOf()

        var x = xMin
        val h = 0.1
        while (x <= xMax) {
            val y = sqrt(16 + 4 * x * x)
            val y1 = -sqrt(16 + 4 * x * x)
            points1.add(Pair(x, y))
            points2.add(Pair(x, y1))
            x = ((x + h) * 10.0).roundToInt() / 10.0
        }

        for (i in 0 until points1.size - 1) {
            val p1X = converter.xCartToScreen(points1[i].first)
            val p1Y = converter.yCartToScreen(points1[i].second)
            val p2X = converter.xCartToScreen(points1[i + 1].first)
            val p2Y = converter.yCartToScreen(points1[i + 1].second)
            canvas.drawLine(p1X.toFloat(), p1Y.toFloat(), p2X.toFloat(), p2Y.toFloat(), graphPaint)
        }

        for (i in 0 until points2.size - 1) {
            val p1X = converter.xCartToScreen(points2[i].first)
            val p1Y = converter.yCartToScreen(points2[i].second)
            val p2X = converter.xCartToScreen(points2[i + 1].first)
            val p2Y = converter.yCartToScreen(points2[i + 1].second)
            canvas.drawLine(p1X.toFloat(), p1Y.toFloat(), p2X.toFloat(), p2Y.toFloat(), graphPaint)
        }
    }
}

