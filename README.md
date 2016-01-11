# LogThis

##### Log any method on android by simply using annotation @LogThis #####

This should not be used in production environment yet.

### Installation ###
```groovy
    repositories {
        jcenter()
    }

   compile 'com.github.luispereira:logthisannotations:0.3.1'
```

### Usage ###
In order to activate the Logger you should call the following method on Application
```java
 if(BuildConfig.DEBUG) { //This verification is only an example to only call the logger on debug environment
    Logger.init();
 }
```

From now on the methods with annotation @LogThis will be logged


```java
@LogThis(LoggerLevel.V) //The default value is LoggerLevel.D
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
- Field annotation to log a field when called
- Provide an api method to write the logs on the sdcard
- Avoid any code on application class by creating a new module in order to enable the logger
