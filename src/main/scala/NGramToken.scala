import scala.io.Source

object NGramToken {

    def ngramize(n:Int, words: Array[String], separator:String = "\t") = {
        val nTotal = words.length - n + 1
        val ngrams = Array.fill[String](nTotal)("")
        for (i <- 0 until nTotal) {
            ngrams(i) = words(i)
            for (j <- i + 1 until i + n) {
                 ngrams(i) = ngrams(i) + separator + words(j)
            }
        }
        ngrams
    }

    def main(args: Array[String]): Unit = {
        if (args.length != 3) {
            println("Usage: NGramToken input_file -n number")
            System.exit(-1)
        }
        val inputFile = args(0)
        val n = args(2).toInt
        for (line <- Source.fromFile(inputFile).getLines) {
            ngramize(n, line.split(" ")).foreach(println)
        }
    }
}
