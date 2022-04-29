## Description

The builder does not load empty JSON arrays properly, causing issues when loading and re-saving a 
game configuration with empty JSON arrays.

When the builder loads in an array of strings from a JSON configuration it should create an empty 
StringListProperty, where the "value" is an empty collection of strings. This is currently not the 
case, as an empty JSON array is parsed as a collection of strings with the first element being "[]".


## Expected Behavior

When the builder loads in an empty array from a JSON configuration it should create an empty
StringListProperty, where the "value" is an empty collection of strings.

## Current Behavior

When the builder loads in an empty array from a JSON configuration it should creates a 
StringListProperty with one element in the list, which is "[]".

## Steps to Reproduce

1. Run Main.java
2. Navigate to the builder
3. Load a game configuration with an empty list anywhere in the JSON configuration. For an example, 
see data/tests/empty/config.json line 29, where the conditions property has no elements.

## Failure Logs

[INFO ] 2022-04-28 15:48:57.503 [JavaFX Application Thread] GameElementFactory - org.json.JSONException: JSONArray[-1] not found.

## Hypothesis for Fixing the Bug

The issue is in the following code (found in oogasalad.builder.model.element.factory.GameElementFactory):

```java
  // Attempts to convert an array to the proper string representation. Consider refactoring
  private String convertToString(String orig) {
    try{
      JSONArray arr = new JSONArray(orig);
      StringBuilder s = new StringBuilder();
      for (int i = 0; i < arr.length() - 1 ; i++) {
        s.append(arr.getString(i));
        s.append(LIST_DELIMITER);
      }
      s.append(arr.getString(arr.length() - 1));
      return s.toString();
    } catch (JSONException e) {
      LOG.info(e);
      return orig;
    }
  }
```

Should be changed to:

```java
  // Attempts to convert an array to the proper string representation. Consider refactoring
  private String convertToString(String orig) {
    try{
      JSONArray arr = new JSONArray(orig);
      StringBuilder s = new StringBuilder();
      for (int i = 0; i < arr.length() ; i++) {
        s.append(arr.getString(i));
        s.append(LIST_DELIMITER);
      }
      return s.toString();
    } catch (JSONException e) {
      LOG.info(e);
      return orig;
    }
  }
```