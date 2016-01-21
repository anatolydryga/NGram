import scala.io.Source
import collection.mutable.HashMap

object NGramCounter {

    def checkNgram(line: String, inputFile: String, n:Int) = {
        val ngram = line.split("\t")
        if (ngram.length != n) throw new IllegalArgumentException(
            "input file %s has line '%s' that does not have %d-gram%n".
            format(inputFile, line, n))
    }

    def ngramCount(inputFile: String, input: Source, n:Int) = {
        val ngramCount = new HashMap[String, Int]() { 
            override def default(key: String) = 0 
        }
       for (line <- input.getLines) {
            checkNgram(line, inputFile, n)
            ngramCount += ( line -> (ngramCount(line) + 1))
        }
        ngramCount
    }

    def main(args: Array[String]): Unit = {
        if (args.length != 3) {
            println("Usage: NGramCounter input_file -n number")
            System.exit(-1)
        }
        val ngramTotal = ngramCount(args(0), Source.fromFile(args(0)), args(2).toInt)
        val sortedNgrams = ngramTotal.toList sortBy { - _._2}
        for (entry <- sortedNgrams) {
            println(entry._1 + "\t" + entry._2)
        }
    }
}
