.PHONY: build run test clean

build:
	mvn clean package

run:
	mvn spring-boot:run

test:
	mvn test

clean:
	mvn clean
	rm -rf target



