//Aim 1: Looping over just the Islet annotaitons
//This is a step by step guide to think through this process and how the code works
//This is written so you should only run one step at a time and gradually build up to have a complete script (select code and press Ctrl+Shift+R)


//1. What are the commands available in the "QP" library?

describe(QP)


//2. getAnnotationObjects() will return a "Collection" of the annotation objects.

getAnnotationObjects()


//3. How long is this list?

getAnnotationObjects().size()


//4. Let's look at a single element from this list e.g. the first one, position 0

getAnnotationObjects()[0]


//5. This doesn't look like much. What can we do with it? Use describe() to find out. 
// You'll see that there's loads on information which can be accessed/added

describe(getAnnotationObjects()[1])


//6. First, we want to find the class of this annotation object

getAnnotationObjects()[1].getPathClass()


//7. Let's make a boolean (true/false) for whether an object is an "Islet" or not
// Note, just asking if it's equal to "Islet" is not enough, it as to be getPathClass("Islet"). 

getAnnotationObjects()[1].getPathClass()==getPathClass("Islet")


//8. We can now loop over this data and pull out just the "true" elements (i.e. the Islets) to make a new collection of objects. Note, "anno" is being used as a variable name as we loop through the elements in the collection. When using findAll() this returns on the "true" results. We could save them as "IsletAnnotations"

var IsletAnnotations = getAnnotationObjects().findAll{anno -> 
anno.getPathClass()==getPathClass("Islet")
}

IsletAnnotations.size()//Check how long this new collection is (should be 2 smaller, without the Tissue and Nerve annotations)


//9. Ultimately, we want to do more, only to the Islet annotations. We don't actually need to re-save these to a new variable to do that. We could just have a conditional "if" statement selectively performing further tasks on just the Islets. To do this, we'll use "each" rather then "findAll" to control the loop.

counter=0

getAnnotationObjects().each{anno -> 

if (anno.getPathClass()==getPathClass("Islet")) {
    string_of_class_name= anno.getPathClass().toString()
    string_of_counter_number=counter.toString()
    
    println string_of_class_name+string_of_counter_number

    counter+=1 //increase value of counter by 1 with each loop
    }
   } 

