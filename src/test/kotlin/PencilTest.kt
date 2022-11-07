import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PencilTest {
    @Test
    fun itCreates() {
        Pencil()
    }

    @Test
    fun itWritesText() {
        val subject = Pencil()
        val paper = Paper()

        val writtenText = subject.write("blarp", paper).read()

        assertEquals("blarp", writtenText)
    }

    @Test
    fun itAppendsTextToPaper() {
        val subject = Pencil()
        val paper = Paper("She sells sea shells")

        val writtenText = subject.write(" down by the sea shore", paper).read()

        assertEquals("She sells sea shells down by the sea shore", writtenText)
    }

    @Test
    fun itAppendsTextToPaperAfterEachWrite() {
        val subject = Pencil()
        val paper = Paper()

        val writtenText = subject.write("world", subject.write("hello ", paper)).read()

        assertEquals("hello world", writtenText)
    }

    @Test
    fun itDegradesDurabilityForLowerCase() {
        val subject = Pencil(3)
        val paper = Paper()

        val writtenText = subject.write("hello", paper).read()

        assertEquals("hel  ", writtenText)
    }

    @Test
    fun itDegradesDurabilityForUpperCase() {
        val subject = Pencil(3)
        val paper = Paper()

        val writtenText = subject.write("heLLO", paper).read()

        assertEquals("he   ", writtenText)
        assertEquals(1, subject.durability)
    }

    @Test
    fun itWritesNothing() {
        val subject = Pencil(0)
        val paper = Paper()

        val writtenText = subject.write("hello", paper).read()

        assertEquals("     ", writtenText)
    }

    @Test
    internal fun itSharpensBackToInitialDurability() {
        val initialDurability = 10
        val subject = Pencil(initialDurability)

        subject.write("hello", Paper())
        subject.sharpen()

        assertEquals(initialDurability, subject.durability)
    }

    @Test
    internal fun itSharpensAndReducesLength() {
        val subject = Pencil()

        subject.write("hello", Paper())
        subject.sharpen()

        assertEquals(99, subject.length)
    }

    @Test
    internal fun itOnlySharpensWhileLengthIsGreaterThanZero() {
        val subject = Pencil(durability = 1, length = 1)

        subject.write("hello", Paper())
        subject.sharpen()
        subject.write("hello", Paper())
        subject.sharpen()

        assertEquals(0, subject.durability)
        assertEquals(0, subject.length)
    }

    @Test
    internal fun itErasesTheLastOccurrenceOfWord() {
        val subject = Pencil()
        val paper = Paper("these are some words to check when you delete words and things")

        subject.erase("words", paper)

        assertEquals("these are some words to check when you delete       and things", paper.read())
    }

}