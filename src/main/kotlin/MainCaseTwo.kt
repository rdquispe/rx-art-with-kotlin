import io.reactivex.rxjava3.core.Observable
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.abs

data class Point(val x: Int, val y: Int, var rgb: Int)

class MainCaseTwo {

    fun run(buffer: BufferedImage): Observable<Point> {
        return Observable.create { emitter ->
            var x = 0
            while (x < buffer.width && !emitter.isDisposed) {
                var y = 0
                while (y < buffer.height && !emitter.isDisposed) {
//                    println("x: $x, y: $y, rgb: ${buffer.getRGB(x, y)}")
                    emitter.onNext(Point(x, y, buffer.getRGB(x, y)))
                    y++
                }
                x++
            }
            emitter.onComplete()
        }
    }
}

fun Point.changeColor(): Point {
    val color = Color(this.rgb, true)
    val target = Color(Integer.decode("0x000000"))
    val newColor = Color(Integer.decode("0xff0077"))
    if (
        abs(color.red - target.red) <= 60 &&
        abs(color.green - target.green) <= 60 &&
        abs(color.blue - target.blue) <= 60
    ) {
        this.rgb = newColor.rgb;
    }
    println(this.rgb)
    return this
}

fun main() {
    val data = Data(name = "/images/pulp.jpg", targetColor = "000000", newColor = "3396ff", tolerance = 60)

    MainCaseTwo().run(data.input)
        .map { point -> point.changeColor() }
        .subscribe()
}