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
    double shift;
    double a = 1;
    double value;
    List<Link> inputLinks  = new ArrayList<Link>();
    List<Link> outputLinks = new ArrayList<Link>();
    
    // рассчитывает выходной сигнал нейрона (value)
    public void activation() {
        double S = 0;
        for (Link l : inputLinks) {
            S += l.w*l.sourceNeuron.value;
        }
        // лог-сигмоидная
        this.value =  1/(1 + Math.pow(Math.E, -1*S));
    }
    
}
