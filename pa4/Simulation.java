
//Bora Mutluoglu | bmutluog | 1564633 | cs12b | March 4, 2018 |
//Implementing a linked list based Queue system to arraycess a certain
//amount of jobs over a certain time.
import java.io.*;
import java.util.Scanner;

public class Simulation {

	public static Job getJob(Scanner in) {
		String[] s = in.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		return new Job(a, d);
	}

	public static void main(String[] args) throws IOException {
		Queue storage = new Queue();
		Queue storage1 = new Queue();

		// 1. check command line arguments
		if (args.length < 1) {
			System.out.println("Usage: Simulation input_file");
			System.exit(1);
		}

		// input file is read in with a scanner
		Scanner in = new Scanner(new File(args[0]));

		// 2. open files for reading and writing
		PrintWriter out = new PrintWriter(new FileWriter(args[0] + ".rpt"));
		PrintWriter out1 = new PrintWriter(new FileWriter(args[0] + ".trc"));
		Job jo = null;
		// 3. read in jobs from input file
		// numjobs gets the amount of jobs in the file which is the first
		// line/number
		int numjobs = in.nextInt();
		// reads in the first number in the file as the number of jobs and skips
		// the next line
		in.nextLine();
		//
		for (int i = 0; i < numjobs; i++) {
			Job j = getJob(in);
			storage.enqueue(j);
			storage1.enqueue(j);
		}
		Queue done = new Queue();
		// header for the report file.
		out.println("Report file: " + (args[0] + ".rpt"));
		out.println(numjobs + " Jobs:");
		out.println(storage.toString());
		out.println();
		out.println("***********************************************************");
		// prints header of trace files
		out1.println("Trace file: " + (args[0] + ".trc"));
		out1.println(numjobs + " Jobs:");
		out1.println(storage.toString());
		out1.println();

		// 4. run simulation with n arraycessors for n=1 to n=numjobs-1 {
		for (int n = 1; n < numjobs; n++) {
			out1.println("*****************************");
			if (n == 1) {
				out1.println(n + " processor:");
				out1.println("*****************************");
			} else if (n > 1) {
				out1.println(n + " processors:");
				out1.println("*****************************");
			}
			Queue[] array = new Queue[n];
			// 5. declare and initialize an array of n processor Queues and any
			// necessary storage Queues
			for (int o = 0; o < n; o++) {
				array[o] = new Queue();
				// System.out.println(array[o].toString());
			}
			int time = 0;
			Queue swit = new Queue();
			int processors = numjobs - 1;
			int top = processors + 1;
			out1.println("time=" + time);
			out1.println("0: " + storage);
			for (int i = 0; i < n; i++) {
				out1.println((i + 1) + ": " + array[i]);
				// System.out.println(array[i].toString());
			}
			out1.println();
			// this for loop places all jobs in the correct order in storage
			// queues.
			boolean empty = true;
			boolean check = false;
			int[] jobs = new int[numjobs];
			int count = 0;
			// 6. while unprocessed jobs remain {
			// 7. determine the time of the next arrival or finish event and
			// update time
			while (storage.isEmpty() == false || empty == false) {
				for (int m = 0; m < n; m++) {
					if (!array[m].isEmpty() && ((Job) (array[m].peek())).getFinish() == -1) {
						((Job) (array[m].peek())).computeFinishTime(time);
					}
				}
				if (check != false) {
					out1.println("time = " + time);
					out1.println("0: " + storage + swit);
					for (int g = 0; g < n; g++) {
						out1.println((g + 1) + ":" + array[g]);
					}
					out1.println();
					check = false;
				}
				time++;
				// 8. complete all processes finishing now
				for (int pi = 0; pi < n; pi++) {
					if (array[pi].isEmpty() != true) {
						if (((Job) array[pi].peek()).getFinish() == time) {

							jobs[count++] = ((Job) array[pi].peek()).getWaitTime();
							swit.enqueue(((Job) array[pi].dequeue()));
							check = true;
						}
					}
				}
				// 9. if there are any jobs arriving now, assign them to a
				// processor
				// Queue of minimum length and with lowest index in the queue
				// array.
				while ((!storage.isEmpty()) && ((Job) (storage.peek())).getArrival() == time) {
					int min = 2000;
					int less = 0;
					for (int zi = 0; zi < n; zi++) {
						if (array[zi].length() < min) {
							min = array[zi].length();
							less = zi;
						}
					}
					array[less].enqueue(storage.dequeue());
					check = true;
				}
				// 10. } end loop
				empty = true;
				for (int r = 0; r < n; r++) {
					if (array[r].isEmpty() != true) {
						empty = false;
						break;
					}
				}
			}
			// print out to trace file
			out1.println("time=" + time);

			out1.println("0: " + storage + swit);
			for (int p = 0; p < n; p++) {
				out1.println((p + 1) + ":" + array[p]);
			}
			// compute all jobs and then reset the time
			int tw = 0;
			int mw = 0;
			double avgw = 0;
			// filling job array
			int track = 0;
			while (track < jobs.length) {
				tw += jobs[track];
				track++;
			}
			for (int u = 0; u < jobs.length; u++) {
				if (jobs[u] > mw) {
					mw = jobs[u];
				}
			}
			avgw = tw / (double) jobs.length;

			// print out the processor jobs now
			// 11. compute the total wait, maximum wait, and average wait for
			// all Jobs, then reset finish times
			// if there is only one processor print the singular version of the
			// word.
			// if there is more than one print the plural version, as well as
			// all the relative times.
			if (n == 1) {
				out.println("1 processor: totalWait=" + tw + ", maxWait=" + mw + ", averageWait="
						+ String.format("%.2f", avgw));
			} else if (n >= 2) {
				out.println(n + " processors: totalWait=" + tw + ", maxWait=" + mw + ", averageWait="
						+ String.format("%.2f", avgw));
			}
			for (int v = 0; v < storage1.length(); v++) {
				storage1.enqueue(((Job) storage1.peek()));
				((Job) storage1.peek()).resetFinishTime();
				storage.enqueue(((Job) storage1.dequeue()));
			}
			swit.dequeueAll();
			// 12. } end loop
		}
		// 13. close input and output files
		in.close();
		out.close();
		out1.close();
	}
}