# LogThis

##### Log any method on android by simply using annotation @LogThis #####

This should not be used in production environment yet.

### Installation ###
 >  compile 'com.github.luispereira:logthisannotations:0.1.0'

### Usage ###
>   @LogThis
    public void logThisMethod(String value) {
      //...
    }

</br>
>   logThisMethod("test")

### Output ###
Every time you call the method the follow output will appear
> com.lib.logthis D/Class: Method -> logThisMethod(value="test") called

### Todo ###
- Class annotation to log every method of a class
- Log every variable change whitin the method
- Create a method to enable and disable the log
