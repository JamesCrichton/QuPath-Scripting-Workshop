/*
This file contains some information regarding the Groovy syntax
Downloaded from https://github.com/qupath/i2k2024/tree/main/workshops/groovy-scripters
Edited a little bit by James Crichton

Select each section and run using Ctrl+Shift+R

*/

//////////////////////
//////////////////////
//1. Strings - characters defined within quotation marks
//a

"Hello world" //this will just print automatically. This is not always the case. You often need to explicitly "println"
    
println "something else"
    

//////////////////////
//b. Save to a variable and print (better if you're using it later)

variable = "Hello world" // the name can be anything, it doesn't have to be "variable"

println variable // all these ways of pringint work
println(variable)


//////////////////////
//c Variables can be defined as a specific class of object explicitly i.e. this is a string. These cannot be overridden

String someVariable = "Some value"
String someVariable = "Some other value" // this one wil fail as the variable "someVariable " has already been defined


//////////////////////
//d. A slightly less explicit way of declaring a variable is to use 'def' or 'var' (recent addition). These show that Groovy is guessing the class of the object i.e. that it is a String (more later). Again, these cannot be overridden. 

var someVariable = "Some value"
println someVariable 

def someOtherVariable = "another variable"
println someOtherVariable 


//Variables can be overridden if var, def or explicit classifiactions aren't used
someOtherVariable = "something"
someOtherVariable = "something else"


//////////////////////
//e. Variables can be inserted into expressiong using $
var someVariable = 24
println "The value of someVariable is ${someVariable}"

// Unlike in ImageJ, semi-columns at line ends are optional


//////////////////////
//////////////////////
//2. Lists
//Use '[]' to create a list:
var someList = []


//////////////////////
//a. Use 'add', '<<', or 'addAll' to add elements to a list:
var someList = []
someList.add "someElement0"
someList << "someElement1"
someList.addAll "someElement2", "someElement3"

println someList


//////////////////////
//b. Use '[i]' to access the (i+1)th element of a list:

var someList = []
someList.add "someElement0"
someList << "someElement1"
someList.addAll "someElement2", "someElement3"

println someList[2]     // print the third element
println someList[-1]    // print the last element



//////////////////////
//////////////////////
//3. Looping
//a. Use 'each' to iterate over a list:
var someList = ["someElement0", "someElement1", "someElement2", "someElement3"]

someList.each { element -> //here, the variable name "element" can be anything
    println element
}

//////////////////////
//b. Use 'findAll' to filter a list
var someList = ["someElement0", "someElement1", "someElement2", "someElement3"]

var elementsEndingWith1 = someList.findAll { element ->
    element.endsWith("1")
}
println elementsEndingWith1    // will print [someElement1]


//////////////////////
//c. Use 'collect' to transform elements of a list
var someList = ["someElement0", "someElement1", "someElement2", "someElement3"]

var someListUpperCased = someList.collect { element ->
    element.toUpperCase()
}
println someListUpperCased    // will print [SOMEELEMENT0, SOMEELEMENT1, SOMEELEMENT2, SOMEELEMENT3]


