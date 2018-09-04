#Bora Mutluoglu | bmutluog | 1564633 | cs12b | January 26, 2018
#this Makefile creates and archives the Search.class into an executable
#jar file and then removes the .class file
Search: Search.class
	echo Main-class: Search > Manifest
	jar cvfm Search Manifest Search.class
	rm Manifest
	chmod +x Search

Search.class: Search.java
	javac -Xlint Search.java

clean:
	rm -f Search Search.class

