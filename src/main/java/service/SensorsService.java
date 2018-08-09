package service;

import model.Sensors;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorsService extends ServiceBase{
    public List<Sensors> listSensors(){
        return sensorsMapper.listSensors();
    }
}
