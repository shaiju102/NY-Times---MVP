# Android MVP Architecture: Sample App
This repository contains a detailed sample app that implements MVP architecture.

The app basically fetch certain news feeds and list them in a recycler view

Prerequisites
--------------

- Android Studio 3.1
- Android SDK v27
- Latest Android Build Tools
- Android Support Repository

#Technology used
* Clean MVP architecture
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide)
* [Espresso]()


#### The app has following packages:
1. **model**: It contains all the data accessing and manipulating components.
2. **view**: It contans the view classes like Activitiy, Fragment.
3. **presenter**: It contains the presenter class that present the data to view.
4. **network**: Retrofit classes .
4. **callback**: General interfaces.

#### Steps to run the app & Test:
- Download the project code, preferably using `git clone`.
- Open the Android SDK Manager (*Tools* Menu | *Android*) and make sure you have installed the *Android testing support library Repository* under *Extras*.
- In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
- Check out the relevant code:
    * The application under test is located in `src/main/java`
    * Tests are in `src/androidTest/java`
- Create the test configuration with a custom runner: `android.support.test.runner.AndroidJUnitRunner`
    * Open *Run* menu | *Edit Configurations*
    * Add a new *Android Tests* configuration
    * Choose a module
    * Add a *Specific instrumentation runner*: `android.support.test.runner.AndroidJUnitRunner`
- Connect a device or start an emulator
    * Turn animations off.
    (On your device, under Settings->Developer options disable the following 3 settings: "Window animation scale", "Transition animation scale" and "Animator duration scale")
- Run the newly created configuration

The application will be started on the device/emulator and a series of actions will be performed automatically.

#### ANDROID-TEST COVERAGE REPORT

- find createDebugCoverageTest task or execute the command gradlew createDebugCoverageReport from the project root in the terminal;
- double click;
- wait for all tests to run
- Note: you should have your test emulator launched or an Android device connected.
- When Android Studio notifies you that everything is OK, you'll be able to find a test coverage report at “YOUR_PROJECT_PATH\app\build\reports\coverage\debug\index.html”:


License
--------


    Copyright 2017 Shaiju MS.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
