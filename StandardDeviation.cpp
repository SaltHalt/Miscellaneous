#include <iostream>
#include <fstream>
#include <vector>
#include <cstdlib>
#include <cmath>
using namespace std;

float generateAverage(vector<int>sample);
float generateSquaredDifference(vector<int>sample, float mean);

int main(){
    vector<int>dataList;
    vector<int>sample;
    int dataPoint;
    float mean;
    float squaredDifference;
    float std_dev_naive;
    float std_dev_corrected;

    ifstream data ("Numbers.txt");
    for (int i=0; i<1000; i++){
        data>>dataPoint;
        dataList.push_back(dataPoint);
        cout<<"Datapoint "<<i<<endl;
    }
    data.close();
    cout<<"Loading complete"<<endl;
    ofstream deviation_naive ("Standard_Deviation_Naive.txt");
    ofstream deviation_corrected ("Standard_Deviation_Corrected.txt");
    for (int i=0; i<100; i++){
        sample.clear();
        for (int j=0; j<5; j++){//<--------------------------------------------------------------------------------sample size
            int index = (rand()*10)%999;
            sample.push_back(dataList[index]);
        }
        mean = generateAverage(sample);
        squaredDifference = generateSquaredDifference(sample,mean);
        std_dev_naive = squaredDifference/sample.size();
        std_dev_corrected = squaredDifference/(sample.size()-1);

        deviation_naive<<sqrt(std_dev_naive)<<endl;
        deviation_corrected<<sqrt(std_dev_corrected)<<endl;
        cout<<"Sample "<<i+1<<" complete. Mean: "<<mean<<" Naive Variance: "<<std_dev_naive<<" Corrected Variance: "<<std_dev_corrected<<endl;
    }

}

float generateAverage(vector<int>sample){
    int partialSum=0;
    for (int i = 0; i<sample.size(); i++)
    partialSum+=sample[i];
    return partialSum/sample.size();
}

float generateSquaredDifference(vector<int>sample, float mean){
    int partialSum=0;
    for (int i = 0; i<sample.size(); i++){

        float deviation = ((float) sample[i]) - mean;
        //cout<<i<<" Sample "<< sample[i]<<"Mean "<<mean<<" Deviation "<<deviation<<endl;
        partialSum+=pow(deviation,2);
    }
    return partialSum;
}
