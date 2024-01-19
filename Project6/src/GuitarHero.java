public class GuitarHero {
	// main function, taken from sample code
      public static void main(String[] args) {

         
          String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
          GuitarString[] stringArray = new GuitarString[37];
          //double pSample = 0;
          for (int i=0; i<37; i++) {
        	  stringArray[i] = new GuitarString(440 * Math.pow(1.05956, i-24));
          }
          while (true) {

              // check if the user has typed a key; if so, process it   
              if (StdDraw.hasNextKeyTyped()) {
                  char key = StdDraw.nextKeyTyped();
                  int index = keyboard.indexOf(key);
                  // make a statement so that any key outside of the string does not
                  // cause crashes/errors
                  if (index>=0) stringArray[index].pluck();
              }

              // compute the superposition of samples
              double sample = 0;
              for (int i=0; i<37; i++) {
            	  sample += stringArray[i].sample();
              }
  
              // play the sample on standard audio
              StdAudio.play(sample);
  
              // advance the simulation of each guitar string by one step   
              for (int i=0; i<37; i++) {
            	  stringArray[i].tic();
              }
          }
       }
  }