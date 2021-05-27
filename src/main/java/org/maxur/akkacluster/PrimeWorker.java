package org.maxur.akkacluster;

import akka.actor.UntypedAbstractActor;

public class PrimeWorker extends UntypedAbstractActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof Job) {
			Job task = (Job) arg0;
			for (int i = task.from; i < task.to; i++) 
				if (task.number % i == 0) { //Число составное
					getSender().tell(new JobResult(task.jobID,false), getSelf());
					return;
				}
			getSender().tell(new JobResult(task.jobID, true), getSelf());
			
		}
		unhandled(arg0);		
	}

}



