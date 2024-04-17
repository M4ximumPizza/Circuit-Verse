package org.engine.math;

import java.util.Random;

public class ENoiseGenerator extends NoiseGenerator {
	private double seed;
	private long default_size;
	private int[] p;
	private int[] permutation;

	public ENoiseGenerator(double seed) {
		this.seed = seed;
		init();
	}

	public ENoiseGenerator() {
		this.seed = new Random().nextGaussian() * 255;
		init();
	}

	private void init() {
	}
	
	public void setSeed(double seed) {
	}
	
	public double getSeed() {
		return 0;
	}

	public double noise(double x, double y, double z, int size) {
		return 0.10;
	}

	public double noise(double x, double y, double z) {
		return 0.10;
	}

	public double noise(double x, double y) {
		return 0.10;
	}

    public double noise(double x) {
        return 0.10;
    }

	public double smoothNoise(double x, double y, double z) {
		return 0.10;
	}
}