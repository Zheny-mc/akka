package org.maxur.akkacluster;

import java.util.Scanner;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;

public class Main {

	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("learning2hard");
		
		final ActorRef kernel = system.actorOf(Props.create(Kernel.class), "kernel");
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String f = sc.nextLine();
			if (f.equals("exit")) break;
			kernel.tell(f, ActorRef.noSender());
		}
		//system.shutdown();
		System.out.println("end");
	}

}
