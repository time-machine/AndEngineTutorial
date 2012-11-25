package com.PerleDevelopment.AndEngine.tutorial;

import java.util.ArrayList;

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

import com.PerleDevelopment.AndEngine.tutorial.helper.AccelerometerHelper;
import com.PerleDevelopment.AndEngine.tutorial.objects.Platform;
import com.PerleDevelopment.AndEngine.tutorial.objects.Player;

public class AndEngineTutorialActivity extends SimpleBaseGameActivity {
  public static final int CAMERA_WIDTH = 480;
  public static final int CAMERA_HEIGHT = 800;

  private Camera mCamera;
  private Scene mMainScene;

  private BitmapTextureAtlas mBitmapTextureAtlas;
  private TiledTextureRegion mPlayerTiledTextureRegion;
  private final ArrayList<Platform> mPlatforms = new ArrayList<Platform>();
  private BitmapTextureAtlas mPlatformBitmapTextureAtlas;
  private TiledTextureRegion mPlatformTextureRegion;

  @Override
  public EngineOptions onCreateEngineOptions() {
    new AccelerometerHelper(this);
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
      .createTiledFromAsset(this.mBitmapTextureAtlas, this, "face_box.png",
        0, 0, 1, 1);
    this.mBitmapTextureAtlas.load();

    // texture for platform
    this.mPlatformBitmapTextureAtlas = new BitmapTextureAtlas(
      this.getTextureManager(), 64, 64);
    this.mPlatformTextureRegion = BitmapTextureAtlasTextureRegionFactory
      .createTiledFromAsset(this.mPlatformBitmapTextureAtlas, this,
        "platform.png", 0, 0, 1, 1);
    this.mPlatformBitmapTextureAtlas.load();
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
    final Player player = new Player(
      centerX, centerY, this.mPlayerTiledTextureRegion,
      this.getVertexBufferObjectManager());

    mPlatforms.add(new Platform(100, 500, this.mPlatformTextureRegion,
      this.getVertexBufferObjectManager()));
    mPlatforms.add(new Platform(200, 600, this.mPlatformTextureRegion,
      this.getVertexBufferObjectManager()));
    mPlatforms.add(new Platform(300, 700, this.mPlatformTextureRegion,
      this.getVertexBufferObjectManager()));

    // add platforms, so we can work with them (Player.java)
    player.setmPlatforms(mPlatforms);

    this.mMainScene.attachChild(player);

    for (Platform platform: mPlatforms) {
      this.mMainScene.attachChild(platform);
    }

    return this.mMainScene;
  }
}
