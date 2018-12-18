# UseFunctionalJGradle

![alt "Build status"](https://travis-ci.org/NawaMan/UseFunctionalJGradle.svg?branch=master)

Example of how to use ![alt "FunctionalJ"](https://github.com/NawaMan/FunctionalJ) in a Gradle project.

It basically boils down to adding the required maven repository (hosted by github).

```Groovy
repositories {
    jcenter()
    maven { url 'https://raw.githubusercontent.com/nawmaman/nawaman-maven-repository/master/' }
}

dependencies {
    compileOnly 'org.projectlombok:lombok:1.16.16'
    compile     'functionalj:functionalj-core:0.1.59.0'    // Please look up the lastest version.
}
```

See the full code here: [build.gradle](https://github.com/NawaMan/UseFunctionalJGradle/blob/master/build.gradle)