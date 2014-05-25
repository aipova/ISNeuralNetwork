package aipova.isneuralnetwork;

import static aipova.isneuralnetwork.NeuralNet.okrugl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void check(NeuralNet network) {
        System.out.println("Проверка работоспособности сети. Слоев " + network.innerLayerCount + " по " + 
                network.innerNeuronCount + " нейронов");
        System.out.println("0.1*0.2*0.5 = 0.01; Сеть:" + network.findResult(0.1, 0.2, 0.5));
        System.out.println("0.35*0.2*0.8 = 0.056; Сеть:" + network.findResult(0.35, 0.2, 0.8));
        System.out.println("0.8*0.4*0.3 = 0,096; Сеть:" + network.findResult(0.8, 0.4, 0.3));
        System.out.println("0.7*0.4*0.2 = 0,056; Сеть:" + network.findResult(0.7, 0.4, 0.2));
        System.out.println("0.27*0.57*0.33 = 0,0507; Сеть:" + network.findResult(0.27, 0.57, 0.33));
    }
    public static void main( String[] args )
    {
        double[][] trainingSample = new double[20][4];
        // заполним массив значений для обучения
        for (int i = 0; i < trainingSample.length; i++) {
            trainingSample[i][0] = okrugl(Math.random(), 100);
            trainingSample[i][1] = okrugl(Math.random(), 100);
            trainingSample[i][2] = okrugl(Math.random(), 100);
            trainingSample[i][3] = trainingSample[i][0] * trainingSample[i][1] 
                    * trainingSample[i][2];
        }
        NeuralNet network = new NeuralNet(3, 5);
        network.trainingSample = trainingSample;
        network.training();
        check(network);
        
        network = new NeuralNet(3, 4);
        network.trainingSample = trainingSample;
        network.training();
        check(network);
        
        network = new NeuralNet(3, 3);
        network.trainingSample = trainingSample;
        network.training();
        check(network);
        
        network = new NeuralNet(4, 4);
        network.trainingSample = trainingSample;
        network.training();
        check(network);
        
        network = new NeuralNet(4, 3);
        network.trainingSample = trainingSample;
        network.training();
        check(network);
        
        network = new NeuralNet(4, 5);
        network.trainingSample = trainingSample;
        network.training();
        check(network);
        
        network = new NeuralNet(2, 3);
        network.trainingSample = trainingSample;
        network.training();
        check(network);
        
        network = new NeuralNet(2, 4);
        network.trainingSample = trainingSample;
        network.training();
        check(network);
        
        network = new NeuralNet(2, 5);
        network.trainingSample = trainingSample;
        network.training();
        check(network);
        
        
 
    }
}
