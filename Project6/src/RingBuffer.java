public class RingBuffer {
	
	int first;
	double[] buffer;
	int Capacity;
	int size;
	
	// create an empty RingBuffer, with given max capacity
	public RingBuffer(int capacity) {
		buffer = new double[capacity];
		first = 0;
		size = 0;
		Capacity = capacity;
	}
	
	/*
	 * Size function. Returns the number of items currently in the buffer
	 * @return: integer, the size of the buffer
	 */
	public int size() {
		return size;
	}
	
	/*
	 * isEmpty function. Checks if the buffer is empty
	 * @return: boolean, true if the buffer is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * isFull function. Checks if the buffer is full
	 * @return: boolean, true if the buffer is full
	 */
	public boolean isFull() {
		return size == Capacity;
	}
	
	/*
	 * Enqueue function. Adds a given item x to the end of the buffer
	 * @param: double x, the item you want to add
	 */
	public void enqueue(double x) {
		if (isFull()) throw new IllegalStateException("Buffer is full!");
		//if (size == 0) first = size; --commented out for now, instead put first in constructor
		buffer[(first + size) % Capacity] = x;
		size++;
	}
	
	/*
	 * Dequeue function. Removes and returns the item at the front of the buffer
	 * @return: double, the value that was the front of the buffer
	 */
	public double dequeue() {
		if (isEmpty()) throw new IllegalStateException("Buffer is empty!");
		double oldFirst = buffer[first%Capacity];
		first++;
		size--;
		return oldFirst;
	}
	
	/*
	 * Peek function. Return (but do not delete) the item at the front of the buffer
	 * @return: double, the value at the front of the buffer
	 */
	public double peek() {
		return buffer[first%Capacity]; //**ASK is there point in modulo here?
	} 
	
	// testing functions
	public static void main(String[] args) {
		RingBuffer test = new RingBuffer(5);
		test.isFull();
		test.isEmpty();
		test.enqueue(1);
		test.enqueue(2);
		test.isEmpty();
		test.enqueue(3);
		test.enqueue(4);
		test.isFull();
		test.dequeue();
		test.dequeue();
		System.out.println(test.size());
		System.out.println(test.peek());
	}
	
}