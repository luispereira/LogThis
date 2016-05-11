# LogThis

##### Log any method on android by simply using annotation @LogThis #####

### Installation ###
```groovy
   buildscript {
     repositories {
       jcenter()
     }
     dependencies {
       classpath "com.github.luispereira:logthis-plugin:0.4.3"
     }
   }
   
   apply plugin: "com.lib.logthisannotations"   //This will search the annotation and will process them

   dependencies {
       debugCompile 'com.github.luispereira:logthisannotations:0.5.0'
       releaseCompile 'com.github.luispereira:logthisannotations-release:0.5.0'
   }
```

### Usage ###
In order to activate the Logger you should call the following method on Application
```java
    Logger.init();
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

Classes fields annotated by @LogThisClassFields will produce the following result:
```java
com.lib.logthis W/com.lib.logthis.MainActivity: ClassField ⇢ i -> oldValue=0 & newValue=1
```


### Write to File ###

In order to activate this feature the SDK should be initialized like this:
```java
    Logger.init().storeLogs(DIRECTORY_TO_SAVE_THE_LOG);
```

After this initialization is done the logs will be stored on a file with the name "LogThis.txt" on the specified directory.

Although the user can always avoid to write a determinate log on the annotation by doing:
```java
@LogThis(logger=LoggerLevel.V, write=false) //write to false turns off the store for these logs
public String logThisMethod(String value) {
  //...
  return "value";
 }
```


### Todo ###
- Field annotation to log a local variable when modified
- Provide an api method to write the logs on the sdcard
- Avoid any code on application class by creating a new module in order to enable the logger
