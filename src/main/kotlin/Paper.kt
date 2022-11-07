class Paper(var text: String = "") {
    open fun read(): String {
        return text
    }

    fun append(text: String): Paper {
        this.text += text
        return this
    }

    fun eraseLastIntanceOf(textToRemove: String): Paper {
        this.text = this.text.replaceLast(textToRemove, " ".repeat(textToRemove.length))
        return this
    }

    fun String.replaceLast(substring: String, replacement: String): String {
        val index = this.lastIndexOf(substring)
        return if (index == -1) this else this.substring(
            0,
            index
        ) + replacement + this.substring(index + substring.length)
    }
}