# UiBooster
[![Release](https://jitpack.io/v/Milchreis/UiBooster.svg)](https://jitpack.io/#Milchreis/UiBooster)

UiBooster is a lean library to create fast and easy dialogs for utility tools. It's based on basic
Java Swing components to run on current JREs and inspired by [ZENITY](https://de.wikipedia.org/wiki/Zenity).

## Components
### Information dialogs
```java
new UiBooster().showInfoDialog("Info message");
```

```java
new UiBooster().showWarningDialog("Warning message", "WARN");
```

```java
new UiBooster().showErrorDialog("Error message", "ERROR");
```

### Text input dialog
```java
String opinion = new UiBooster().showTextInputDialog("What do you think?");
```

### Confirmation dialog
```java
new UiBooster().showConfirmDialog(
                "Do you really want this action?",
                "Are you sure?",
                () -> System.out.println("Action accepted"),
                () -> System.out.println("Action declined"));
```

### Selection dialog
```java
String selection = new UiBooster().showSelectionDialog(
        "What's your favorite movie?",
        "Favorite Movie?",
        Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"));
```

### Colorpicker
```java
Color selectedColor = new UiBooster().showColorPicker("Choose your favorite color", "Color picking");
```    

### File and directory selection dialogs
```java
UiBooster booster = new UiBooster();
File file = booster.showFileSelection();
File directory = booster.showDirectorySelection();
File fileOrDirectory = booster.showFileOrDirectorySelection();
```

### Login dialog
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
```java
WaitingDialog dialog = new UiBooster().showWaitingDialog("Starting", "Please wait");
dialog.setMessage("Ready");
dialog.close();
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
    <version>1.2.0</version>
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
        implementation 'com.github.Milchreis:UiBooster:1.2.0'
}
```
