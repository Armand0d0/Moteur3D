package game;


import engine.IGameLogic;
import engine.MouseInput;
import graphic.PointLight;
import engine.Window;
import graphic.*;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import engine.Timer;
import org.lwjgl.system.CallbackI;

import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;

//import static org.lwjgl.glfw.GLFW.GLFW_KEY_;


public class DummyGame implements IGameLogic {

	
	
	 
	private final Renderer renderer;
	//private final MouseInput mouseInput;
	 
	 private GameItem[] gameItems;
	 private float random = (float)Math.random()*10;
	 private final Camera camera;
	 private Vector3f cameraInc;
	 private float speed = 10f;
	 private float runningSpeed = 1f;
	 private float noRunningSpeed = 0.5f;
	 private PointLight[] pointLights;
	 private DirectionalLight[] directionalLights;
	 private SpotLight[] spotLights;
	private Hud hud ;
	private  Mesh mesh;
	private  Mesh mesh2;
	private float Xmouse;
	private float GIspeed = 1f;
	 private static final float MOUSE_SENSITIVITY = 0.1f;
	 private Vector3f ambientLight;
	private float MotoSpeed =0;
	private float MotoAcceleration =0.3f;
	private float freeWheeling =0.99f;
	private float maxSpeed = 2f;
	private Vector3f MotoDir = new Vector3f(0,0,0);
	private float MSensivity = 1;
	private float maxAngle = 150;
	private float MotoBreak = 0.2f;
	private  boolean drive = false;




