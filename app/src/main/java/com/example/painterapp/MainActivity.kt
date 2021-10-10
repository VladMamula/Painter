package com.example.painterapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Передача параметров в CartesianPainter
        initCartesianParameters()

        btn_confirm.setOnClickListener {
            initCartesianParameters()
        }
    }

    private fun initCartesianParameters() {
        //Цвет графика
        val graphColor = when (rg_graph_color.checkedRadioButtonId) {
            R.id.rb_graph_red -> 0xffff0000.toInt()
            R.id.rb_graph_blue -> 0xff0000ff.toInt()
            else -> 0xff000000.toInt()
        }
        // Цвет осей
        val axesColor = when (rg_axes_color.checkedRadioButtonId) {
            R.id.rb_axes_red -> 0xffff0000.toInt()
            R.id.rb_axes_blue -> 0xff0000ff.toInt()
            else -> 0xff000000.toInt()
        }
        //Толщина графика
        val graphWidth = when (rg_graph_width.checkedRadioButtonId) {
            R.id.rb_graph_thin -> 5F
            R.id.rb_graph_wide -> 15F
            else -> 10F
        }
        // Номер графика
        val graph = when (rg_graph.checkedRadioButtonId) {
            R.id.rb_graph1 -> 1
            R.id.rb_graph2 -> 2
            else -> 1
        }
        // Назначаем полученные параметры
        cartesian_painter.xMin = et_xMin.text.toString().toDouble()
        cartesian_painter.xMax = et_xMax.text.toString().toDouble()
        cartesian_painter.yMin = et_yMin.text.toString().toDouble()
        cartesian_painter.yMax = et_yMax.text.toString().toDouble()
        cartesian_painter.graphPaint.color = graphColor
        cartesian_painter.graphPaint.strokeWidth = graphWidth
        cartesian_painter.axesPaint.color = axesColor
        cartesian_painter.graph = graph
        // Отрисовываем
        cartesian_painter.invalidate()
    }
}