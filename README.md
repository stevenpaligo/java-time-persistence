# ThreeTen-Extra JPA
This project contains utilities for persisting objects from the [ThreeTen-Extra library](https://www.threeten.org/threeten-extra/) using the [Java Persistence API (JPA)](https://en.wikipedia.org/wiki/Java_Persistence_API).

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.stevenpaligo/threeten-extra-jpa/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.stevenpaligo/threeten-extra-jpa)
[![Javadoc](https://javadoc.io/badge/com.stevenpaligo/threeten-extra-jpa.svg)](http://www.javadoc.io/doc/com.stevenpaligo/threeten-extra-jpa)



## Getting Started


### Prerequisites
ThreeTen-Extra JPA requires Java 8+ and JPA. The first two portions of this project's version number correspond to the JPA version that is required (e.g. 2.2.0 requires JPA 2.2).


### Installation
To include ThreeTen-Extra JPA in a [Maven](https://maven.apache.org/) project, add the following dependency:

```xml
<dependencies>
  ...
  <dependency>
    <groupId>com.stevenpaligo</groupId>
    <artifactId>threeten-extra-jpa</artifactId>
    <version>${threeten-extra-jpa.version}</version>
  </dependency>
  ...
</dependencies>
```

For non-Maven projects, download the JAR from Maven's [Central Repository](http://repo1.maven.org/maven2/com/stevenpaligo/threeten-extra-jpa/). The list of dependencies can be found in the pom.xml file (see the source on [GitHub](https://github.com/stevenpaligo/threeten-extra-jpa))



## Usage
Simply add this project as a Maven dependency or as a JAR on the classpath. The JPA converters will be discovered automatically.

The following table lists the valid combinations of ThreeTen-Extra classes and the database types:

| ThreeTen-Extra Class 	| JPA-Supported Type 	| Database Type(s) 	| Comments                                                                                      	|
|----------------------	|--------------------	|------------------	|-----------------------------------------------------------------------------------------------	|
| TaiInstant           	| BigDecimal         	| DECIMAL, NUMERIC 	| To correctly store the value, the database field must have a precision of 28 and a scale of 9 	|
| TaiInstant           	| String             	| CHAR, VARCHAR    	| To correctly store the value, the database field must have a length of at least 35 characters 	|
| UtcInstant           	| String             	| CHAR, VARCHAR    	| To correctly store the value, the database field must have a length of at least 30 characters 	|

See the JavaDoc for more information.



## Contributions

Contributions (bug reports, feature requests, etc.) are always welcome and should be coordinated through the [GitHub Issues](https://github.com/stevenpaligo/threeten-extra-jpa/issues) system.



## Authors

* **Steven Paligo** - [stevenpaligo.com](http://stevenpaligo.com)

See also the list of [contributors](https://github.com/stevenpaligo/threeten-extra-jpa/graphs/contributors) who participated in this project.



## License

This project is licensed under the Apache License Version 2.0. See the [license file](LICENSE) file for details.
