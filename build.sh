
cd base
mvn -U $1 -Dmaven.test.skip=true

cd ../commons
mvn -U $1 -Dmaven.test.skip=true

cd ../starters
mvn -U $1 -Dmaven.test.skip=true
