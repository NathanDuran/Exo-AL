# Exo-AL
Exoplanet Candidate Detection with Active Learning AI

Welcome to the Exo-AL project! Our goal is to try and find exoplanet candidates in data
collected by NASA's Kepler spacecraft. To accomplish this we are using an Artificial
Intelligence system that is capable of classifying stars as either exoplanet hosts or non-
hosts. When the AI systems 'confidence' is low for a stars classification it will ask for
human assistance in a process called Active Learning. That's where you come in. On the
classify page of this site you will be shown a series of stars 'light curves' and asked
whether you think it may, or may not, be host to an orbiting exoplanet. By asking you to
help the AI to classify stars we hope to improve its ability to classify stars in the future.
Think of it as 'teaching' the AI to be better at its job.

-You can find the project at: ~~www.nated.co.uk~~

-Datasets were created from the raw light curve data from the Kepler mission
available from: https://exoplanetarchive.ipac.caltech.edu/index.html

-A set of statistical features were created for each stars light curve using a separate feature extraction module.

-Due to the size of the full light curve dataset a smaller dataset was created from
20 light curves and has been included: Exo-AL/web/resources/datasets/ValidationSetTEST.arff

-The full datasets are also included in the above folder.

-The corresponding 20 light curves are contained in Exo-AL/web/resources/lightcurves/
making the program fully functional but scaled down.

# FeatureExtraction Module
Kepler Light Curve Feature Extraction

The feature extraction software converts Kepler Light Curve data into a set of statistical features
for use with the Weka machine learning classifiers.

-Light Curve data must be included in .csv format in the resource directory

-The list of stars to be processed must be included as text file lists of stars by Kepler ID

-To run the JAR from within its directory: java -jar FeatureExtraction.jar {host star list} {non-host star list} {set name}

-Two small test sets are included for demonstration purposes "HostTEST" and "NonHostTEST"

-The output .arff file will be created in the /resources/output directory
