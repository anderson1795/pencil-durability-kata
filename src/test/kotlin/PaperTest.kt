import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PaperTest {
    @Test
    fun itCreatesBlank() {
        val subject = Paper()

        val actualText = subject.read()

        assertEquals("", actualText);
    }

    @Test
    fun itCreatesWithText() {
        val subject = Paper("stuff")

        val actualText = subject.read()

        assertEquals("stuff", actualText);
    }

    @Test
    fun itAppendsText() {
        val subject = Paper()

        val actualText = subject.append("more").append("data").read()

        assertEquals("moredata", actualText);
    }

    @Test
    fun itRemovesLastInstanceOfText() {
        val subject = Paper("word 1 word 2")

        val actualText = subject.eraseLastIntanceOf("word").read()

        assertEquals("word 1      2", actualText);
    }


}