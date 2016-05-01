import java.io.IOException;
import java.io.InputStream;


public class calculate_audio {
    int readBytes = 0;
	
    
	public byte[] calculate_audio(InputStream waveStreamForCalculate, int fileLength){
		//InputStream waveStreamForCalculate; 
		
		byte[] audioBuffer = new byte[fileLength];
		try {
			waveStreamForCalculate.read(audioBuffer, 0, audioBuffer.length);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//System.out.println(this.EXTERNAL_BUFFER_SIZE/15/60/5);
		System.out.println("b2" + readBytes);
		
		int[] index = new int[4500];
//		int index_iter = 0;
//		
//		byte[] new_audioBuffer = new byte[fileLength];
//		byte[] buffer = new byte[3200 * 10];
//		int new_iter = 0;
//		for(int i = 0; i < audioBuffer.length; i += buffer.length){
//			int buffer_iter = 0;
//			for(int j = i; j < i + buffer.length; j++){
//				if(j >= audioBuffer.length) break;
//				buffer[buffer_iter++] = audioBuffer[j];
//			}
//			double db = soundLevel(buffer);
//			
//			if(db > -7.50){
//				index[index_iter++] = i/3200;
//				for(int j = i; j < i + buffer.length; j++){
//					if(j >= audioBuffer.length) break;
//					new_audioBuffer[new_iter++] = audioBuffer[j];
//				}
//			}
//			
//		}
//		
		CommunicateVariables communicateVariables = CommunicateVariables.getSingular();
		communicateVariables.audioIndexInput(index);
		
		
		while(!communicateVariables.finished()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int[] temp = communicateVariables.getIndex();
		int ret_iter = 0;
		byte[] ret = new byte[fileLength];
		for(int i = 0; i < temp.length; i++){
			for(int j = 0; j < 3200; j++ ){
				ret[ret_iter++] = audioBuffer[j+temp[i]*3200];
			}
		}
		
		//return waveStreamForCalculate;
		System.out.println("ret finish");
		return ret;
		
	}
	
	private double soundLevel(byte[] buffer) {
		double db = 0.0;
		
		for (byte each : buffer) {
			db += each * each;
		}
		double total = Math.pow(db, 0.5)/ buffer.length;
		
		return 20.0 * Math.log10(total);
		}

}
