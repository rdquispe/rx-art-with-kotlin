import java.awt.Color
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.abs

class MainCaseOne {

    @Throws(IOException::class)
    fun run(data: Data) {
        for (x in 0 until data.input.width) {
            for (y in 0 until data.input.height) {
                var rgb: Int = data.input.getRGB(x, y)
                val color = Color(rgb, true)
                val tolerance = data.tolerance
                if (abs(color.red - data.targetColor.red) <= tolerance &&
                    abs(color.green - data.targetColor.green) <= tolerance &&
                    abs(color.blue - data.targetColor.blue) <= tolerance
                ) {
                    rgb = data.newColor.rgb
                }
                data.output.setRGB(x, y, rgb)
            }
        }
        ImageIO.write(data.output, "jpg", data.fileOutput)
        println("finished...")
    }
}


fun main(args: Array<String>) {
    val start = System.currentTimeMillis()

    val data = Data(name = "/images/pulp.jpg", targetColor = "000000", newColor = "3396ff", tolerance = 60)
    MainCaseOne().run(data)

    val end = System.currentTimeMillis()
    println("time: " + (end - start) + " milliseconds")
}