package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CellParser extends AbstractParser<String[][]> {

  @Override
  public String[][] parse(File configFile) throws FileNotFoundException, JSONException {
    JSONObject root = fileToJSON(configFile);
    JSONObject boardObj = findAttribute(root, "board");
    return getCellConfig(boardObj);
    //return new String[0][];
  }

  private String[][] getCellConfig(JSONObject boardObj) throws JSONException {
    String[][] colorConfig = new String[boardObj.getInt("width")][boardObj.getInt("height")];
    //return colorConfig;

    JSONArray cellColorsJSON = boardObj.getJSONArray("colorConfiguration");

    int height = colorConfig.length;

    for (int i = 0; i < colorConfig.length; i++) {
      JSONArray row = cellColorsJSON.getJSONArray(i);
      for (int j = 0; j < colorConfig[i].length; j++) {
        colorConfig[height - i - 1][j] = row.getString(j);
      }
    }
    return colorConfig;
  }
}
