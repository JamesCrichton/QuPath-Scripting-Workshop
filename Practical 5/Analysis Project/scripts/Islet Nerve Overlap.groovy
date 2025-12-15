//Calculating the area of Nerve annotaitons whch intersects with each islet annotation

//Again, this is a step by step script which gradually builds up to perform this task. Each step is written to be run in isolation by selecting and pressing Ctrl+Shift+R

//1. Calculating intersections requires comparing shapes as "geometries" using the intersection(), look in describe(RoiTools) for guidance (note this actually requires a "Geometry" not an "ROI"). 
//First we need to be able to access the ROI data for an annotation

    example_annotation = getAnnotationObjects()[1] //The first annotation in the total list
    
    example_annotation_ROI = example_annotation.getROI()
    
    println example_annotation_ROI //First we're printing the ROI to see what it's like (a polygon defining its shape. These coordinates are actually its bounding box)
    
    describe(example_annotation_ROI) //Use "describe" to see what you can do this ROI data (note, getGeometry())
    

//2. intersection() is run on a Geometry, and requires another Geometry for comparison, to give the overlap. So, we need to turn our islet and nerve annotations into Geometries to fit into this command

    example_annotation = getAnnotationObjects()[1] //The first annotation in the total list
    example_annotation_Geom = example_annotation.getROI().getGeometry() //you can now see all the coordinates involved in the polygon
    
    describe(example_annotation_Geom) //see what can now be done to a "Geometry"
    
    
//3. Get a Nerve Geometry and an Islet Geometry and compare their intersection

    var NerveAnnotation= getAnnotationObjects().findAll{anno-> anno.getPathClass()==getPathClass("Nerve")} //Loop through annotations and get the "Nerve" ones
    var IsletAnnotations= getAnnotationObjects().findAll{anno-> anno.getPathClass()==getPathClass("Islet")} //Loop through annotations and get the "Islet" ones
    
    NerveGeom=NerveAnnotation[0].getROI().getGeometry() //Choose the single annotation (position 0) and get the Geometry
    ExampleIsletGeom=IsletAnnotations[0].getROI().getGeometry() //Get an example Islet geometry
    
    IntersectionGeom = ExampleIsletGeom.intersection(NerveGeom) // Calculate the intersection. Note this is a "Geometry Collection" i.e. multipe polygons defining the overlap
    
    describe(IntersectionGeom) // What can we do with this? Note getArea()
    
    
 //4. Calculate the area of intersection. Also calculate the Area of the islet for reference (see what the units are - is it what you expect??)
    
    selectAllObjects()
    addShapeMeasurements("AREA")//This is being added for reference later 
 
    var NerveAnnotation= getAnnotationObjects().findAll{anno-> anno.getPathClass()==getPathClass("Nerve")} //Loop through annotations and get the "Nerve" ones
    var IsletAnnotations= getAnnotationObjects().findAll{anno-> anno.getPathClass()==getPathClass("Islet")} //Loop through annotations and get the "Islet" ones
    
    NerveGeom=NerveAnnotation[0].getROI().getGeometry() //Choose that single annotation (position 0) and get the Geometry
    ExampleIsletGeom=IsletAnnotations[0].getROI().getGeometry() //Get an example Islet geometry
    
    IntersectionGeom = ExampleIsletGeom.intersection(NerveGeom) // Calculate the intersection. Note this is a "Geometry Collection" i.e. multipe polygons defining the overlap
    
    println "Calculated intersection area = "+IntersectionGeom.getArea().toString()
    println "Calculated islet area = "+ExampleIsletGeom.getArea().toString()
    println "Area in um^2 already calculated ="+ IsletAnnotations[0].getMeasurementList().get("Area µm^2")
    
    
