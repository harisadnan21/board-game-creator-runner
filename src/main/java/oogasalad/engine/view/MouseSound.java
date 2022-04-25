package oogasalad.engine.view;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author Cynthia France
 */
public class MouseSound {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/resource-names/";
  public static final String DEFAULT_LANGUAGE_RESOURCE_PACKAGE = "/engine-view/languages/";
  public static ResourceBundle sndBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Sound");
  public static String SOUNDS_FOLDER = "src/main/resources/engine-view/sounds/";
  public static String DEFAULT_SOUND = "Click";
  public static String[] SOUNDS = new String[] {"Click", "Thump"};

  private boolean soundOn;
  private Map<String, Clip> sounds;
  private ResourceBundle myResources;
  private Clip currSound;

  public MouseSound(String language) {
    sounds = new HashMap<>();
    myResources = ResourceBundle.getBundle(DEFAULT_LANGUAGE_RESOURCE_PACKAGE + language);
    soundOn = true;
    getSounds();
    setSound(DEFAULT_SOUND);
  }

  public void setSound(String sound) {
    if (sounds.containsKey(sound)) {
      soundOn = true;
      currSound = sounds.get(sound);
    }
    else {
      currSound = null;
      soundOn = false;
    }
  }

  private void getSounds() {
    for (String s : SOUNDS) {
      Clip clip = getSound(s);
      sounds.put(s, clip);
    }
  }

  private Clip getSound(String name) {
    AudioInputStream audioIn;
    try {
      System.out.println(SOUNDS_FOLDER + sndBundle.getString(name));
      File f = new File(SOUNDS_FOLDER + sndBundle.getString(name));
      audioIn = AudioSystem.getAudioInputStream(f);
      Clip clip = AudioSystem.getClip();
      clip.open(audioIn);
      return clip;
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      ApplicationAlert alert = new ApplicationAlert(myResources.getString("Error"), myResources.getString("SoundNotFound"));
      return null;
    }
  }

  public void playSound() {
    if (soundOn) {
      currSound.setMicrosecondPosition(0);
      currSound.start();
    }
  }
}
