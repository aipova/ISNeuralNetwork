/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aipova.isneuralnetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author aipova
 */
public class NeuralNet {

    List<Layer> layers = new ArrayList<Layer>();
    double[][] trainingSample = new double[100][4];
    double speed = 0.8;
    int innerNeuronCount = 3;
    int innerLayerCount = 3;
    public NeuralNet() {
        createNet();
    }

    public NeuralNet(int innerNeuronCount, int innerLayerCount) {
        this.innerNeuronCount = innerNeuronCount;
        this.innerLayerCount = innerLayerCount;
        createNet();
    }
    

    void createNet() {

        // заполнение нейронами
        Layer inputLayer = new Layer();
        Neuron input1 = new Neuron();
        inputLayer.neurons.add(input1);

        Neuron input2 = new Neuron();
        inputLayer.neurons.add(input2);

        Neuron input3 = new Neuron();
        inputLayer.neurons.add(input3);

        layers.add(inputLayer);
        for (int i = 0; i < innerLayerCount; i++) {
            Layer innerLayer = new Layer();
            for (int j = 0; j < innerNeuronCount; j++) {
                innerLayer.neurons.add(new Neuron());
            }
            layers.add(innerLayer);
        }
        
        Neuron output = new Neuron();
        Layer outputLayer = new Layer();
        outputLayer.neurons.add(output);
        layers.add(outputLayer);

        // создание связей
        for (int i = 0; i < layers.size() - 1; i++) {
            Layer l1 = layers.get(i);
            Layer l2 = layers.get(i + 1);
            for (Neuron n1 : l1.neurons) {
                for (Neuron n2 : l2.neurons) {
                    Link l = new Link((Math.random()/100), n1, n2);
                    n1.outputLinks.add(l);
                    n2.inputLinks.add(l);
                }
            }
        }
    }

    public double findResult(double inputValue1, double inputValue2, 
            double inputValue3) {
        Layer input = layers.get(0);
        Neuron input1 = input.neurons.get(0);
        Neuron input2 = input.neurons.get(1);
        Neuron input3 = input.neurons.get(2);
        input1.value = inputValue1;
        input2.value = inputValue2;
        input3.value = inputValue3;
        for (int i = 1; i < layers.size(); i++) {
            Layer l = layers.get(i);
            for (Neuron n : l.neurons) {
                n.activation();
            }
        }
        return layers.get(layers.size() - 1).neurons.get(0).value;
    }

    public void calculateErrors(double y) {
        Layer output = layers.get(layers.size() - 1);
        for (Neuron n : output.neurons) {
            n.errorOutputLayer(y);
        }
        for (int i = layers.size() - 2; i > 0; i--) {
            Layer inner = layers.get(i);
            for (Neuron n : inner.neurons) {
                n.errorInnerLayer();
            }
        }
    }

    public void weightCorrection() {
        for (int i = layers.size() - 1; i >= 0; i--) {
            Layer layer = layers.get(i);
            for (Neuron n : layer.neurons) {
                for (Link l : n.inputLinks) {
                    double dw = -1 * speed * l.destNeuron.error 
                            * l.sourceNeuron.value;
                    l.w += dw;
                }
            }
        }
    }

    public double Ew(double[] factResults) {
        double sum = 0;
        for (int i = 0; i < factResults.length; i++) {
            sum += (factResults[i] - trainingSample[i][3])
                    * (factResults[i] - trainingSample[i][3]);
        }
        return sum / 2;
    }

    public void training() {
        int era = 1;
        double netError = 1;
        double[] results;
        do {
            results = new double[trainingSample.length];
            for (int i = 0; i < trainingSample.length; i++) {
                double result = findResult(trainingSample[i][0],
                        trainingSample[i][1], trainingSample[i][2]);
                results[i] = result;
                calculateErrors(trainingSample[i][3]);
                weightCorrection();
            }
            netError = Ew(results);
            era++;
            if (era == 100000) {
                break;
            }
        } while (netError > 0.001);
        System.out.println("Эпох обучения - " + era);
        System.out.println("Ошибка - " + netError);
    }

    static double okrugl(double x, int a) {
        return Math.rint(x * a) / a;
    }

    void print() {
        for (Layer l : layers) {
            for (Neuron n : l.neurons) {
                System.out.print(n.value + "\t");
            }
            System.out.println();
        }
    }
}
