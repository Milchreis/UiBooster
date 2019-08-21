# UiBooster
UiBooster is a lean library to create fast and easy dialogs for utility tools.

## Usage
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

## Maven
If you want to use UiBooster than add the following to you pom.xml.
```
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```
<dependency>
    <groupId>com.github.Milchreis</groupId>
    <artifactId>UiBooster</artifactId>
    <version>1.1.0</version>
</dependency>
```
