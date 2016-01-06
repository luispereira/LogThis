# LogThis

##### Log any method on android by simply using annotation @LogThis #####

This should not be used in production environment yet.

### Installation ###
```groovy
    repositories {
        jcenter()
    }

   compile 'com.github.luispereira:logthisannotations:0.2.0'
```

### Usage ###
```java
@LogThis
public String logThisMethod(String value) {
  //...
  return "value";
 }

 logThisMethod("test")
```

### Output ###
Every time you call the method the follow output will appear:
```java
com.lib.logthis D/Class: Method -> logThisMethod(value="test") called
com.lib.logthis D/Class: Method -> logThisMethod(value="test") returned value -> [value]
```
Method which have the return generic type void will not print the return result.

### Todo ###
- Class annotation to log every method of a class
- Create a method to enable and disable the log
