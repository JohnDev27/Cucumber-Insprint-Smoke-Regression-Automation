# Cucumber-Insprint-Smoke-Regression-Automation
The objective is to build a light wight frame work capable of the in-sprint automaiton and Regression.  We focus to achive Parallel execution in sauce labs and detailed reports in Extent.
The Page Object folder has one class file per object. If the application follows a common DOM naming pattern, we could pass the label as a parameter and then dynamically create the Xpath and identify the object locator. This way of object identification is very easy and successfull for Products like Guidewire Claim Center, PC and for Angular JS UI screens, reducing the number of Classes to be created if we follow  convenional Page object Model.
The Soap Projects class had the methods to set properties to a SOAP Test and Execute SOAP test from Java.
The Web Driver Factory class had got the class to connect to various driver, Sauce labs as well to Head less browsers.
We use the Selenium-framework.properties file to store all the constant data .
The Data Storage Class has the methods to read and write data object, load a property file, read and update the values in the property file.
We have included the Jenkins file.
The Mave Build section in the Surefire plgin details include multi fork and helps execute the selenium test in Parallel.
Tags in the feature file can determine if the Tesst need to run as a part of insprint automation, smoke or Regression.
The Test Execution Report is achived using EXTENT reports .
