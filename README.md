# Project-AI-JAVA-ANNOTATION-2021
 Train AI model to learn Java code Error and Error location base on base on Performer structure
=================================================================================
 Manual:
 
 Each folder contain difference function.

  Code-Augmentation:
    Reference to Enrichment java code.

  Data-Generator:
    Make data split to several files to training.
    Caution: Not use for a while, please check before use it.

  Java-Get-XML-Attribute:
    Get XML file attribute out and write to excel file.
    Array version is to make error begin and end line to one in the array.
    Value version is to make error begin and end file to value.

  Java-Libraries:
    Essential pack for java to operation.
    Caution: May need to reinstall file.

  New-Ver-Python-Debugger:
    Enrich java file after Normalize original file.
    Caution: May have some bug.

  Paper:
    Research paper.

  Performer:
    This folder Contain Performer main file, Keras files and files to rate models score.
    Need to Know: V5 is the final version, V6 is test about multiple network layer in Tensorflow.

  Project-XML-Error:
    This program makes java file to xml file and show error type and message.

  Source-Code:
    java source code.
    Caution: Not use for a while.

  Suggestion-Message-Adjustment-SourceCode:
    Java source code and Message Adjustment folder.

  Training:
    Training dataset.

  Transformer:
    Tensorflow base on Transformer structure.
    Caution: Not use for a while.

  System Spec recommend:

      CPU:       intel i7 12700 or AMD Ryzen 5800x or higher

      Ram:       32GB or higher

      GPU:       RTX3080Ti or higher VRam Graphic Card

      Storage:   1TB SSD or higher

  My System Spec:

      CPU:       AMD Ryzen 5950x

      Cooling:   NZXT Kraken Z73

      Ram:       Crucial Ballistix DDR4 3600 64GB

      MB:        Asus CROSSHAIR-VIII-DARK-HERO

      GPU:       NVIDIA RTX 3090 24GB

      Storage:   Seagate Firecuda 530 & WD SN750

      Power:     Seasonic PX-1300

  Before We Start.

  Please install blue j for JAVA compiler.
  Blue j version require 5.0.0 or newer version.
  Install XFFS package for JAVA.

  Please use Version Control in Performer V5's version Control folder and download Python package for Tensorflow.
  Caution: please use VersionControl.ipynb and follow the instruction.

  Prepare for Training models.

  1.Use Project-XML-Error create XML file to preserve JAVA Source Code and Error type and Error location and message.

  2.Use Python-Debugger to Enrich training files.

  3.Use Java-Get-XML-Attribute to get file source code, error type, error line, error message, and make excel file and txt files.

  Ready for Training.

  1.Put Training files to Training folder, and use Main.ipynb to run Tensorflow program to Training models.

  2.Training X Y file and models store path can be change in Main.ipynb file, if the path was created.

  3.Model-for-training and Model-for-training-org folder are use for store x, y training files. org folder is to store original files.

  Rate your models.

  1.In ModelScoreRater.ipynb, give the x, y training files path and models path, and run it. This program will show your model's score.
