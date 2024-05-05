package com.baeldung.algorithms.ga.ant_colony;

public final class Ant {

	final int trailSize;
	final int[] trail;
	final boolean[] visited;

	public Ant(int tourSize) {
		this.trailSize = tourSize;
		this.trail = new int[tourSize];
		this.visited = new boolean[tourSize];
	}

	void visitCity(int currentIndex, int city) {
		trail[currentIndex + 1] = city;
		visited[city] = true;
	}

	boolean visited(int i) {
		return visited[i];
	}

	double trailLength(double[][] graph) {
        var length = graph[trail[trailSize - 1]][trail[0]];
		for (var i = 0; i < trailSize - 1; i++) {
			length += graph[trail[i]][trail[i + 1]];
		}
		return length;
	}

	void clear() {
		for (var i = 0; i < trailSize; i++) {
            visited[i] = false;
        }
	}

}
