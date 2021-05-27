package org.maxur.akkacluster;

public class Job {
	
	public final int jobID; //Идентификатор задачи, используется для аггрегации результатов
	public final int number; //Исследуемое число
	public final int from; //Нижняя граница диапазона перебора
	public final int to; //Верхняя граница диапазона перебора
	public Job(int jobID, int number, int from, int to) {
		this.jobID = jobID; this.number = number; this.from = from; this.to = to;
	}
}