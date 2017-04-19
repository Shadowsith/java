import studiplayer.basic.BasicPlayer;

public class TaggedFile
extends AudioFile{

    //Variables-------------------------


    //Constructors---------------------

    public TaggedFile() {
    
	super();
    }
    public TaggedFile(String s) {
    
	super(s);  
    }
    //Methodes--------------------------

    public void play(){

	String file = file.getPathname();
	BasicPlayer.play(file.getPathname());
    }

    public void togglePause(){

	BasicPlayer.togglePause(); 
    }
    
    public void stop(){

	BasicPlayer.stop();
    }

    public String getFormattedDuration(){
	return "";
    }

    public String getFormattedPosition(){
	return "";
    }

    public String getAlbum(){
	return "";
    }

    public void readAndStoreTags(String a){

    }

    public String timeFormatter(long a){
	return "";
    }

    public String toString(){
	return "";
    }

}
