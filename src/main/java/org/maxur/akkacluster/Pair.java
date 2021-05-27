package org.maxur.akkacluster;

public class Pair<A,B> {
	public A first;
	public B second;
	public static <C,D> Pair<C,D> get(C a, D b) {
		Pair<C,D> p = new Pair();
		p.first = a;
		p.second = b;
		return p;
	}
}