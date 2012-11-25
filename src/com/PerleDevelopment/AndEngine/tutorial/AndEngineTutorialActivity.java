package com.PerleDevelopment.AndEngine.tutorial;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import com.PerleDevelopment.AndEngine.tutorial.objects.Player;

public class AndEngineTutorialActivity extends SimpleBaseGameActivity {
  public static final int CAMERA_WIDTH = 480;
  public static final int CAMERA_HEIGHT = 800;

  private Camera mCamera;
  private Scene mMainScene;

  private BitmapTextureAtlas mBitmapTextureAtlas;
  private TiledTextureRegion mPlayerTiledTextureRegion;

  @Override
  public EngineOptions onCreateEngineOptions() {
    this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
    return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED,
      new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
  }

  @Override
  protected void onCreateResources() {
    // load all the textures this game needs
    this.mBitmapTextureAtlas = new BitmapTextureAtlas(
      this.getTextureManager(), 32, 32);

    this.mPlayerTiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
      .createTiledFromAsset(
         this.mBitmapTextureAtlas, this, "face_box.png", 0, 0, 1, 1);

    this.mBitmapTextureAtlas.load();
  }

  @Override
  protected Scene onCreateScene() {
    this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate

    // create scene and set background color to (1, 1, 1) = white
    this.mMainScene = new Scene();
    this.mMainScene.setBackground(new Background(1, 1, 1));

    // center the player on the camera
    final float centerX = (
      CAMERA_WIDTH - this.mPlayerTiledTextureRegion.getWidth()) / 2 ;
    final float centerY = (
      CAMERA_HEIGHT - this.mPlayerTiledTextureRegion.getHeight()) / 2 ;

    // create the sprite and add it to the scene
    final Player oPlayer = new Player(
      centerX, centerY, this.mPlayerTiledTextureRegion,
      this.getVertexBufferObjectManager());
    this.mMainScene.attachChild(oPlayer);

    return this.mMainScene;
  }
}
