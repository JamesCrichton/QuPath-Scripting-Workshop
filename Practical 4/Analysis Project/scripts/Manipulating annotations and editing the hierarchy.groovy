//Here we're going to make new annotations; rings around the islet annotations. We then want to link those to their parent annotation in the hierarchy


//1. First we want to try selecting an islet and making it bigger, using "buffer"

    getAnnotationObjects()[0].getROI().getGeometry().buffer(10) //this calculates a polygon for our first annotation, enlarged by 10 pixels
    

//2. Next we want to chop out the centre of this annotation by calculating a polygon for the difference between the dilated shape and the original

    original_geom=getAnnotationObjects()[0].getROI().getGeometry()
    dilated_geom=original_geom.buffer(10) //this calculates a polygon for our first annotation, enlarged by 10 pixels
    
    ring_geom=dilated_geom.difference(original_geom) //Calculate a shape which is the difference between the original and the dilated annotation
    
    println ring_geom //print the new shape
    

//3. Making this into a new annotation

    original_geom=getAnnotationObjects()[0].getROI().getGeometry()
    dilated_geom=original_geom.buffer(10) //this calculates a polygon for our first annotation, enlarged by 10 pixels
    
    ring_geom=dilated_geom.difference(original_geom) //Calculate a shape which is the difference between the original and the dilated annotation
    
    
    //Make the new geom into an ROI
    ring_ROI = GeometryTools.geometryToROI(ring_geom, ImagePlane.getDefaultPlane())
    
    //Make the ROI into an annotation object with classification "Islet Ring"
    newAnnotation = PathObjects.createAnnotationObject(ring_ROI, getPathClass("Islet Ring"))
    addObject(newAnnotation)
    
//4. You should now be able to see this new anotation. Does it have a Parent in the data? 
// Try to assign the parent of this annotation automaticaly

    resolveHierarchy()
    
    
//5. It now has a parent (Tissue), but not the one we want (the Islet it was derived from)
    
    //Remove pre-existing "Islet Ring" annotations
    IsletRingAnnotations = getAnnotationObjects().findAll{anno -> anno.getPathClass()==getPathClass("Islet Ring")}       
    removeObjects(IsletRingAnnotations, true)
    
    //Make the new ring annotation 
    islet_annotation = getAnnotationObjects()[0]
    original_geom=islet_annotation.getROI().getGeometry()
    dilated_geom=original_geom.buffer(10) //this calculates a polygon for our first annotation, enlarged by 10 pixels
    ring_geom=dilated_geom.difference(original_geom) //Calculate a shape which is the difference between the original and the dilated annotation
    
    //Make the new geom into an ROI
    ring_ROI = GeometryTools.geometryToROI(ring_geom, ImagePlane.getDefaultPlane())
    
    //Make the ROI into an annotation object with classification "Islet Ring"
    newAnnotation = PathObjects.createAnnotationObject(ring_ROI, getPathClass("Islet Ring"))  
    addObject(newAnnotation)
    
    //Set the annotation as a child of the original islet it was derived from
    islet_annotation.addChildObject(newAnnotation) 







new_annotationID = newAnnotation
    islet_annotation 
    
    describe(getAnnotationObjects()[0])

    islet_annotation .setName(isletID)
    
    //
    original_geom=getAnnotationObjects()[0].getROI().getGeometry()


    










getAnnotationObjects().each{anno ->
        if (anno.getPathClass()==getPathClass("Islet")) {
            IsletID=anno.getID().toString() //Get islet ObjectID. Make it a string
            anno.setName(IsletID)
        }
       } 
