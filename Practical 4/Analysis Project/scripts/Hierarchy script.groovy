//Which islet do cells belong to?
//Here, we're going to use this question to exlore the "Hierarchy"
//This script is written to be run one step at a time, gradually building up to form a complete script
//Only run a selection using Ctrl+Shift+R


//1. First, we only want to detect cells in Islets

    selectObjectsByClassification("Islet")
    
    runPlugin('qupath.imagej.detect.cells.WatershedCellDetection', '{"detectionImage":"DAPI","requestedPixelSizeMicrons":0.5,"backgroundRadiusMicrons":8.0,"backgroundByReconstruction":true,"medianRadiusMicrons":0.0,"sigmaMicrons":1.5,"minAreaMicrons":10.0,"maxAreaMicrons":400.0,"threshold":70.0,"watershedPostProcess":true,"cellExpansionMicrons":5.0,"includeNuclei":true,"smoothBoundaries":true,"makeMeasurements":true}')
    
    
//2. Look at the "Parent" value for each cell. It's an Islet - OK, but wuich one?!
//Next we're going to copy the unique Islet "ObjectID" to also be the islet's "Name", as a reference. 

    //Copy ID to Name
    getAnnotationObjects().each{anno ->
        if (anno.getPathClass()==getPathClass("Islet")) {
            IsletID=anno.getID().toString() //Get islet ObjectID. Make it a string
            anno.setName(IsletID)
        }
       } 
       
//3. Has this added the name of the Parent to the Child?
// In some cases you will need to resolve the hierarchy after making changes which affect existing annotations/detections

resolveHierarchy()
