<p align="center">
  <img src="https://github.com/Milchreis/UiBooster/blob/master/screenshots/logo.png?raw=true" />
</p>

[![Release](https://jitpack.io/v/Milchreis/UiBooster.svg)](https://jitpack.io/#Milchreis/UiBooster)
[![Build Status](https://travis-ci.org/Milchreis/UiBooster.svg?branch=master)](https://travis-ci.org/Milchreis/UiBooster)

UiBooster is a lean library to create fast and easy dialogs for utility tools. It equips your commandline tools blazing
fast with GUI components for user without CLI experience.

It's based on Java swing components to run on current JREs without struggle. I decided to create this project,
because I missed something like [zenity](https://de.wikipedia.org/wiki/Zenity) for my Java applications.

If you like this project and you want to keep me awake 🤪

<a href='https://ko-fi.com/L4L21072C' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi1.png?v=2' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

## Components

 - [Infomation dialogs](#information-dialogs)
 - [Text input dialog](#text-input-dialog)
 - [Confirmation dialog](#confirmation-dialog)
 - [Selection dialog](#selection-dialog)
 - [Colorpicker](#colorpicker)
 - [Datepicker](#datepicker)
 - [File and directory selection dialogs](#file-and-directory-selection-dialogs)
 - [Login dialog](#login-dialog)
 - [Waiting dialog](#waiting-dialog)
 - [Progress dialog](#progress-dialog)
 - [Tabel dialog](#table-dialog)
 - [Gallery dialog](#gallery-dialog)
 - [Form dialog](#form-dialog)
 - [System tray menu](#system-tray)

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

### Selection dialog
![screenshot selection dialog](https://github.com/Milchreis/UiBooster/blob/master/screenshots/selection.jpg?raw=true)
```java
String selection = new UiBooster().showSelectionDialog(
        "What's your favorite movie?",
        "Favorite Movie?",
        Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"));
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
 FilledForm form = new UiBooster()
            .createForm("Personal informations")
            .addText("Whats your first name?")
            .addTextArea("Tell me something about you")
            .addSelection(
                    "Whats your favorite movie?",
                    Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"))
            .show();
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

## Options
```java
boolean useNativeLookAndFeel = true;
UiBooster booster = new UiBooster(new UiBoosterOptions(useNativeLookAndFeel));
```

## Include to project
### Maven
If you want to use UiBooster than add the following to you pom.xml.
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
    <version>1.4.0</version>
</dependency>
```

### Gradle
Add the repo and the dependency to your root build.gradle, if you want to use it.
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
        implementation 'com.github.Milchreis:UiBooster:1.4.0'
}
```
