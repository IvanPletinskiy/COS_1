package com.handen.dsp.dsp_1

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.chart.LineChart
import javafx.scene.chart.NumberAxis
import javafx.scene.chart.XYChart
import javafx.scene.chart.XYChart.Series
import javafx.stage.Stage
import kotlin.math.PI
import kotlin.math.sin

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        drawFirstPlot()
        drawSecondPlot()
        drawThirdPlot()
        drawPolyharmonicChart()
        drawPolyharmonicLinearChart()
    }
}

fun functionOne(A: Int, frequency: Int, n: Int, phase: Double): Double {
    return A * sin((2 * Math.PI * frequency * n / N) + phase)
}

fun functionOne(A: Double, frequency: Double, n: Int, phase: Double): Double {
    return A * sin((2 * Math.PI * frequency * n / N) + phase)
}

fun drawFirstPlot() {
    val stage = Stage()
    stage.title = "1.a"
    val xAxis = NumberAxis()
    val yAxis = NumberAxis()
    xAxis.label = "n"
    val lineChart = LineChart(xAxis, yAxis)

    val phases = listOf(
        Math.PI / 4 to "PI / 4",
        Math.PI / 2 to "PI / 2",
        3 * Math.PI / 4 to "3PI / 4",
        0.0 to "0",
        Math.PI to "PI"
    )

    val A = 5;
    val f = 1

    phases.forEach {
        val series = Series<Number, Number>()
        series.name = it.second
        getValues(A, f, it.first).forEach {
            series.data.add(XYChart.Data(it.first, it.second))
        }

        lineChart.data.add(series)
    }

    lineChart.createSymbols = false

    val scene = Scene(lineChart, 800.0, 600.0)
    stage.scene = scene
    stage.show()
}

fun drawSecondPlot() {
    val stage = Stage()
    stage.title = "1.б"
    val xAxis = NumberAxis()
    val yAxis = NumberAxis()
    xAxis.label = "n"
    val lineChart = LineChart(xAxis, yAxis)

    val A = 1;
    val phase = Math.PI
    val frequencies = listOf(
        1 to "f1",
        3 to "f2",
        2 to "f3",
        4 to "f4",
        10 to "f5"
    )

    frequencies.forEach {
        val series = Series<Number, Number>()
        series.name = it.second
        getValues(A, it.first, phase).forEach {
            series.data.add(XYChart.Data(it.first, it.second))
        }

        lineChart.data.add(series)
    }

    lineChart.createSymbols = false

    val scene = Scene(lineChart, 800.0, 600.0)
    stage.scene = scene
    stage.show()
}

fun drawThirdPlot() {
    val stage = Stage()
    stage.title = "1.в"
    val xAxis = NumberAxis()
    val yAxis = NumberAxis()
    xAxis.label = "n"
    val lineChart = LineChart(xAxis, yAxis)

    val frequency = 4
    val phase = Math.PI
    val amplitudes = listOf(
        3 to "A1",
        5 to "A2",
        10 to "A3",
        4 to "A4",
        8 to "A5"
    )

    amplitudes.forEach {
        val series = Series<Number, Number>()
        series.name = it.second
        getValues(it.first, frequency, phase).forEach {
            series.data.add(XYChart.Data(it.first, it.second))
        }

        lineChart.data.add(series)
    }

    lineChart.createSymbols = false

    val scene = Scene(lineChart, 800.0, 600.0)
    stage.scene = scene
    stage.show()
}

fun drawPolyharmonicChart() {
    val stage = Stage()
    stage.title = "3"
    val xAxis = NumberAxis()
    val yAxis = NumberAxis()
    xAxis.label = "n"
    val lineChart = LineChart(xAxis, yAxis)


    val data = listOf(
        Triple(5, 1, PI / 9),
        Triple(5, 2, PI / 4),
        Triple(5, 3, PI / 3),
        Triple(5, 4, PI / 6),
        Triple(5, 5, 0.0)
    )


    val values = (0 until N).map { n ->
        n to data.sumOf {
            functionOne(it.first, it.second, n, it.third)
        }
    }

    val series = Series<Number, Number>()
    values.forEach {
        series.data.add(XYChart.Data(it.first, it.second))
    }

    lineChart.data.add(series)
    lineChart.createSymbols = false

    val scene = Scene(lineChart, 800.0, 600.0)
    stage.scene = scene
    stage.show()
}

fun drawPolyharmonicLinearChart() {
    var amplitude = 10.0; var frequency = 10.0; var phase = 0.0

    val values = (0  until  N).map {
        val value = it.toDouble() to functionOne(amplitude, frequency, it, phase)
        amplitude += (it % N) * 0.0001
        frequency += (it % N) * 0.0001
        phase += (it % N) * 0.0001
        value
    }

    val series = Series<Number, Number>()
    values.forEach {
        series.data.add(XYChart.Data(it.first, it.second))
    }

    val stage = Stage()
    stage.title = "4"
    val xAxis = NumberAxis()
    val yAxis = NumberAxis()
    xAxis.label = "n"
    val lineChart = LineChart(xAxis, yAxis)

    lineChart.data.add(series)
    lineChart.createSymbols = false

    val scene = Scene(lineChart, 800.0, 600.0)
    stage.scene = scene
    stage.show()
}

fun getValues(A: Int, f: Int, phase: Double): List<Pair<Double, Double>> {
    return (0 until N).map {
        it.toDouble() to functionOne(A, f, it, phase)
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}

val N = 512