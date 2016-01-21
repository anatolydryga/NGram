import org.scalatest._
import scala.io.Source 

class NgramCounterSpec extends FlatSpec with Matchers{

    val sampleBigram = "The\tquick\nbrown\tfox\njumps\tover\nThe\tquick\n"

    "Bigram" should "have 3 unique ngrams" in {
        val sample = Source.fromString(sampleBigram)
        NGramCounter.ngramCount("bla", sample, 2) should have size 3
    }

    it should "fail if n is not equal to 2 for bigram" in {
        val sample = Source.fromString(sampleBigram)
        an [IllegalArgumentException] should be thrownBy 
            NGramCounter.ngramCount("bla", sample, 3)
    }

    it should "have expected counts" in {
        val sample = Source.fromString(sampleBigram)
        val res = NGramCounter.ngramCount("bla", sample, 2)
        res should contain ("brown\tfox" -> 1)
        res should contain ("jumps\tover" -> 1)
        res should contain ("The\tquick" -> 2)
    }
}
