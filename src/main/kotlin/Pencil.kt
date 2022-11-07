open class Pencil {
    var durability: Int
    var length: Int = 100
        get() = field                     // getter
        private set
    private val initialDurability: Int

    constructor(durability: Int = 1000, length: Int = 100) {
        this.durability = durability
        this.initialDurability = durability
        this.length = length
    }

    open fun write(text: String, paper: Paper): Paper {
        val textToWrite = text.takeUntilWornOut(durability)
        durability -= getCost(textToWrite)
        return paper.append(textToWrite)
    }


    private fun String.takeUntilWornOut(durability: Int): String {
        var remainingDurability = durability
        var outString = ""
        for (index in indices)
            if (remainingDurability >= getCost(get(index)) && isValidChar(get(index))) {
                outString += get(index)
                remainingDurability -= getCost(get(index))
            } else {
                outString += ' '
            }
        return outString
    }

    fun isValidChar(char: Char): Boolean {
        return char != ' ' && char != '\n' && char != '\t'
    }

    fun getCost(char: Char): Int = when {
        char.isUpperCase() -> 2
        char.isLowerCase() -> 1
        else -> 0
    }

    fun getCost(text: String): Int {
        return text.sumOf { c -> getCost(c) }
    }

    fun sharpen() {
        if (length > 0) {
            this.durability = initialDurability
            this.length--
        }
    }

    fun erase(textToRemove: String, paper: Paper): Paper {
        return paper.eraseLastIntanceOf(textToRemove)
    }

}