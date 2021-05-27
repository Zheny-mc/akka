package org.maxur.akkacluster;

public class JobResult {
	
	public final int jobID;
	public final boolean isPrime;
	public JobResult(int jobID, boolean isPrime) {
		this.jobID = jobID; this.isPrime = isPrime;
	}
}