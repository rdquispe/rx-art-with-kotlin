import java.awt.image.BufferedImage
import java.io.File
import java.nio.file.Paths
import javax.imageio.ImageIO
import java.awt.Color


class Data(name: String, targetColor: String = "000000", newColor: String = "ff0077", tolerance: Int) {

    var input: BufferedImage
    var output: BufferedImage
    var targetColor: Color
    var newColor: Color
    var tolerance: Int
    var fileOutput: File

    init {
        val path = Paths.get("src", "main", "resources").toFile().absolutePath
        val file = File(path + name)
        this.input = ImageIO.read(file)
        val fileName = name.split(".")
        this.fileOutput = File("$path${fileName[0]}-out.${fileName[1]}")
        this.output = BufferedImage(input.width, input.height, BufferedImage.TYPE_INT_RGB)
        this.targetColor = Color(Integer.decode("0x$targetColor"))
        this.newColor = Color(Integer.decode("0x$newColor"))
        this.tolerance = tolerance
    }
}