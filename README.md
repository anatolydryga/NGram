# NGram
generate n-grams and frequencies 

# Compilation

In bash:
```
sbt test
sbt compile
sbt package
```

# N-gram Generation

To generate all n-grams of size 5 for a file input.txt:
```
scala -classpath target/scala-2.10/ngram_2.10-1.0.jar NGramToken src/test/resources/sample_test.txt -n 2
```
All n-grams will be printed to stdout with tab as separator.
