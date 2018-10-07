package service;

import model.Data;
import mqtt.Listener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService extends ServiceBase {
    public void insert(Data data){
        dataMapper.insert(data);
    }
    public List<Data> allCurrentData(int address_id){
        return dataMapper.selectAllSensorCurrentData(address_id);
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

    public void insertDatas(List<Data> datas){
        for(Data data:datas){
            dataMapper.insert(data);
        }
    }
}
