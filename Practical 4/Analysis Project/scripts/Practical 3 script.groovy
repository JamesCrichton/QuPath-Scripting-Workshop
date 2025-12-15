//Script to segment pancreas tissue section 

//1. Segment Tissue
resetSelection();  //make sure nothing's selected
createAnnotationsFromPixelClassifier("Tissue Thresholder", 100000, 0.0, "DELETE_EXISTING") // deletes pre-existing annotations

//2. Segment Islets
selectAnnotations();  //select the only annotation ("Tissue")
createAnnotationsFromPixelClassifier("Islet Classifier", 0.0, 0.0, "SPLIT")

//3. Segment Neurons
selectObjectsByClassification("Tissue") //Select the Tissue annotation fot eh classifier to run within 
createAnnotationsFromPixelClassifier("Neuron Classifier", 0.0, 0.0)

