package org.maxur.akkacluster;

import java.util.TreeMap;

import akka.actor.ActorRef;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.routing.SmallestMailboxPool;

public class Kernel extends UntypedAbstractActor {		
	
	//work ID -> (currentWorkerCount, isPrime)
	private TreeMap<Integer, Pair<Integer, Boolean>> jobs = new TreeMap<Integer, Pair<Integer, Boolean>>();
	private int job_id_counter = 0;
	private ActorRef router = getContext().actorOf(new SmallestMailboxPool(5).props(Props.create(PrimeWorker.class)), "workers");
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		
		if (arg0 instanceof String) {			
			int i = Integer.valueOf((String) arg0);
			for (int j = 2; j < i; j++) 
				router.tell(new Job(job_id_counter, i, j, j+1), getSelf());			
			jobs.put(job_id_counter, Pair.get(i - 2, true));
			job_id_counter++;
			return;
		}
		
		if (arg0 instanceof JobResult) {
			JobResult jr = (JobResult) arg0;			
			Pair<Integer, Boolean> task = jobs.get(jr.jobID);
			if (!jr.isPrime) task.second = false;
			task.first--;
			if (task.first < 1){
				System.out.println("Число " + jr.jobID + (task.second ? " простое" : " составное"));
				jobs.remove(jr.jobID);
			}
		}
		unhandled(arg0);		
	}
	
}

