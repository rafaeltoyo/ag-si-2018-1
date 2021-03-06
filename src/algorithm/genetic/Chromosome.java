package algorithm.genetic;

import algorithm.base.Particle;
import controller.OutputController;

import java.util.ArrayList;

/**
 * Classe que representa um cromossomo (conjunto de genes) do AG
 *
 */
abstract public class Chromosome implements Particle, Cloneable, Comparable<Chromosome>
{

    protected ArrayList<Gene> genes = new ArrayList<>();

    protected float fitness = 0;

    public Chromosome clone() throws CloneNotSupportedException {
        Chromosome clone = (Chromosome) super.clone();
        clone.genes = new ArrayList<>();
        for (Gene gene : this.genes) {
            clone.genes.add(gene.clone());
        }
        return clone;
    }

    public Chromosome rndClone() throws CloneNotSupportedException {
        Chromosome clone = (Chromosome) super.clone();
        clone.genes = new ArrayList<>();
        for (Gene gene : this.genes) {
            clone.genes.add(gene.rndClone());
        }
        return clone;
    }

    protected void addGene(Gene gene) { this.genes.add(gene); }

    public ArrayList<Gene> getGenes() { return genes; }

    public float calcFitness() {
        this.fitness = 0;
        for (Gene gene : this.genes) {
            if (gene.isActive()) {
                this.fitness += gene.getValue();
            }
        }

        return this.fitness;
    }

    @Override
    public float fitness() {
        if (fitness == 0) {
            return calcFitness();
        }
        return fitness;
    }

    @Override
    public int compareTo(Chromosome o) {
        return (int) (this.fitness() - o.fitness());
    }

    @Override
    public void print() {
        OutputController.getInstance().print(this.fitness + " - |", false);
        //System.out.print(this.fitness + " - |");
        for (Gene gene : this.genes) {
            OutputController.getInstance().print(gene.isActive() ? "1|" : "0|", false);
            //System.out.print(gene.isActive() ? "1|" : "0|");
        }
    }
}