	public DummyGame() throws Exception{
		 renderer = new Renderer();
		camera = new Camera();
		cameraInc = new Vector3f(0f,0f,0f);

				
		
	 }
	@Override
	public void init(Window win) throws Exception {
	    renderer.init(win);
//	    camera.setPosition(0, 5, 7);
//        camera.setRotation(30, 0, 0);
//	    Mesh Tonus = OBJLoader.loadMesh("/Tonus.obj");
//	    Mesh Panda = OBJLoader.loadMesh("/MonPanda3D.obj");
		float[] pos = new float[]{0,0,0.01f,0,0,-0.01f,0,0.01f,0}; int[] indices = new int[]{1,2,3};float[] tex = new float[]{0,0,1,1,0,1};float[] norm = new float[]{0,0,0,0,0,0,0,0,0};
		Mesh Point = new Mesh(pos,indices,tex,norm);
	    Mesh Cube = OBJLoader.loadMesh("/cubeT.obj");
	    Mesh LightTests = OBJLoader.loadMesh("/LightTests.obj");
	   // Mesh Bugatti = OBJLoader.loadMesh("/bugatti.obj");
	    Mesh Bunny = OBJLoader.loadMesh("/bunny.obj");
	    Mesh Sphere = OBJLoader.loadMesh("/sphere.obj");

		Mesh Sphere2 = OBJLoader.loadMesh("/boule.obj");
	    Mesh Singe = OBJLoader.loadMesh("/singe.obj");
		Mesh Lapin = OBJLoader.loadMesh("/lapin.obj");
		Mesh Plan = OBJLoader.loadMesh("/plan.obj");


	   // Mesh mesh = X3DLoader.loadMesh("/cubeT.x3d");
        Texture CubeT = new Texture("/damier.png");
        Texture PandaT = new Texture("/Panda.png");
        Texture BlackT = new Texture("/Black.png");
        Texture RainbowT = new Texture("/Rainbow.png");
        Texture TriangleT = new Texture("/Triangle.png");
        Texture CarreT = new Texture("/Carre.png");
        Texture White = new Texture("/white.png");
        Texture SingeT = new Texture("/singe.png");



        Material cube = new Material(CubeT,1);
		Material white = new Material(White,100);
		Material rainbow = new Material(RainbowT,1);
		Material triangle = new Material(TriangleT,1);


//        Tonus.setMaterial(rainbow); 
//        Panda.setMaterial(panda);
		//Lapin.setMaterial(white);
		Point.setMaterial(rainbow);
        Sphere.setMaterial(triangle);
        Sphere2.setMaterial(white);
        Cube.setMaterial(cube);
        //Singe.setMaterial(SingeT);
        LightTests.setMaterial(white);
      Bunny.setMaterial(rainbow);
//      Bugatti.setMaterial(cube);
		//GameItem LapinGI = new GameItem(Lapin);
        GameItem SphereGI = new GameItem(Sphere);
		GameItem PointGI = new GameItem(Point);
        GameItem SphereGI2 = new GameItem(Sphere2);
        GameItem SingeGI = new GameItem(Singe);
//      GameItem TonusGI= new GameItem(Tonus);
//      GameItem PandaGI = new GameItem(Panda);
        GameItem CubeGI= new GameItem(Cube);
        GameItem LightTestsGI = new GameItem(LightTests);
//      GameItem BugattiGI = new GameItem(Bugatti);
      GameItem BunnyGI = new GameItem(Bunny);
		PointGI.setScale(1000);
//      PandaGI.setPosition(0,1.5f,-6);

        CubeGI.setScale(10);
		CubeGI.setPosition(0,-11,0);
        SphereGI.setScale(2f);
		SphereGI.setPosition(-10,0,0);

		SphereGI2.setScale(2f);
		SphereGI2.setPosition(10,-5,15);

		LightTestsGI.setScale(4);
        LightTestsGI.setPosition(10,5,0);

		Texture MotoT = new Texture("/Moto.png");
		Mesh moto = OBJLoader.loadMesh("/moto.obj");
		Material Moto = new Material(MotoT,4);
		moto.setMaterial(Moto);
				GameItem MotoGI = new GameItem(moto);
				MotoGI.setScale(0.1f);
				MotoGI.setRotation(0,0,0);
				Plan.setMaterial(new Material(CubeT , 3));
				GameItem PlanGI =  new GameItem(Plan);
				PlanGI.setScale(1000);

		mesh = Bunny;
		mesh2 = Cube;
		CubeGI.setPosition(0,100,0);








		gameItems = new GameItem[] {
				MotoGI,//PlanGI,
		};
		generate();

//	    gameItems = new GameItem[50];
//	    int x = 0,z = 0;
//	    for(int i=0;i<50;i++) {
//	    	gameItems[i] = new GameItem(Cube);
//	    	gameItems[i].setPosition(x, 0, z);
//	    	x+=4;
//	    	if(x==10) {
//	    		x=0;
//	    		z+=4;
//	    	}
//	    }
	    	PointLight pl = new PointLight(new Vector3f(-10,0,0),new Vector3f(1,0,1),10);
		pointLights = new PointLight[]{
				//pl,
		};
			DirectionalLight dl = new DirectionalLight(new Vector3f(1,1,0),new Vector3f(1,1,1),0.3f);
		directionalLights = new DirectionalLight[]{
		dl,
		};
		SpotLight sl = new SpotLight(pl,15,new Vector3f(0,-1,0));
		spotLights = new SpotLight[]{
				//sl
		};

		hud = new Hud(10);

		//hud.setImage(2,CubeT);
		//hud.getGameItems()[2].setPosition(win.getWidth()/2,win.getHeight()/2,0);

		camera.setPosition(0,1,0);
		camera.setRotation(0,90,0);

	}
public void generate() throws Exception {
	Mesh road = OBJLoader.loadMesh("/plan.obj"); road.setMaterial(new Material(new Texture("/Road.png"),0.2f));
	Mesh roadTurn = OBJLoader.loadMesh("/plan.obj"); roadTurn.setMaterial(new Material(new Texture("/Road_Turn.png"),0.2f));


	float scale = 25;
	float d = scale*2;


	GameItem[] RgameItems = new GameItem[100];

	Vector3f pos = new Vector3f(0,0,0);
	Vector3f dir = new Vector3f(1,0,0);
	boolean turn = false;
	boolean turn2 = false;




	float r = 90;
	float r2 = 00;
	float Zdir = 0;

	for(int i = 0;i<RgameItems.length;i++){


		if(turn2){
			dir.x = dir.x == 0 ? 1 : 0;
			if(dir.x == 0) dir.z = Zdir;
			else{dir.z = 0;}
			turn = true;
		}else{ turn = false; }

		r += turn ? 90 : 0;

		pos.x += dir.x*d;
		pos.z += dir.z*d;


		if(random(1.5)&& !turn){

			 Zdir = random12() ? 1 : -1;

			if(dir.z == 1){ r2 =180;}else if(dir.z == -1){r2 = 270;}
			else if(Zdir == -1){r2 = 90;}else{r2 = 0;}
			RgameItems[i] =  new GameItem(roadTurn).setPos(pos).setRot(0,r2,0).SetScale(scale);


			turn2 = true;
				}
		else{

			RgameItems[i] = new GameItem(road).setPos(pos).setRot(0,r,0).SetScale(scale);
			turn2 = false;
		}




	}

	 gameItems =  add(gameItems, RgameItems);
}
public GameItem[] add(GameItem[] arr1 , GameItem[] arr2){
		 GameItem[] result = new GameItem[arr1.length + arr2.length];
		for(int i=0;i<arr1.length;i++){
			result[i] = arr1[i];
		}
	for(int i=0;i<arr2.length;i++){
		result[i+arr1.length] = arr2[i];
	}
		return result;
}
public boolean random(double d){
boolean f;
	if(Math.random() <= 1d/d){
		f = false;
	}else {
		f = true;
	}
		return f;
}
public boolean random12(){
		return Math.random()<=0.5 ? true : false;
}
	@Override
	public void input(Window window, MouseInput mouseInput) throws Exception {
		
	    cameraInc.set(0, 0, 0);
		 // && gameItems[0].getRotation().z > -90  && gameItems[0].getRotation().z  < 90
			Xmouse = (float)mouseInput.getCurrentPos().x / window.getWidth()  ;

	    if (window.getInput().isKeyDown(GLFW_KEY_W)) {//z
	        cameraInc.z = -1;
	    }
	    if (window.getInput().isKeyDown(GLFW_KEY_S)) {//s
	        cameraInc.z = 1;
	    }
	    if (window.getInput().isKeyDown(GLFW_KEY_A)) {//q
	        cameraInc.x = -1;

	    }
	    if (window.getInput().isKeyDown(GLFW_KEY_D)||window.getInput().isKeyDown(GLFW_KEY_V)) {//d
	        cameraInc.x = 1;

	    }
	    if (window.getInput().isKeyDown(GLFW_KEY_LEFT_SHIFT)) {//w
	        cameraInc.y = -1;

	    }
	    if (window.getInput().isKeyDown(GLFW_KEY_SPACE)) {//x
	        cameraInc.y = 1;

	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_C)) {
	    	speed = runningSpeed;
	    }else {
	    	speed = noRunningSpeed;
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_KP_0)) {
	    	camera.setPosition(0, 0, 0);
	    	camera.setRotation(0, 0, 0);
	    	//gameItems[0].setPosition(0,0,0);
	    	MotoSpeed =0;
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_X)) {
	    	window.PolygonMode();
	    }else {
	    	window.UnPolygonMode();
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_KP_6)) {
	    	gameItems[0].movePosition(-GIspeed,0f,0f);
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_KP_4)) {
			gameItems[0].movePosition(GIspeed,0,0);
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_KP_8)) {
			gameItems[0].movePosition(0,GIspeed,0);
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_KP_2)) {
			gameItems[0].movePosition(0,-GIspeed,0);
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_KP_7)) {
			gameItems[0].movePosition(0,0,GIspeed);
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_KP_1)) {
			gameItems[0].moveRotation(0,GIspeed,0);
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_KP_3)) {
	    	gameItems[0].moveRotation(0,-GIspeed,0);
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_O) && MotoSpeed <= maxSpeed) {
			MotoSpeed += MotoAcceleration;
	    }
	    if(window.getInput().isKeyDown(GLFW_KEY_L) && MotoSpeed >= -maxSpeed) {
			MotoSpeed -= MotoAcceleration/3.;

		}
		if(window.getInput().isKeyDown(GLFW_KEY_K) ) {
			MotoDir.y += MSensivity;
		}
		if(window.getInput().isKeyDown(GLFW_KEY_M)) {
			MotoDir.y -= MSensivity;

		}
		if(window.getInput().isKeyDown((GLFW_KEY_KP_DECIMAL))){
			gameItems = new GameItem[] {
					gameItems[0]
			};
			generate();
		}
		if(mouseInput.isRightButtonPressed()){

			drive = !drive;
		}
	}



	@Override
	public void update(float time, float fps, Window window, MouseInput mouseInput) throws Exception {
		hud.setText(0, "FPS: " + fps);
		hud.getGameItems()[0].setPosition(0, 0, 0);


if(drive){///////////////////////////////////////////
		Vector3f r = gameItems[0].getRotation();
		Vector3f p = gameItems[0].getPosition();
		float angle = -(((Xmouse) * maxAngle) - maxAngle / 2);
		gameItems[0].setRotationZ(angle);
		float dir = -r.z / 50;

		gameItems[0].moveRotation(0, dir, 0);
		gameItems[0].moveRelativePos(MotoSpeed);
		gameItems[0].setPosition(p.x, Math.abs((Xmouse - 0.5f) * 2) / 3 + 0.1f, p.z);


		float cameraDist = 10;
		float y = cos(r.z) * cameraDist;
		float rot = 90;

		Vector3f d = new Vector3f(cos(r.y + rot) * cameraDist - sin(r.z + r.y) * cameraDist, y, sin(r.y + rot) * cameraDist + cos(r.z + r.y) * cameraDist);
		d = new Vector3f(-d.x, d.y, -d.z);

		camera.setPosition(p.x	+	d.x		,	p.y	+	d.y	+5f,	p.z	+	d.z);
		camera.setRotation(20,r.y+180,0);
		MotoSpeed*=freeWheeling;
	}///////////////////////////////////////////////////
		else {


	camera.movePosition(cameraInc.x * speed, cameraInc.y * speed, cameraInc.z * speed);


	if (mouseInput.isLeftButtonPressed() ) {
		Vector2f rotVec = mouseInput.getDisplVec();
		camera.moveRotation(rotVec.y * MOUSE_SENSITIVITY, rotVec.x * MOUSE_SENSITIVITY, 0);
	}
}
	}
		public float cos(float f){
		return (float) Math.cos(rad(f));
		}
	public float sin(float f){
		return (float) Math.sin(rad(f));
	}
	public float rad(float f){
		return (float) Math.toRadians(f);
	}
	@Override
	public void render(Window window) {

		try {
			renderer.render(window,camera,gameItems,pointLights,directionalLights,spotLights , hud);
		} catch (Exception e) {e.printStackTrace();}

	}
	@Override
	public void cleanup() {
		renderer.cleanup();
		 for
		  (GameItem  gameItem : gameItems) {
		 	if(gameItem !=null)
	           gameItem.getMesh().cleanUp();
	}
	}

}
