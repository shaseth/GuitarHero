import java.lang.reflect.Array;
import java.util.Random;

public class GuitarString {
	
	double Frequency;
	double[] init;
	RingBuffer buffer;
	int ticCount = 0;
	
	// create a guitar string of a given frequency, using a 44100 sampling rate,
	// and creating a RingBuffer class to represent our guitar string
	public GuitarString(double frequency) {
		int N = (int) Math.round(44100/frequency); //44100
		buffer = new RingBuffer(N);
		for (int i = 0; i<N; i++) {
			buffer.enqueue(0);
		}
	}
	
	// create a guitar string which values are given by the array,
	// mainly used in this project for debugging/grading
	public GuitarString(double[] init) {
		int len = Array.getLength(init);
		RingBuffer bufferTest = new RingBuffer(len);
		for (int i = 0; i<len; i++) {
			bufferTest.enqueue(init[i]);
		}
	}
	
	/*
	 * Pluck function. Sets the created buffer to white noise
	 * Does not take in any values nor does it return anything.
	 * Replaces the N items in the RingBuffer with N random values
	 * between -0.5 and 0.5
	 */
	public void pluck() {
		Random r = new Random();
		for (int i = 0; i<buffer.size(); i++) {
			double randomValue = Math.round((-0.5 + (0.5 - -0.5) * r.nextDouble())*10.0)/10.0;
			buffer.dequeue();
			buffer.enqueue(randomValue);
		}
	}
	
	/*
	 * Tic function. Advances the simulation by one time step. 
	 * Does not take in a value or return anything. This function
	 * deletes the sample at the front of the RingBuffer and adds to the 
	 * end of the buffer the average of the first two samples, multiplied
	 * by the energy decay factor
	 */
	public void tic() {
		double num1 = buffer.dequeue();
		double num2 = buffer.peek();
		double avg = (num1+num2)/2;
		buffer.enqueue(avg*0.994); //0.994
		ticCount++;
	}
	
	/*
	 * Sample function. Returns the current sample.
	 * @return: double, the value of the item at the front of the RingBuffer
	 */
	public double sample() {
		return buffer.peek();
	}
	
	/*
	 * Time function. Returns the number of tics
	 * @return: int, total number of times tic() function is called
	 */
	public int time() {
		return ticCount;
	}
	
	// testing functions here
	public static void main(String[] args) {
		GuitarString g = new GuitarString(1045);
		g.pluck();
		g.tic();
		g.tic();
		System.out.println(g.sample());
		System.out.println(g.time());
	}
	
}