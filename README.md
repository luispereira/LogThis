# LogThis

##### Log any method on android by simply using annotation @LogThis #####

### Installation ###
```groovy
    repositories {
        jcenter()
    }

   compile 'com.github.luispereira:logthisannotations:0.4.0'
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

It is also possible to Log every field change:

```java
@LogThis
public String mSampleField = "test1";

...

mSampleField = "test2";
```

Or you can log every field of a class:
```java
@LogThisClassFields(LoggerLevel.I)
public class SampleClass{

   private String mValue;
   public int otherField = 0;

}
```
In this case every field on the class will be logged.

### Output ###
Every time you call the method the follow output will appear:
```java
com.lib.logthis D/Class: Method -> logThisMethod(value="test") called
com.lib.logthis D/Class: Method -> logThisMethod(value="test") returned value -> [value]
```
Method which have the return generic type void will not print the return result.

Or in case of a field log:
```java
com.lib.logthis D/Class: Field ⇢ mSampleField -> oldValue=test1 0 & newValue=test2
```

### Todo ###
- Field annotation to log a local variable when modified
- Provide an api method to write the logs on the sdcard
- Avoid any code on application class by creating a new module in order to enable the logger
