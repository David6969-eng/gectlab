mkdir -p lib
cd lib
wget https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.3.0/mysql-connector-j-8.3.0.jar
mv mysql-connector-j-8.3.0.jar mysql.jar
cd ..

Then run the below code

javac -cp ".:lib/mysql.jar" *.java
java -cp ".:lib/mysql.jar" LibraryViewer
