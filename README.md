<p align="center">
  <img src="https://github.com/Milchreis/UiBooster/blob/master/screenshots/logo.png?raw=true" />
</p>

[![Release](https://jitpack.io/v/Milchreis/UiBooster.svg)](https://jitpack.io/#Milchreis/UiBooster)
[![Build Status](https://travis-ci.org/Milchreis/UiBooster.svg?branch=master)](https://travis-ci.org/Milchreis/UiBooster)

UiBooster is a lean library to create fast and easy dialogs for utility tools. It equips your applications blazing
fast with GUI components to increase the comfort for your users.

It's based on Java swing components to run on current JREs without any struggle. This library encapsulates the long and 
sometimes complicated GUI code and leaves more clarity in your project.
 
I decided to create this project, because I missed something like [zenity](https://de.wikipedia.org/wiki/Zenity) 
for my Java applications.

If you like this project, and you want to keep me awake 🤪, please:

<a href='https://ko-fi.com/L4L21072C' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi1.png?v=2' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

## Components

 - [Information dialogs](#information-dialogs)
 - [Text input dialog](#text-input-dialog)
 - [Confirmation dialog](#confirmation-dialog)
 - [Password input dialog](#password-input-dialog)
 - [Selection dialog](#selection-dialog)
 - [Multiple selection dialog](#multiple-selection-dialog)
 - [Slider dialog](#slider-dialog)
 - [Colorpicker](#colorpicker)
 - [Datepicker](#datepicker)
 - [File and directory selection dialogs](#file-and-directory-selection-dialogs)
 - [Exception dialog](#exception-dialog)
 - [List dialog](#list-dialog)
 - [Login dialog](#login-dialog)
 - [Waiting dialog](#waiting-dialog)
 - [Progress dialog](#progress-dialog)
 - [Table dialog](#table-dialog)
 - [Gallery dialog](#gallery-dialog)
 - [Form dialog](#form-dialog)
 - [Splash screen](#splash-screen)
 - [System tray menu](#system-tray)
 - [Notification](#notification)

### Information dialogs
![screenshot info dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/info.jpg?raw=true)
![screenshot warn dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/warn.jpg?raw=true)
![screenshot error dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/error.jpg?raw=true)
```java
new UiBooster().showInfoDialog("UiBooster is a lean library to ....");
```
```java
new UiBooster().showWarningDialog("Your computer has a low battery ....", "WARN");
```
```java
new UiBooster().showErrorDialog("The connection to SQL database is failed.", "ERROR");
```

### Text input dialog
![screenshot input dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/input.jpg?raw=true)
```java
String opinion = new UiBooster().showTextInputDialog("What do you think?");
```

### Confirmation dialog
![screenshot confirm dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/confirm.jpg?raw=true)
```java
new UiBooster().showConfirmDialog(
                "Do you really want this action?",
                "Are you sure?",
                () -> System.out.println("Action accepted"),
                () -> System.out.println("Action declined"));
```

### Password input dialog
![screenshot password dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/password.jpg?raw=true)
```java
String password = booster.showPasswordDialog("Whats your password?", "Password");
```

### Selection dialog
![screenshot selection dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/selection.jpg?raw=true)
```java
String selection = new UiBooster().showSelectionDialog(
        "What's your favorite movie?",
        "Favorite Movie?",
        Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"));
```

### Multiple selection dialog
![screenshot multiple selection dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/multiselection.jpg?raw=true)
```java
 List<String> selectedElement = new UiBooster().showMultipleSelection(
            "What are your favorite hobbies?",
            "Your hobbies",
            "Reading", "Traveling", "Fishing", "Music", "Gardening", "Sport", "Television",
            "Video Games", "Crafting", "Bird Watching", "Collecting");
```

### Slider dialog
![screenshot slider dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/slider.jpg?raw=true)
```java
Integer numberOfHotDogs = new UiBooster().showSlider("How many HotDogs do you want?", "Your order", 
                0, 10, 2, 5, 1);
```

### Colorpicker
![screenshot color dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/color.jpg?raw=true)
```java
Color selectedColor = new UiBooster().showColorPicker("Choose your favorite color", "Color picking");
```    

### Datepicker
![screenshot datepicker](https://github.com/Milchreis/UiBooster/blob/master/screenshots/dateselection.jpg?raw=true)
```java
Date birthday = new UiBooster().showDatePicker("What's your birthday?", "Birthday");
```

### File and directory selection dialogs
![screenshot file dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/fileselection.jpg?raw=true)
```java
UiBooster booster = new UiBooster();
File file = booster.showFileSelection();
File directory = booster.showDirectorySelection();
File fileOrDirectory = booster.showFileOrDirectorySelection();
```

### Exception dialog
![screenshot exception dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/exception.jpg?raw=true)
```java
new UiBooster().showException(
    "An error occurred", 
    "Exception message",
    new Exception("Something went wrong ...")
);
```

### List dialog
![screenshot list dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/list.jpg?raw=true)
```java
ListElement selectedElement = new UiBooster().showList(
    "Select a robot", 
    "Avatars from RoboHash.org",
    element -> System.out.println("Selected: " + element.toString()),
    new ListElement("Robo 1", "Green and strong",         "src/test/resources/avatar1.png"),
    new ListElement("Robo 2", "Shy without an avatar!"),
    new ListElement("Robo 3", "- Crazy\n- Fast\n- Funny", "src/test/resources/avatar2.png"),
    new ListElement("Robo 4", null,                       "src/test/resources/avatar3.png")
);
```

### Login dialog
![screenshot login dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/login.jpg?raw=true)
```java
LoginCredentials credentials = new UiBooster().showLogin(
        "Login",
        "Internal area",
        "Username",
        "Password",
        "Go",
        "Cancel");
```

### Waiting dialog
![screenshot waiting dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/waiting.jpg?raw=true)
```java
WaitingDialog dialog = new UiBooster().showWaitingDialog("Starting", "Please wait");
dialog.setMessage("Ready");
dialog.close();
```
![screenshot waiting with message dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/waiting_with_message.jpg?raw=true)
```java
WaitingDialog dialog = new UiBooster().showWaitingDialog("Starting", "Please wait");
dialog.setMessage("Initializing");
dialog.setLargeMessage("Some more information...\nMaybe from log files or other resources. \nBe transparent to the user to understand long processes...");
dialog.close();
```

### Progress dialog
![screenshot progress dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/progress.jpg?raw=true)
```java
ProgressDialog dialog = new UiBooster().showProgressDialog("Please wait", "Waiting", 0, 120);
dialog.setProgress(10);
// ...
dialog.setProgress(120);
dialog.setMessage("Ready");
dialog.close();
```

### Table dialog
![screenshot table dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/table.jpg?raw=true)
```java
String[][] modifiedData = new UiBooster().showTable(    // showTableImmutable for immutable tables
        new String[][]{
                {"Jimmy Johnson", "35", "Zombieland"},
                {"Danny Durango", "23", "Hangover"},
                {"Larry Berry", "54", ""}
        },
        Arrays.asList("Name", "Age", "Favorite movie"),
        "Favorite movies");
```

### Gallery dialog
![screenshot gallery dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/gallery.jpg?raw=true)
```java
new UiBooster().showPictures(
        "My picture",
        Arrays.asList(
            new File("/home/nick/pictures/img-01.jpg"),
            new File("/home/nick/pictures/img-02.jpg")
        )
);
```

### Form dialog
![screenshot gallery dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/form.jpg?raw=true)
```java
UiBooster booster = new UiBooster();
Form form = booster.createForm("Personal information")
            .addText("Whats your first name?")
            .addTextArea("Tell me something about you")
            .addSelection(
                    "Whats your favorite movie?",
                    Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"))
            .addLabel("Choose an action")
            .addButton("half full", () -> booster.showInfoDialog("Optimist"))
            .addButton("half empty", () -> booster.showInfoDialog("Pessimist"))
            .addSlider("How many liters did you drink today?", 0, 5, 1, 5, 1)
            .show();

// use .run() instead of show() to open the formBuilder without blocking.
```
The form is very powerful and provides a lot of features:
 * add your own elements ([Example](https://github.com/Milchreis/UiBooster/blob/master/src/test/java/de/milchreis/uibooster/CustomFormBuilderElementTest.java))
 * put multiple elements in one row ([Example](https://github.com/Milchreis/UiBooster/blob/master/src/test/java/de/milchreis/uibooster/FormBuilderWithRowsTest.java#L41))
 * add a listener for any changes ([Example](https://github.com/Milchreis/UiBooster/blob/master/src/test/java/de/milchreis/uibooster/FormBuilderChangeListenerTest.java#L22))
 * set window setting, f.e. size and position ([Example](https://github.com/Milchreis/UiBooster/blob/master/src/test/java/de/milchreis/uibooster/FormBuilderTest.java#L96))

### Splash screen
![screenshot splash screen](https://github.com/Milchreis/UiBooster/blob/master/screenshots/splash.jpg?raw=true)
```java
Splashscreen splash = new UiBooster().showSplashscreen("/path/to/your/splash.png");
// do your stuff
splash.hide();
```

### System tray
![screenshot gallery dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/tray.jpg?raw=true)
```java
UiBooster booster = new UiBooster();
booster.createTrayMenu("Food", "screenshots/color.jpg")
        .withPopupMenu()
        .addMenu("Hotdogs", () -> booster.showInfoDialog("Sausage in a roll"))
        .addMenu("Fries", () -> booster.showInfoDialog("Fried potatoes"))
        .addMenu("Pizza", () -> booster.showInfoDialog("Dough with tomato sauce"));
```

### Notification
![screenshot gallery dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/notification.jpg?raw=true)
```java
new UiBooster().createNotification("It's hot and delicious", "Dinner is ready");
```

## Options
```java
UiBooster booster = new UiBooster(
    new UiBoosterOptions(
        UiBoosterOptions.Theme.DARK_THEME,
        "/path/to/your/custom-window-icon.png"
    )
);
```

## Include to project
### Maven
If you want to use `UiBooster` than add the following to your pom.xml.
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>com.github.Milchreis</groupId>
    <artifactId>UiBooster</artifactId>
    <version>1.12.0</version>
</dependency>
```

### Gradle
If you want to use `UiBooster` with gradle than add the repo, and the dependency to your root build.gradle file.
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```gradle
dependencies {
        implementation 'com.github.Milchreis:UiBooster:1.12.0'
}
```
