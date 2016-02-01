package _2048;


public class GameModes {
	
	int mode, goal;
	String text;
	
	
	public GameModes(int mode){
		this.mode = mode;
		for (Mode modes: Mode.values()){
			System.out.println(modes.toString());
		}
		System.out.println(Mode.INTRO.text);
	}
	public String getText(){
		return Mode.values()[mode].getText();		
	}
	public int getGoal(int mode){
		return Mode.values()[mode].getGoal();
	}
	
	enum Mode {
		STANDARD(2048,"Bring a single tile to 2048"),
		INTRO(100,"Get a score of 100");
		
		Mode( int goal, String text){
			this.text = text;
			this.goal = goal;
		}
		private int goal;
		private String text;
		
		public String getText(){
			return text;		
		}

		public int getGoal() {
			return goal;
		}

		
		
		
	 }
	 
	
}
