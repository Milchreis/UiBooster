# UiBooster
UiBooster is a lean library to create fast and easy dialogs for utility tools.

## Usage
### Show an information to the user
```java
new UiBooster().showInfoDialog("Info message");
```

### Ask the user for conformation
```java
new UiBooster().showConfirmDialog(
                "Do you really want this action?",
                "Are you sure?",
                () -> System.out.println("Action accepted"),
                () -> System.out.println("Action declined"));
```

### The user can selection from a string list
```java
String selection = new UiBooster().showSelectionDialog(
        "What's your favorite movie?",
        "Favorite Movie?",
        Arrays.asList("Pulp Fiction", "Bambi", "The Godfather", "Hangover"));
```

### The user can pick a color
```java
Color selectedColor = new UiBooster().showColorPicker("Choose your favorite color", "Color picking");
```    

### Select a file or directory of both
```java
UiBooster booster = new UiBooster();
File file = booster.showFileSelection();
File directory = booster.showDirectorySelection();
File fileOrDirectory = booster.showFileOrDirectorySelection();
```

### Ask the user for login credentials
```java
LoginCredentials credentials = new UiBooster().showLogin(
        "Login",
        "Internal area",
        "Username",
        "Password",
        "Go",
        "Cancel");
```
