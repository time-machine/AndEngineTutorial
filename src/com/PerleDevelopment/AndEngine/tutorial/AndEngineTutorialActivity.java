package com.PerleDevelopment.AndEngine.tutorial;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class AndEngineTutorialActivity extends SimpleBaseGameActivity {
  private static final int CAMERA_WIDTH = 480;
  private static final int CAMERA_HEIGHT = 800;
  private Camera mCamera;
  private Scene mMainScene;
  private BitmapTextureAtlas mBitmapTextureAtlas;
  private TextureRegion mPlayerTextureRegion;

  @Override
  public EngineOptions onCreateEngineOptions() {
    this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
    return new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED,
      new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
  }

  @Override
  protected void onCreateResources() {
    // load all the textures this game needs
    mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(),
      32, 32);
    mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory
      .createFromAsset(this.mBitmapTextureAtlas, this, "face_box.png", 0, 0);
    mBitmapTextureAtlas.load();
  }

  @Override
  protected Scene onCreateScene() {
    this.mEngine.registerUpdateHandler(new FPSLogger()); // logs the frame rate

    // create scene and set background color to (1, 1, 1) = white
    this.mMainScene = new Scene();
    this.mMainScene.setBackground(new Background(1, 1, 1));

    // center the player on the camera
    final int iStartX = (CAMERA_WIDTH - mBitmapTextureAtlas.getWidth()) / 2 ;
    final int iStartY = (CAMERA_HEIGHT - mBitmapTextureAtlas.getHeight()) / 2 ;

    // create the sprite and add it to the scene
    final Sprite oPlayer = new Sprite(iStartX, iStartY, mPlayerTextureRegion,
      getVertexBufferObjectManager());
    this.mMainScene.attachChild(oPlayer);

    return this.mMainScene;
  }
}