//5. The reason these measurements differ is because one is measured in px (ours), the other is measured in um. 
//We need to rescale our measurements to um^2. 

    um_per_px = getCurrentServer().getPixelCalibration().getPixelHeight() //Use this to correct the scale of our calculation
    um_per_px_sq = um_per_px*um_per_px 
    
    selectAllObjects()
    addShapeMeasurements("AREA")//This is being added for reference later 
 
    var NerveAnnotation= getAnnotationObjects().findAll{anno-> anno.getPathClass()==getPathClass("Nerve")} //Loop through annotations and get the "Nerve" ones
    var IsletAnnotations= getAnnotationObjects().findAll{anno-> anno.getPathClass()==getPathClass("Islet")} //Loop through annotations and get the "Islet" ones
    
    NerveGeom=NerveAnnotation[0].getROI().getGeometry() //Choose that single annotation (position 0) and get the Geometry
    ExampleIsletGeom=IsletAnnotations[0].getROI().getGeometry() //Get an example Islet geometry
    
    IntersectionGeom = ExampleIsletGeom.intersection(NerveGeom) // Calculate the intersection. Note this is a "Geometry Collection" i.e. multipe polygons defining the overlap
    
    CalculatedIntersectionArea=IntersectionGeom.getArea()*um_per_px_sq
    CalculatedIsletArea=ExampleIsletGeom.getArea()*um_per_px_sq
    
    println "Calculated intersection area = "+CalculatedIntersectionArea.toString()
    println "Calculated islet area = "+CalculatedIsletArea.toString()
    println "Area in um^2 already calculated ="+ IsletAnnotations[0].getMeasurementList().get("Area µm^2")
    println "Phew, this look OK now :)"

    
//6. Now lets's add this new measurement into the data for this islet
   
    um_per_px = getCurrentServer().getPixelCalibration().getPixelHeight() //Use this to correct the scale of our calculation
    um_per_px_sq = um_per_px*um_per_px 

    selectAllObjects()
    addShapeMeasurements("AREA")//This is being added for reference later 
 
    var NerveAnnotation= getAnnotationObjects().findAll{anno-> anno.getPathClass()==getPathClass("Nerve")} //Loop through annotations and get the "Nerve" ones
    var IsletAnnotations= getAnnotationObjects().findAll{anno-> anno.getPathClass()==getPathClass("Islet")} //Loop through annotations and get the "Islet" ones
    
    NerveGeom=NerveAnnotation[0].getROI().getGeometry() //Choose that single annotation (position 0) and get the Geometry
    ExampleIsletGeom=IsletAnnotations[0].getROI().getGeometry() //Get an example Islet geometry
    
    IntersectionArea= ExampleIsletGeom.intersection(NerveGeom).getArea()*um_per_px_sq // Calculate the intersection area and scale to um^2
    
    //This is where we can add the data to the annotation measurements
    IsletAnnotations[0].getMeasurements().put("Overlapping Nerve Area", IntersectionArea)
    
    selectObjects(IsletAnnotations[0]) //Select this object on the image so you can see it. Look at its measurements and see the "Overlapping Nerve Area"
    
    

//7. Finally, we want to calculate this for all the islets. We do this by adding it into our original loop 

    um_per_px = getCurrentServer().getPixelCalibration().getPixelHeight() //Use this to correct the scale of our calculation
    um_per_px_sq = um_per_px*um_per_px 
    
    selectAllObjects()
    addShapeMeasurements("AREA")//This is being added for reference later 
 
    var NerveAnnotation= getAnnotationObjects().findAll{anno-> anno.getPathClass()==getPathClass("Nerve")} //Loop through annotations and get the "Nerve" ones
    NerveGeom=NerveAnnotation[0].getROI().getGeometry() //Choose that single annotation (position 0) and get the Geometry

    //Loop through all annotations and calculate nerve overlap exclusively for Islets
    getAnnotationObjects().each{anno ->
    if (anno.getPathClass()==getPathClass("Islet")) {
        IsletGeom=anno.getROI().getGeometry() //Get Islet geometry   
        IntersectionArea= IsletGeom.intersection(NerveGeom).getArea()*um_per_px_sq // Calculate the intersection area and scale to um^2
        anno.getMeasurements().put("Overlapping Nerve Area", IntersectionArea)
    }
   } 




