class FileProvider {

    fun readFileAsLines(fileName: String): List<String> {
        return FileProvider::class.java.getResourceAsStream("/advent/$fileName")?.bufferedReader()?.readLines()
            ?: throw RuntimeException("Failed to read file")
    }

    fun readFileAsString(fileName: String): String {
        return FileProvider::class.java.getResourceAsStream("/advent/$fileName")?.bufferedReader()?.readText()
            ?: throw RuntimeException("Failed to read file")
    }
}