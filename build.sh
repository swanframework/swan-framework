

goals=$1

if [ x$1 == "x" ]; then
  goals="clean"
fi

cd base
mvn -U $goals -Dmaven.test.skip=true

cd ../commons
mvn -U $goals -Dmaven.test.skip=true

cd ../starters
mvn -U $goals -Dmaven.test.skip=true
