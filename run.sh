#!/bin/bash
# Compile and run the Java Inventory Manager

SRC_DIR="src"
OUT_DIR="out"

echo "Compiling Java Inventory Manager..."
mkdir -p $OUT_DIR

javac -sourcepath $SRC_DIR -d $OUT_DIR \
    $SRC_DIR/model/Product.java \
    $SRC_DIR/util/CsvHandler.java \
    $SRC_DIR/service/InventoryService.java \
    $SRC_DIR/Main.java

if [ $? -eq 0 ]; then
    echo "Compilation successful. Launching app..."
    java -cp $OUT_DIR Main
else
    echo "Compilation failed. Please check the errors above."
    exit 1
fi
