#!/bin/bash

NGRAM_JAR=target/scala-2.10/ngram_2.10-1.0.jar
CORPUS_FOLDER=/media/THING1/dryga/WordPrediction/cleaned_corpus_twitter
OUTPUT_FOLDER=./ngramTables

for FILE_NAME in $( ls $CORPUS_FOLDER ); do
    FULL_FILE_NAME=$CORPUS_FOLDER/$FILE_NAME
    echo $FULL_FILE_NAME
    for n in `seq 1 4`;
    do
        OUTPUT_NAME=$(basename $FULL_FILE_NAME)
        OUTPUT_NAME=${OUTPUT_NAME%.*}
        echo "ngram:" $n
        echo Tokenizer
        time scala -J-Xmx2g -classpath $NGRAM_JAR NGramToken $FULL_FILE_NAME -n $n > tokens.temp
        echo Counter
        time scala -J-Xmx2g -classpath $NGRAM_JAR NGramCounter tokens.temp -n $n > result.temp
        mv result.temp $OUTPUT_FOLDER/${OUTPUT_NAME}_${n}gram_count.tsv
        rm tokens.temp
        echo
    done
done
