REPETITIONS=30

OUTPUT_FILE=output.csv

if [ -f $OUTPUT_FILE ]; then
    mv $OUTPUT_FILE "$OUTPUT_FILE.backup_`date`"
fi

if [ "$OSTYPE" == "linux-gnu" ]; then
    cp /proc/cpuinfo cpuinfo.txt
    cp /proc/meminfo meminfo.txt
    
    MEMORY=`grep MemTotal /proc/meminfo | awk '{print $2}'`
    MEMORY=$((MEMORY / 1000000 - 1)) # Leave 1 GB for OS
    JAVAFLAGS="-Xmx${MEMORY}G"
    echo $JAVAFLAGS
fi



javac Example.java

echo "" > output.csv

for (( i = 0; i < $REPETITIONS; i++ )); do
    # Alternate the order because of cache usage
    if [ $((i % 2)) -eq 0 ];
    then
        F1="-s"
        F2="-p"
    else
        F1="-p"
        F2="-s"
    fi
    java $JAVAFLAGS Example $F1 $i >> $OUTPUT_FILE
    java $JAVAFLAGS Example $F2 $i >> $OUTPUT_FILE
done