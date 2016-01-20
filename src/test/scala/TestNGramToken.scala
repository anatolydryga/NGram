import org.scalatest._

class SetSpec extends FlatSpec with Matchers{

    val sample = "The quick brown fox jumps over the lazy dog".split(" ")

    "Ngram" should "have correct number of ngrams" in {
        NGramToken.ngramize(1, sample) should have length 9
        NGramToken.ngramize(2, sample) should have length 8
        NGramToken.ngramize(8, sample) should have length 2
        NGramToken.ngramize(9, sample) should have length 1
    }

    it should "be empty for N in Ngram greater than number of words" in {
        NGramToken.ngramize(100, sample) should have length 0
    }

    it should "have all the words" in {
        NGramToken.ngramize(1, sample) should equal (sample)
    }

    it should "have only 2 8-grams" in {
        NGramToken.ngramize(8, sample, " ") should equal (Array(
            "The quick brown fox jumps over the lazy",
            "quick brown fox jumps over the lazy dog"
        ) )
    }

    it should "have tab separator by default" in {
        NGramToken.ngramize(8, sample)(0) should include regex "\t"
    }

    it should "separator can be changed to space" in {
        NGramToken.ngramize(8, sample, " ")(0) should include regex " "
    }
}
