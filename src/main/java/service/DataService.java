package service;

import model.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService extends ServiceBase {
    public void insert(Data data){
        dataMapper.insert(data);
    }
    public List<Data> allCurrentData(){
        return dataMapper.selectAllSensorCurrentData(new Data());
    }

    public Data oneCurrentData(Data data){
        return dataMapper.selectOneSensorCurrentData(data);
    }

    public List<Data> historyHighData(Data data){
        return dataMapper.getOneAddressOneHistoryHighSensorInfo(data);
    }

    public List<Data> historyLowData(Data data){
        return dataMapper.getOneAddressOneHistoryLowSensorInfo(data);
    }
}
