/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aipova.isneuralnetwork;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aipova
 */
public class Neuron {
    double shift = 0;
    double a = 1;
    double value = 0;
    double error = 0;
    double S = 0;
    List<Link> inputLinks  = new ArrayList<Link>();
    List<Link> outputLinks = new ArrayList<Link>();

    public Neuron() {
    }
    
    // рассчитывает выходной сигнал нейрона (value)
    public void activation() {
        S = 0;
        for (Link l : inputLinks) {
            S += l.w*l.sourceNeuron.value;
        }
        // линейная
        this.value =  1/ (1 + Math.pow(Math.E, -1*a*S));
    }
    
    // рассчитывает ошибку
    public void errorOutputLayer(double y) {
        this.error = a*value*(1-value)*(value-y);
    }
    
    // рассчитывает ошибку
    public void errorInnerLayer() {
        double sum = 0;
        for (Link l : outputLinks) {
            sum += l.w*l.destNeuron.error;
        }
        error = a*value*(1-value)*sum;
    } 
}
