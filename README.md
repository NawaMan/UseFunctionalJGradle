# UseFunctionalJGradle

![alt "Build status"](https://github.com/NawaMan/UseFunctionalJGradle/actions/workflows/maven.yml/badge.svg)

Example of how to use [FunctionalJ](https://github.com/NawaMan/FunctionalJ) in a Gradle project.

It basically boils down to adding the required maven repository (hosted by github).

```Groovy
repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compile             'io.functionalj:functionalj-all:0.5.2.0'      // Please look up the lastest version.
    annotationProcessor 'io.functionalj:functionalj-types:0.5.2.0'    // Please look up the lastest version.
}
```

See the full code here: [build.gradle](https://github.com/NawaMan/UseFunctionalJGradle/blob/master/build.gradle)
