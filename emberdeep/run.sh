#!/usr/bin/env bash

cd src/main/java/ || exit 1

run_class="$1"
run_path="${run_class//./\/}"; run_path="${run_path%/*}"

echo "Compilando $run_path"
javac "$run_path"/*.java || exit 1

echo "--- Ejecutando $run_class ---"
echo ""
java $run_class
rm "$run_path"/*.class
echo ""
echo "--- Ejecutado $run_class ---"

cd - 
