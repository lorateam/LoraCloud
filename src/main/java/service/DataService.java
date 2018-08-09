package service;

import model.Data;
import org.springframework.stereotype.Service;

@Service
public class DataService extends ServiceBase {
    public void insert(Data data){
        dataMapper.insert(data);
    }
}
